package Consumer;


public class Run {

	public static void main(String[] args) {
		InitSensor1 sensor1 = new InitSensor1();
		InitSensor2 sensor2 = new InitSensor2();

		sensor1.startMeasure();
		sensor2.startMeasure();
		
		sensor1.listen();
		sensor2.listen();
		
		try {
			Thread.sleep(10000);
			sensor1.stopMeasure();
			sensor2.stopMeasure();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
