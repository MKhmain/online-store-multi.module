package org.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	private static final String URL="jdbc:mysql://localhost:3306/";
	private static final String DB_NAME="learn_it_db";
	private static final String USER="root";
	private static final String PASSWORD="test";
	private static Connection instance;

	public DBUtils() {
	}
	public static Connection getInstance(){
		if(instance==null){
			try {

				instance= DriverManager.getConnection(URL+DB_NAME,USER,PASSWORD);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return instance;
	}
}
