
package com.onlinetutor.dbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
private Connection connection;
	
	public DBConnector(String dbURL, String user, String pwd) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		this.connection = DriverManager.getConnection(dbURL, user, pwd);
	}
	
	public Connection getConnection(){
		return this.connection;
	}
	
}
