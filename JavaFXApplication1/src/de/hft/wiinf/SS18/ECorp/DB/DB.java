package de.hft.wiinf.SS18.ECorp.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

	public static void main(String[] args) {

		try {

			Connection c = DriverManager.getConnection(
					"jdbc:derby:C:\\Users\\Alexi\\Documents\\IDE´s\\Eclipse\\Wi-Inf-Projekt-1\\DB;", "ecorp", "ecorp");
			Statement s = c.createStatement();

			s.executeQuery("test");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
