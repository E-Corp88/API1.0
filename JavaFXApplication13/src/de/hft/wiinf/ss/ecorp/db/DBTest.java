package de.hft.wiinf.ss.ecorp.db;

import static org.junit.Assert.*;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DBTest {
	

	@Test
	public void saveDBSensor1ClassNotFoundExceptionTest() {	
			
	}
	@Test
	public void saveDBSensor1SQLExceptionTest() {	
		DB db = new DB();
		//db.getArchiveS1(list, time);
		
						
	}
	
	
	@After
	public void deleteTrash() {
		
	}
	

}
