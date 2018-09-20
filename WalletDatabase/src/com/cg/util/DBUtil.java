package com.cg.util;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	static Connection con;
	static
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/demo1";
			String user="root";
			String password="root";
			con=DriverManager.getConnection(url,user,password);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public static Connection getConnect()
	{
		return con;
	}
	}
