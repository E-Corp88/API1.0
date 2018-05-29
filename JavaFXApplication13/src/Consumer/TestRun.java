package Consumer;


public class TestRun {

	 public static void main(String[] args) {
		 InitSensor1 sensor1 = new InitSensor1();
		 
		 sensor1.startMeasure();
		 sensor1.listen();
	}
}
