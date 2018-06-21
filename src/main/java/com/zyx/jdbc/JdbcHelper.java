package com.zyx.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcHelper {

	public static void main(String[] args) throws SQLException {

		Connection conn = null;
		try {
			conn = getConnection();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn != null)
				conn.close();
		}
		
	}
	
	public static Connection getConnection() throws IOException, SQLException{
		Properties props = new Properties();
		//String separator = File.separator;
		try(InputStream in = JdbcHelper.class.getResourceAsStream("jdbc.properties")){
			props.load(in);
		}
		
		String drivers = props.getProperty("jdbc.drivers");
		if(drivers != null){
			//System.setProperty("jdbc.drivers", drivers);
		}
		/*try {
			Class.forName(drivers);
		} catch (ClassNotFoundException e) {
			// NOP
			return null;
		}*/
		
		String url = props.getProperty("jdbc.url");
		String username = props.getProperty("jdbc.username");
		String password = props.getProperty("jdbc.password");
		
		return DriverManager.getConnection(url, username, password);
	}

}
