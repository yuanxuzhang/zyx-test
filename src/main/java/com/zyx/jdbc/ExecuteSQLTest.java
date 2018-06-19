package com.zyx.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class ExecuteSQLTest {

	public static void main(String[] args) throws IOException {

		try{
			
			Scanner in = args.length == 0 ? new Scanner(System.in) : new Scanner(Paths.get(args[0]));
			
			try(Connection conn = getConnection()){
				
				if(conn == null){
					return;
				}
				Statement stat = conn.createStatement();
				
				while(true){
					
					if(args.length == 0){
						System.out.println("Enter command or EXIt to exit：");
					}
					if(!in.hasNextLine()){
						return;
					}
					
					String line = in.nextLine();
					if(line.equalsIgnoreCase("EXIT")){
						return;
					}
					if(line.trim().endsWith(";")){
						line = line.trim();
						line = line.substring(0, line.length() - 1);
					}
					try{
						
						// 泛化执行器
						boolean isResult = stat.execute(line);
						if(isResult){
							ResultSet rs = stat.getResultSet();
							showResultSet(rs);
						}
						else{
							int updateCount = stat.getUpdateCount();
							System.out.println(updateCount + "rows updated");
						}
					}
					catch(SQLException e){
						for(Throwable ex : e){
							e.printStackTrace();
						}
					}
					
				}
			}
		}
		/*
		 * 每个SQLException都有一个由多个SQLException对象构成的链，这些对象可以通过getNextException方法获取。这个异常链是每个
		 * 异常都具有的由Throwable对象那个构成的“成因”链之外的异常链。SQLException类实现了Iterable<Throwable>接口，其iter-
		 * ator方法可以产生一个Iterable<Throwable>,这个迭代器可以迭代这两个链，首先迭代第一个SQLException的成因链，然后迭代下一
		 * 个SQLException，以此类推。
		 */
		catch(SQLException e){
			for(Throwable t : e){
				t.printStackTrace();
			}
		}
	}
	
	private static void showResultSet(ResultSet rs) throws SQLException {
		
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		
		for(int i = 1; i <= columnCount; i++){
			
			if(i > 1){
				System.out.print(", ");
				System.out.print(metaData.getColumnLabel(i));
			}
			System.out.println();
		}
		
		while(rs.next()){
			
			for(int i = 1; i <= columnCount; i++){
				
				if(i > 1){
					System.out.print(", ");
				}
				System.out.print(rs.getString(i));
			}
			System.out.println();
		}
	}

	public static Connection getConnection() throws IOException, SQLException{
		Properties props = new Properties();
		try(InputStream in = Files.newInputStream(Paths.get(""))){
			props.load(in);
		}
		
		String drivers = props.getProperty("jdbc.driver");
		try {
			Class.forName(drivers);
		} catch (ClassNotFoundException e) {
			// NOP
			return null;
		}
		
		String url = props.getProperty("jdbc.url");
		String username = props.getProperty("jdbc.username");
		String password = props.getProperty("jdbc.password");
		
		return DriverManager.getConnection(url, username, password);
	}

}
