package com.common.database.unit;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.common.database.ConnectDB;

public class ConnectDBUnit {
	@Test
	public void mysqlConnectionTest(){
		try {
			Connection conn = ConnectDB.getConnection("MySQL", "127.0.0.1", "root","root");
			if (conn == null) {
				System.out.println("Connection the database is failled !");
			} else {
				System.out.println(conn.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
