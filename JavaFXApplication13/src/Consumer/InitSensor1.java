package Consumer;

import de.hft.wiinf.cebarround.CeBarRoundDataSensor;
import de.hft.wiinf.cebarround.CeBarRoundObserver;
import de.hft.wiinf.cebarround.SensorEvent;
import de.hft.wiinf.cebarround.SensorRegister;

public class InitSensor1 {

	SensorRegister app = new CeBarRoundDataSensor();

	public void listen() {
		final CeBarRoundObserver<SensorEvent> listener = event -> {
			event.getTemperature();
			event.getPressure();
			event.getRevolutions();
			event.getDate();
			event.getSensorTypeCode();
			event.getUniqueSensorIdentifier();
		};
		
		app.addListener(listener);
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

}
