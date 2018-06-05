package de.hft.wiinf.ss.ecorp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import de.hft.wiinf.ss.ecorp.event.EventDTO;

public class DB {

	public ArrayList<EventDTO> datalist = new ArrayList<>();

	public void saveDB(String[] args) {

		try {

			Connection c = DriverManager.getConnection(
					"jdbc:derby:C:\\Users\\Alexi\\Documents\\IDE�s\\Eclipse\\Wi-Inf-Projekt-1\\DB;", "ecorp",
					"ecorp");
			PreparedStatement ps = c.prepareStatement("INSERT INTO data VALUES (?, ?)");

			if (!datalist.isEmpty()) {
				for (int x = 0; x < datalist.size(); x++) {
					ps.setString(1, String.valueOf(datalist.get(x).getTemp()));
					ps.setString(2, String.valueOf(datalist.get(x).getPressure()));
					ps.setString(3, String.valueOf(datalist.get(x).getRev()));
					ps.setString(4, String.valueOf(datalist.get(x).getId()));
					ps.setString(5, String.valueOf(datalist.get(x).getDate()));
					ps.setString(6, String.valueOf(datalist.get(x).getTypecode()));
					ps.addBatch();

				}
			}

		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
