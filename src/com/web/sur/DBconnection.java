package com.web.sur;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

	public static Connection getConnection(String driver,String url,String user,String password) throws SQLException{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	return DriverManager.getConnection(url,user,password);
}
}
