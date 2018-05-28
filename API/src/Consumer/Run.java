package Consumer;

import java.util.ArrayList;
import java.util.Date;

public class Run {

	static InitSensor1 sensor1 = new InitSensor1();

	static ArrayList<Integer> templist = new ArrayList<>();
	static ArrayList<Double> preslist = new ArrayList<>();
	static ArrayList<Integer> revlist = new ArrayList<>();
	static ArrayList<Date> datelist = new ArrayList<>();
	static ArrayList<String> stclist = new ArrayList<>();
	static ArrayList<Long> usilist = new ArrayList<>();

	public static void main(String[] args) {

		// InitSensor2 sensor2 = new InitSensor2();

		sensor1.startMeasure();
		// sensor2.startMeasure();

		sensor1.listen();
		// sensor2.listen();

		try {
			Thread.sleep(10000);
			sensor1.stopMeasure();
			// sensor2.stopMeasure();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addValues();
		printTemp();
		printPressure();
		printRev();
		printDate();

	}

	public static void addValues() {
		for (int x = 0; x < sensor1.events.size(); x++) {
			templist.add((int) sensor1.events.get(x).getTemperature());
			preslist.add((double) sensor1.events.get(x).getPressure());
			revlist.add((int) sensor1.events.get(x).getRevolutions());
			datelist.add(sensor1.events.get(x).getDate());
			stclist.add(sensor1.events.get(x).getSensorTypeCode());
			usilist.add(sensor1.events.get(x).getUniqueSensorIdentifier());
		}
	}

	public static void printTemp() {
		System.out.println("Temperatures:");
		for (int x = 0; x < templist.size(); x++) {
			System.out.println(templist.get(x));
		}
		System.out.println("");
	}

	public static void printRev() {
		System.out.println("Revolutions:");
		for (int x = 0; x < revlist.size(); x++) {
			System.out.println(revlist.get(x));
		}
		System.out.println("");

	}
	
	public static void printPressure() {
		System.out.println("Pressures:");
		for (int x = 0; x < preslist.size(); x++) {
			System.out.println(preslist.get(x));
		}
		System.out.println("");

	}
	
	public static void printDate() {
		System.out.println("Dates:");
		for (int x = 0; x < datelist.size(); x++) {
			System.out.println(datelist.get(x));
		}
		System.out.println("");

	}
	
	

}
