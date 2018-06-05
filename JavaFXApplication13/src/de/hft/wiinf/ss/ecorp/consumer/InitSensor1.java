package de.hft.wiinf.ss.ecorp.consumer;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import de.hft.wiinf.cebarround.CeBarRoundDataSensor;
import de.hft.wiinf.cebarround.CeBarRoundObserver;
import de.hft.wiinf.cebarround.SensorEvent;
import de.hft.wiinf.cebarround.SensorRegister;
import de.hft.wiinf.ss.ecorp.controller.FXMLDocumentController;
import de.hft.wiinf.ss.ecorp.table.FXMLTableController;

import java.util.logging.Logger;

public class InitSensor1 implements CeBarRoundObserver<SensorEvent> {
	private Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private Executor dataex = Executors.newSingleThreadExecutor();
	private Executor dataex2 = Executors.newSingleThreadExecutor();

	SensorRegister app = new CeBarRoundDataSensor();

	public double temp = 0;
	public double pressure = 0;
	public int rev = 0;
	public Date date;
	public String typecode = "";
	public long id = 0;

	public FXMLDocumentController ctrl;
	public FXMLTableController tableCtrl = new FXMLTableController ();

	public InitSensor1(FXMLDocumentController ctrl) {
		this.ctrl = ctrl;
	}

	public InitSensor1(FXMLTableController tableCtrl) {
		this.tableCtrl = tableCtrl;
	}

	public void listen() {

		app.addListener(this);
	}

	public void startMeasure() {
		app.startMeasure();
	}

	public void stopMeasure() {
		app.stopMeasure();
	}

	public void setVerbosity(double t) {
		app.setVerbosity(t);
	}

	@Override
	public void sensorDataEventListener(SensorEvent event) {
		dataex.execute(() -> {
			temp = event.getTemperature();
			pressure = event.getPressure();
			rev = event.getRevolutions();
			date = event.getDate();
			typecode = event.getSensorTypeCode();
			id = event.getUniqueSensorIdentifier();
			ctrl.dataChanged();
		});

		dataex2.execute(() -> {
			temp = event.getTemperature();
			pressure = event.getPressure();
			rev = event.getRevolutions();
			date = event.getDate();
			typecode = event.getSensorTypeCode();
			id = event.getUniqueSensorIdentifier();
			tableCtrl.dataChangedTable();
		});

	}

}
