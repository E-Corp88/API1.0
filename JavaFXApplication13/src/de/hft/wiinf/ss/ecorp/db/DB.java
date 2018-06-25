package de.hft.wiinf.ss.ecorp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hft.wiinf.ss.ecorp.event.EventDTO;

/**
 *
 * @author User
 */
public class DB {

    /**
     * List filled with EventDTO objects from Sensor1
     */
    public ArrayList<EventDTO> datalistsensor1 = new ArrayList<>();

    /**
     * List filled with EventDTO objects from Sensor2
     */
    public ArrayList<EventDTO> datalistsensor2 = new ArrayList<>();

    /**
     * List filled with temperature data from sensor1 out of the db
     */
    public ArrayList<Double> tempValuesS1 = new ArrayList<>();

    /**
     * List filled with pressure data from sensor1 out of the db
     */
    public ArrayList<Double> presValuesS1 = new ArrayList<>();

    /**
     * List filled with revolution data from sensor1 out of the db
     */
    public ArrayList<Integer> revValuesS1 = new ArrayList<>();

    /**
     * List filled with time data from sensor1 out of the db
     */
    public ArrayList<String> timeValuesS1 = new ArrayList<>();

    /**
     * List filled with temperature data from sensor1 out of the db
     */
    public ArrayList<Double> tempValuesS2 = new ArrayList<>();

    /**
     * List filled with pressure data from sensor1 out of the db
     */
    public ArrayList<Double> presValuesS2 = new ArrayList<>();

    /**
     * List filled with revoltuion data from sensor1 out of the db
     */
    public ArrayList<Integer> revValuesS2 = new ArrayList<>();

    /**
     * List filled with time data from sensor1 out of the db
     */
    public ArrayList<String> timeValuesS2 = new ArrayList<>();

    /**
     * Counter for measurement series
     */
    public Integer messIDS1 = 1;

    /**
     * Counter for measurement series
     */
    public Integer messIDS2 = 1;

    Connection c;

    /**
     * Constructer loads Database driver and establish a Connection
     */
    public DB() {
        loadDatabaseDriver();
        establishConnection();

    }

