package Consumer;


import java.util.ArrayList;

import de.hft.wiinf.cebarround.CeBarRoundDataSensor;
import de.hft.wiinf.cebarround.CeBarRoundObserver;
import de.hft.wiinf.cebarround.SensorEvent;
import de.hft.wiinf.cebarround.SensorRegister;

final class InitSensor1 {

	SensorRegister app = new CeBarRoundDataSensor();
	 public ArrayList <SensorEvent> events = new ArrayList <>();

	public void listen() {
		final CeBarRoundObserver<SensorEvent> listener = currentevent -> 
		events.add(currentevent);

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
