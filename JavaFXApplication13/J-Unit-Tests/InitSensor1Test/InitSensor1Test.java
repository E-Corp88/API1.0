/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InitSensor1Test;

import de.hft.wiinf.ss.ecorp.consumer.InitSensor1;
import de.hft.wiinf.ss.ecorp.controller.FXMLDocumentController;
import de.hft.wiinf.ss.ecorp.event.EventDTO;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class InitSensor1Test {

    FXMLDocumentController ctrl;
    InitSensor1 in = new InitSensor1(ctrl);

    public InitSensor1Test() {
    }

    @Test
    public void saveDataTest() {
        ArrayList<EventDTO> list = new ArrayList<>();

        for (int i = 0; i <= 1000; i++) {
            EventDTO ev = new EventDTO(0.5, 3.4, 2000, "TestStringDate", "TestTypcode", i);
            in.saveData(list, ev.getTemp(), ev.getPressure(), ev.getRev(), ev.getDate(), ev.getTypecode(), ev.getId());
        }

        for (int i = 0; i <= 1000; i++) {
            EventDTO ev = new EventDTO(0.5, 3.4, 2000, "TestStringDate", "TestTypcode", i);
            
            assertTrue(list.get(i).getTemp() == ev.getTemp());
            assertTrue(list.get(i).getPressure() == ev.getPressure());
            assertTrue(list.get(i).getRev() == ev.getRev());
            assertTrue(list.get(i).getTypecode() == ev.getTypecode());
            assertTrue(list.get(i).getId() == ev.getId());

        }

    }
}
