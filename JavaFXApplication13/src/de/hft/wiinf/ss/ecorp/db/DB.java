package de.hft.wiinf.ss.ecorp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import de.hft.wiinf.ss.ecorp.event.EventDTO;

public class DB {

	public ArrayList<EventDTO> datalist = new ArrayList<>();
	Connection c;

	public void saveDB() {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {

			c = DriverManager.getConnection("jdbc:derby:MyDB;create=TRUE", "ecorp", "ecorp");
			Statement s = c.createStatement();

			try {
				s.executeUpdate("CREATE SCHEMA ECORP");
			} catch (SQLException e) {
			}

			try {
				s.executeUpdate(
						"CREATE TABLE ECORP.DATA (ZEIT INTEGER not null primary key, TEMPERATUR VARCHAR(30), DRUCK VARCHAR(30), UMDREHUNG VARCHAR(30), TYPECODE VARCHAR(50))");
			} catch (SQLException e) {
			}

			if (!datalist.isEmpty()) {
				for (int x = 0; x < datalist.size(); x++) {
					PreparedStatement ps = c.prepareStatement(
							"INSERT INTO ECORP.DATA (ZEIT,TEMPERATUR,DRUCK,UMDREHUNG,TYPECODE) VALUES(?,?,?,?,?)");
					ps.setInt(1, (int) (datalist.get(x).getDate().getTime()));
					ps.setString(2, String.valueOf(datalist.get(x).getTemp()));
					ps.setString(3, String.valueOf(datalist.get(x).getPressure()));
					ps.setString(4, String.valueOf(datalist.get(x).getRev()));
					ps.setString(5, String.valueOf(datalist.get(x).getTypecode()));
					ps.executeUpdate();
				}
			}

		} catch (

		SQLException e) {
			e.printStackTrace();
		}

	}

	public void closeConnection() {
		try {
			c.close();
			System.out.println("connection closed");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
