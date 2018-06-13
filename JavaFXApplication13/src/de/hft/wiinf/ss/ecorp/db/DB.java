package de.hft.wiinf.ss.ecorp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import de.hft.wiinf.ss.ecorp.consumer.InitSensor1;
import de.hft.wiinf.ss.ecorp.controller.FXMLDocumentController;
import de.hft.wiinf.ss.ecorp.event.EventDTO;

public class DB {

	public ArrayList<EventDTO> datalistsensor1 = new ArrayList<>();
	public ArrayList<EventDTO> datalistsensor2 = new ArrayList<>();

	public ArrayList<Double> tempValues = new ArrayList<>();
	public ArrayList<Double> presValues = new ArrayList<>();
	public ArrayList<Integer> revValues = new ArrayList<>();
	public ArrayList<String> timeValues = new ArrayList<>();

	public Integer messIDS1 = 1;
	public Integer messIDS2 = 1;
	public Integer messIDNrS1 = 0;
	public Integer messIDNrS2 = 0;

	// FXMLDocumentController controller = new FXMLDocumentController();

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
						"CREATE TABLE ECORP.SENSOR1 (TEMPERATUR VARCHAR(30), DRUCK VARCHAR(30), UMDREHUNG VARCHAR(30), TIME VARCHAR(30), TYPECODE VARCHAR(50), MESSID VARCHAR(30), MESSIDNR VARCHAR(30))");
			} catch (SQLException e) {
			}

			if (!datalistsensor1.isEmpty()) {
				for (int x = 0; x < datalistsensor1.size(); x++) {
					PreparedStatement ps = c.prepareStatement(
							"INSERT INTO ECORP.SENSOR1 (TEMPERATUR,DRUCK,UMDREHUNG,TIME,TYPECODE,MESSID,MESSIDNR) VALUES(?,?,?,?,?,?,?)");

					ps.setString(1, String.valueOf(datalistsensor1.get(x).getTemp()));
					ps.setString(2, String.valueOf(datalistsensor1.get(x).getPressure()));
					ps.setString(3, String.valueOf(datalistsensor1.get(x).getRev()));
					ps.setString(4, (datalistsensor1.get(x).getDate().toString()));
					ps.setString(5, String.valueOf(datalistsensor1.get(x).getTypecode()));
					ps.setString(6, messIDS1.toString());
					ps.setString(7, messIDNrS1.toString());
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
						"CREATE TABLE ECORP.SENSOR2 (TEMPERATUR VARCHAR(30), DRUCK VARCHAR(30), UMDREHUNG VARCHAR(30),TIME VARCHAR(30), TYPECODE VARCHAR(50), MESSID VARCHAR(30))");
			} catch (SQLException e) {
			}

			if (!datalistsensor2.isEmpty()) {
				for (int x = 0; x < datalistsensor2.size(); x++) {
					PreparedStatement ps = c.prepareStatement(
							"INSERT INTO ECORP.SENSOR2 (TEMPERATUR,DRUCK,UMDREHUNG,TIME,TYPECODE,MESSID) VALUES(?,?,?,?,?,?)");
					ps.setString(1, (datalistsensor2.get(x).getDate().toString()));
					ps.setString(2, String.valueOf(datalistsensor2.get(x).getTemp()));
					ps.setString(3, String.valueOf(datalistsensor2.get(x).getPressure()));
					ps.setString(4, String.valueOf(datalistsensor2.get(x).getRev()));
					ps.setString(5, String.valueOf(datalistsensor2.get(x).getTypecode()));
					// ps.setString(6, controller.messIDS2.toString());
					ps.executeUpdate();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void getArchive(ArrayList<String> list) {

		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {

			c = DriverManager.getConnection("jdbc:derby:MyDB;create=TRUE", "ecorp", "ecorp");
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT MESSID from ECORP.SENSOR1");
			while (rs.next()) {
				list.add(rs.getString(1));
			}
			rs.close();

		} catch (SQLException e) {
		}
	}

	public void loadDBS1(String num) {

		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {

			c = DriverManager.getConnection("jdbc:derby:MyDB;create=TRUE", "ecorp", "ecorp");
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(
					"SELECT TEMPERATUR,DRUCK,UMDREHUNG,TIME from ECORP.SENSOR1 WHERE MESSID= '" + num + "'");
			while (rs.next()) {
				tempValues.add(rs.getDouble(1));
				presValues.add(rs.getDouble(2));
				revValues.add(rs.getInt(3));
				timeValues.add(rs.getString(4));

			}
			rs.close();

		} catch (

		SQLException e) {
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
