package DBTest;


import de.hft.wiinf.ss.ecorp.db.DB;
import de.hft.wiinf.ss.ecorp.event.EventDTO;
import java.sql.SQLException;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class DBTest {

    DB db = new DB();
    EventDTO ev = new EventDTO(3.0, 2.0, 5000, "TestDate", "TestTypode", 1234567890);

    public DBTest() {
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void dbTestSensor1() {
        
        db.datalistsensor1.add(ev);
        assertTrue(ev.equals(db.datalistsensor1.get(0)));
        db.saveDBSensor1();   
        db.loadDBS1("1");       
        assertTrue(ev.getTemp() == db.tempValuesS1.get(0));
        assertTrue(ev.getPressure() == db.presValuesS1.get(0));
        assertTrue(ev.getRev() == db.revValuesS1.get(0));
        assertTrue(ev.getDate().equals(db.timeValuesS1.get(0)));  
        db.deleteDBS1("1");
        
        db.tempValuesS1.remove(0);
        db.presValuesS1.remove(0);
        db.revValuesS1.remove(0);
        db.timeValuesS1.remove(0);
        db.loadDBS1("1"); 
        //list is empty
        System.out.println(db.tempValuesS1.get(0));
        System.out.println(db.presValuesS1.get(0));
        System.out.println(db.revValuesS1.get(0));
        System.out.println(db.timeValuesS1.get(0));                 
    }
    
    @Test (expected = IndexOutOfBoundsException.class)
    public void dbTestSensor2(){
        db.datalistsensor2.add(ev);
        db.saveDBSensor2();   
        db.loadDBS2("1");       
        assertTrue(ev.getTemp() == db.tempValuesS2.get(0));
        assertTrue(ev.getPressure() == db.presValuesS2.get(0));
        assertTrue(ev.getRev() == db.revValuesS2.get(0));
        assertTrue(ev.getDate().equals(db.timeValuesS2.get(0)));  
        db.deleteDBS2("1");
        
        db.tempValuesS2.remove(0);
        db.presValuesS2.remove(0);
        db.revValuesS2.remove(0);
        db.timeValuesS2.remove(0);
        db.loadDBS2("1"); 
        //List is empty    
        System.out.println(db.tempValuesS2.get(0));
        System.out.println(db.presValuesS2.get(0));
        System.out.println(db.revValuesS2.get(0));
        System.out.println(db.timeValuesS2.get(0));
    }
    @After
    public void deleteTrash() {
        db.closeConnection();
    }
    

}
