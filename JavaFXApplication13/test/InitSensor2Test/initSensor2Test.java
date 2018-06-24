/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InitSensor2Test;

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
public class initSensor2Test {

    FXMLDocumentController ctrl;
    InitSensor1 in = new InitSensor1(ctrl);

    public initSensor2Test() {
    }

    @Test
    public void saveDataTest() {
        ArrayList<EventDTO> list = new ArrayList<>();
        for (int i = 0; i <= 1000; i++) {
            in.saveData(list, 0.5, 3.4, 2000, "TestStringDate", "TestTypcode", i);
        }

        for (int i = 0; i <= 1000; i++) {
            list.get(i).equals(new EventDTO(0.5, 3.4, 2000, "TestStringDate", "TestTypcode", i));
        }
    }
}
