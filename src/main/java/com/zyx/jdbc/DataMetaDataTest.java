package com.zyx.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DataMetaDataTest {

	public static void main(String[] args) throws SQLException, IOException {

		try(Connection conn = JdbcHelper.getConnection()){
			
			if(conn != null){
				DatabaseMetaData metaData = conn.getMetaData();
				ResultSet schemasRS = metaData.getSchemas();
				metaData.generatedKeyAlwaysReturned();
				while(schemasRS.next()){
					ResultSetMetaData rsMetaData = schemasRS.getMetaData();
					int count = rsMetaData.getColumnCount();
					for(int i = 0; i < count; i++){
						System.out.print(rsMetaData.getColumnName(i)+ "  ");
					}
					
					System.out.println();
					
					for(int i = 0; i < count; i++){
						System.out.print(schemasRS.getObject(i));
					}
				}
			}
		}
	}

}
