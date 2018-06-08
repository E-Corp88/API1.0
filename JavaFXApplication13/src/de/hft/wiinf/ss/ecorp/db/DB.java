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

	public ArrayList<EventDTO> datalistsensor1 = new ArrayList<>();
	public ArrayList<EventDTO> datalistsensor2 = new ArrayList<>();

	Connection c;

	public void saveDBSensor1() {
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
						"CREATE TABLE ECORP.SENSOR1 (TIME VARCHAR(30), TEMPERATUR VARCHAR(30), DRUCK VARCHAR(30), UMDREHUNG VARCHAR(30), TYPECODE VARCHAR(50))");
			} catch (SQLException e) {
			}

			if (!datalistsensor1.isEmpty()) {
				for (int x = 0; x < datalistsensor1.size(); x++) {
					PreparedStatement ps = c.prepareStatement(
							"INSERT INTO ECORP.SENSOR1 (TIME,TEMPERATUR,DRUCK,UMDREHUNG,TYPECODE) VALUES(?,?,?,?,?)");
					ps.setString(1, (datalistsensor1.get(x).getDate().toString()));
					ps.setString(2, String.valueOf(datalistsensor1.get(x).getTemp()));
					ps.setString(3, String.valueOf(datalistsensor1.get(x).getPressure()));
					ps.setString(4, String.valueOf(datalistsensor1.get(x).getRev()));
					ps.setString(5, String.valueOf(datalistsensor1.get(x).getTypecode()));
					ps.executeUpdate();
				}
			}

		} catch (

		SQLException e) {
			e.printStackTrace();
		}

	}

	public void saveDBSensor2() {
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
						"CREATE TABLE ECORP.SENSOR2 (TIME VARCHAR(30), TEMPERATUR VARCHAR(30), DRUCK VARCHAR(30), UMDREHUNG VARCHAR(30), TYPECODE VARCHAR(50))");
			} catch (SQLException e) {
			}

			if (!datalistsensor2.isEmpty()) {
				for (int x = 0; x < datalistsensor2.size(); x++) {
					PreparedStatement ps = c.prepareStatement(
							"INSERT INTO ECORP.SENSOR2 (TIME,TEMPERATUR,DRUCK,UMDREHUNG,TYPECODE) VALUES(?,?,?,?,?)");
					ps.setString(1, (datalistsensor2.get(x).getDate().toString()));
					ps.setString(2, String.valueOf(datalistsensor2.get(x).getTemp()));
					ps.setString(3, String.valueOf(datalistsensor2.get(x).getPressure()));
					ps.setString(4, String.valueOf(datalistsensor2.get(x).getRev()));
					ps.setString(5, String.valueOf(datalistsensor2.get(x).getTypecode()));
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
