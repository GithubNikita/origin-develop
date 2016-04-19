package com.intexsoft.my.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class StudentStorage
{
	private String storageFileName;

	public StudentStorage(String storageFileName)
	{
		this.storageFileName = storageFileName;
	}

	public void clear() throws IOException
	{
		try (PrintWriter writer = new PrintWriter(new File(this.storageFileName));)
		{
			writer.print("");
		}
	}

	public void addNewStudent(Student newStudent) throws IOException
	{
		createFileIfNedded();
		try (OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(this.storageFileName, true), "UTF-8");
			BufferedWriter bw = new BufferedWriter(fw);)
		{
			String userString = this.getStudentFileString(newStudent);
			bw.write(userString);
		}
	}

	private void createFileIfNedded() throws IOException
	{
		File file = new File(this.storageFileName);
		if (!file.exists())
		{
			file.createNewFile();
		}
	}

	private String getStudentFileString(Student st)
	{
		String userString = st.getName() + " " + st.getGroupNumber() + "\n";
		return userString;
	}

	public ArrayList<Student> getAllStudents() throws IOException
	{
		ArrayList<Student> studentList = new ArrayList<Student>();
		File file = new File(this.storageFileName);
		try (InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
			BufferedReader br = new BufferedReader(reader);)
		{
			String line = null;
			while ((line = br.readLine()) != null)
			{
				Student student = this.parseStudentLine(line);
				studentList.add(student);
			}
		}
		return studentList;
	}

	private Student parseStudentLine(String line)
	{
		String[] splittedLine = line.split(" ");
		String name = splittedLine[0];
		int groupNumber = Integer.parseInt(splittedLine[1]);
		Student student = new Student(name, groupNumber);
		return student;
	}
}
