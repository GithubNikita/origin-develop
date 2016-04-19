package com.intexsoft.my.app;

import java.util.Scanner;
import com.intexsoft.my.app.Application;

public class App
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		try
		{
			Application app = new Application(scanner);
			app.run();
		}
		finally
		{
			scanner.close();
		}
	}
}
