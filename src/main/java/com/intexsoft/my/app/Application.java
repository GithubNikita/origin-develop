package com.intexsoft.my.app;

import java.io.IOException;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Application
{
	private final static String STORAGE_FILE_NAME = "students.txt";
	private StudentStorage studentStorage;
	private Scanner scanner;

	public Application(Scanner scanner)
	{
		this.studentStorage = new StudentStorage(Application.STORAGE_FILE_NAME);
		this.scanner = scanner;
	}

	public void run()
	{
		while (true)
		{
			showGeneralInfo();
			if (processInput(readUserInput()) == 0)
			{
				return;
			}
		}
	}

	private int processInput(int userInput)
	{
		switch (userInput)
		{
			case 1:
				addNewStudent();
				break;
			case 2:
				showStudentList();
				break;
			case 4:
				cleanAll();
				break;
		}
		return userInput;
	}

	private void cleanAll()
	{
		try
		{
			studentStorage.clear();
		}
		catch (IOException e)
		{
			showFileError(e);
		}
	}

	private void showFileError(IOException e)
	{
		System.out.println("Нет возможности работать с файлом: " + STORAGE_FILE_NAME + " по причине: " + e.getMessage());
	}

	private int readUserInput()
	{
		try
		{
			return scanner.nextInt();
		}
		catch (InputMismatchException e)
		{
			System.out.println("Некорректный ввод: ");
		}
		return -1;
	}

	private void showGeneralInfo()
	{
		System.out.println("1 - Добавить студента");
		System.out.println("2 - Показать студентов");
		System.out.println("4 - Удалить всех");
		System.out.println("0 - Выход");
	}

	private void showStudentList()
	{
		for (Student st : getStudents())
		{
			System.out.println(st.getName() + " " + st.getGroupNumber());
		}
	}

	private List<Student> getStudents()
	{
		try
		{
			return studentStorage.getAllStudents();
		}
		catch (IOException e)
		{
			showFileError(e);
		}
		return Collections.emptyList();
	}

	private void addNewStudent()
	{
		System.out.println("Введите имя студента");
		String name = scanner.next();
		System.out.println("Введите номер группы");
		int groupNumber = readGroup();
		if (groupNumber >= 0)
		{
			putToFileSystem(new Student(name, groupNumber));
		}
	}

	private void putToFileSystem(Student newStudent)
	{
		try
		{
			studentStorage.addNewStudent(newStudent);
		}
		catch (IOException e)
		{
			showFileError(e);
		}
	}

	private int readGroup()
	{
		try
		{
			return scanner.nextInt();
		}
		catch (InputMismatchException e)
		{
			System.out.println("Группа не валидна");
		}
		return -1;
	}
}