    private void establishConnection() {
        try {
            c = DriverManager.getConnection("jdbc:derby:MyDB;create=TRUE", "ecorp", "ecorp");
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    /**
     * This method saves data from sensor1 into database
     */
    public void saveDBSensor1() {

        try {

            Statement s = c.createStatement();

            try {
                s.executeUpdate("CREATE SCHEMA ECORP");
            } catch (SQLException e) {
            }

            try {
                s.executeUpdate(
                        "CREATE TABLE ECORP.SENSOR1 (TEMPERATUR VARCHAR(30), DRUCK VARCHAR(30), UMDREHUNG VARCHAR(30), TIME VARCHAR(30), TYPECODE VARCHAR(50), MESSID VARCHAR(30))");
            } catch (SQLException e) {
            }

            if (!datalistsensor1.isEmpty()) {
                for (int x = 0; x < datalistsensor1.size(); x++) {
                    PreparedStatement ps = c.prepareStatement(
                            "INSERT INTO ECORP.SENSOR1 (TEMPERATUR,DRUCK,UMDREHUNG,TIME,TYPECODE,MESSID) VALUES(?,?,?,?,?,?)");

                    ps.setString(1, String.valueOf(datalistsensor1.get(x).getTemp()));
                    ps.setString(2, String.valueOf(datalistsensor1.get(x).getPressure()));
                    ps.setString(3, String.valueOf(datalistsensor1.get(x).getRev()));
                    ps.setString(4, (datalistsensor1.get(x).getDate().toString()));
                    ps.setString(5, String.valueOf(datalistsensor1.get(x).getTypecode()));
                    ps.setString(6, messIDS1.toString());
                    ps.executeUpdate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void loadDatabaseDriver() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method saves data from sensor2 into database
     */
    public void saveDBSensor2() {

        try {

            Statement s = c.createStatement();

            try {
                s.executeUpdate("CREATE SCHEMA ECORP");
            } catch (SQLException e) {
            }

            try {
                s.executeUpdate(
                        "CREATE TABLE ECORP.SENSOR2 (TEMPERATUR VARCHAR(30), DRUCK VARCHAR(30), UMDREHUNG VARCHAR(30), TIME VARCHAR(30), TYPECODE VARCHAR(50), MESSID VARCHAR(30))");
            } catch (SQLException e) {
            }

            if (!datalistsensor2.isEmpty()) {
                for (int x = 0; x < datalistsensor2.size(); x++) {
                    PreparedStatement ps = c.prepareStatement(
                            "INSERT INTO ECORP.SENSOR2 (TEMPERATUR,DRUCK,UMDREHUNG,TIME,TYPECODE,MESSID) VALUES(?,?,?,?,?,?)");

                    ps.setString(1, String.valueOf(datalistsensor2.get(x).getTemp()));
                    ps.setString(2, String.valueOf(datalistsensor2.get(x).getPressure()));
                    ps.setString(3, String.valueOf(datalistsensor2.get(x).getRev()));
                    ps.setString(4, (datalistsensor2.get(x).getDate().toString()));
                    ps.setString(5, String.valueOf(datalistsensor2.get(x).getTypecode()));
                    ps.setString(6, messIDS2.toString());
                    ps.executeUpdate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method gets a messID and a Time from Database and puts them into the
     * two given lists
     *
     * @param list list which should be filled with messID from Sensor1
     * @param time list which should be filled with time from Sensor1
     */
    public void getArchiveS1(ArrayList<String> list, ArrayList<String> time) {

        try {

            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT MESSID,TIME from ECORP.SENSOR1");
            while (rs.next()) {
                list.add(rs.getString(1));
                time.add(rs.getString(2));

            }
            rs.close();

        } catch (SQLException e) {
        }
    }

    /**
     * This method gets a messID and a Time from Database and puts them into the
     * two given lists
     *
     * @param list list which should be filled with messID from Sensor1
     * @param time which should be filled with time from Sensor1
     */
    public void getArchiveS2(ArrayList<String> list, ArrayList<String> time) {

        try {

            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT MESSID,TIME from ECORP.SENSOR2");
            while (rs.next()) {
                list.add(rs.getString(1));
                time.add(rs.getString(2));

            }
            rs.close();

        } catch (SQLException e) {
        }
    }

    /**
     * This method loads sensor1 data from the database
     *
     * @param num
     */
    public void loadDBS1(String num) {

        try {

            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(
                    "SELECT TEMPERATUR,DRUCK,UMDREHUNG,TIME from ECORP.SENSOR1 WHERE MESSID=" + "'" + num + "'");
            while (rs.next()) {
                tempValuesS1.add(rs.getDouble(1));
                presValuesS1.add(rs.getDouble(2));
                revValuesS1.add(rs.getInt(3));
                timeValuesS1.add(rs.getString(4));

            }
            rs.close();

        } catch (SQLException e) {
        }
    }

    /**
     * This method loads sensor2 data from the database
     *
     * @param num
     */
    public void loadDBS2(String num) {

        try {

            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(
                    "SELECT TEMPERATUR,DRUCK,UMDREHUNG,TIME from ECORP.SENSOR2 WHERE MESSID=" + "'" + num + "'");
            while (rs.next()) {
                tempValuesS2.add(rs.getDouble(1));
                presValuesS2.add(rs.getDouble(2));
                revValuesS2.add(rs.getInt(3));
                timeValuesS2.add(rs.getString(4));

            }
            rs.close();

        } catch (SQLException e) {
        }
    }

    /**
     * This method deletes sensor1 data from the database
     *
     * @param num
     */
    public void deleteDBS1(String num) {

        try {
            Statement s = c.createStatement();
            s.executeUpdate("DELETE from ECORP.SENSOR1 WHERE MESSID=" + "'" + num + "'");
            s.close();

        } catch (SQLException e) {
        }
    }

    /**
     * This method deletes sensor2 data from the database
     *
     * @param num
     */
    public void deleteDBS2(String num) {

        try {
            Statement s = c.createStatement();
            s.executeUpdate("DELETE from ECORP.SENSOR2 WHERE MESSID=" + "'" + num + "'");

        } catch (SQLException e) {
        }
    }

    /**
     * This method closes the database connection
     */
    public void closeConnection() {
        try {
            c.close();
            System.out.println("connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
