package Consumer;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import de.hft.wiinf.cebarround.CeBarRoundDataSensor;
import de.hft.wiinf.cebarround.CeBarRoundObserver;
import de.hft.wiinf.cebarround.SensorEvent;
import de.hft.wiinf.cebarround.SensorRegister;
import javafxapplication13.FXMLDocumentController;

public class InitSensor1 implements CeBarRoundObserver<SensorEvent> {

	private Executor dataex = Executors.newSingleThreadExecutor();

	SensorRegister app = new CeBarRoundDataSensor();

	public double temp = 0;
	public double pressure = 0;
	public int rev = 0;
	public Date date;
	public String typecode = "";
	public long id = 0;
	public FXMLDocumentController ctrl;

	public InitSensor1(FXMLDocumentController ctrl) {
		this.ctrl = ctrl;
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
		});
		
	}

}
