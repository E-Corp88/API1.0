package de.hft.wiinf.SS18.ECorp.Consumer;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import de.hft.wiinf.SS18.ECorp.Controller.FXMLDocumentController;
import de.hft.wiinf.SS18.ECorp.Table.FXMLTableController;
import de.hft.wiinf.cebarround.CeBarRoundDataSensor;
import de.hft.wiinf.cebarround.CeBarRoundObserver;
import de.hft.wiinf.cebarround.SensorEvent;
import de.hft.wiinf.cebarround.SensorRegister;
import java.util.logging.Logger;

public class InitSensor1 implements CeBarRoundObserver<SensorEvent> {
    private Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private Executor dataex = Executors.newSingleThreadExecutor();

    SensorRegister app = new CeBarRoundDataSensor();

    public double temp = 0;
    public double pressure = 0;
    public int rev = 0;
    public Date date;
    public String typecode = "";
    public long id = 0;
    public FXMLDocumentController ctrl;
    public FXMLTableController tableCtrl;

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

    public void getVerbosity() {
        System.out.println(app.getVebosity());
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
            try{
            tableCtrl.dataChangedTable();
            }catch(Exception e){
               log.info("dataChangedTable isn´t working");
            }
        });

    }

}
