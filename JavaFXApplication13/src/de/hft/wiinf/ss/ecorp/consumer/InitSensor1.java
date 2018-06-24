package de.hft.wiinf.ss.ecorp.consumer;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import de.hft.wiinf.cebarround.CeBarRoundDataSensor;
import de.hft.wiinf.cebarround.CeBarRoundObserver;
import de.hft.wiinf.cebarround.SensorEvent;
import de.hft.wiinf.cebarround.SensorRegister;
import de.hft.wiinf.ss.ecorp.controller.FXMLDocumentController;
import de.hft.wiinf.ss.ecorp.event.EventDTO;

import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class InitSensor1 implements CeBarRoundObserver<SensorEvent> {
	private static final Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private Executor dataex = Executors.newSingleThreadExecutor();

	SensorRegister app = new CeBarRoundDataSensor();

    /**
     * Temperatur from sensor1
     */
    public double temp = 0;

    /**
     * Pressure from sensor1
     */
    public double pressure = 0;

    /**
     * Revolution from sensor1
     */
    public int rev = 0;

    /**
     * Date to resulting sensor data
     */
    public Date date;

    /**
     * Typcode from sensor1
     */
    public String typecode = "";

    /**
     * Unique sensorID from sensor1
     */
    public long id = 0;

    
    public FXMLDocumentController ctrl;

    /**
     *
     * @param ctrl
     */
    public InitSensor1(FXMLDocumentController ctrl) {
		this.ctrl = ctrl;
	}

    public InitSensor1() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This mehtod adds a listener to SensorRegister
     */
    public void listen() {

		app.addListener(this);
	}

    /**
     * This method starts the measure
     */
    public void startMeasure() {
		app.startMeasure();
	}

    /**
     * This method stops the measure
     */
    public void stopMeasure() {
		app.stopMeasure();
	}

    /**
     * This method set the verbosity
     * @param t time in milliseconds 
     */
    public void setVerbosity(double t) {
		app.setVerbosity(t);
	}

    /**
     * This method sets all variables to the current value.<br>
     * It also delivers the current values to the FXMLDocumentController
     * @param event event which contains all resulting sensor data
     */
    @Override
	public void sensorDataEventListener(SensorEvent event) {
		dataex.execute(() -> {
			temp = event.getTemperature();
			pressure = event.getPressure();
			rev = event.getRevolutions();
			date = event.getDate();
			typecode = event.getSensorTypeCode();
			id = event.getUniqueSensorIdentifier();
			ctrl.dataChangedS1();
		});

	}
	
	
    /**
     * This method saves 1000 EventDTO objects in a arraylist
     *
     * @param datalist list which is needed to contain 1000 Objects
     * @param temp current temp
     * @param pressure current pressure
     * @param rev current revolution
     * @param date current date
     * @param typecode sensor typcode
     * @param id sensor ID
     */
	public void saveData(ArrayList<EventDTO> datalist, double temp, double pressure, int rev, String date,
			String typecode, long id) {
		EventDTO dto = new EventDTO(temp, pressure, rev, date, typecode, id);
		if (datalist.size() <= 1000) {
			datalist.add(dto);
		}
	}

}
