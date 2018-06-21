package de.hft.wiinf.ss.ecorp.event;

import java.util.Date;

public class EventDTO {

	private double temp = 0;
	private double pressure = 0;
	private int rev = 0;
	private String date;
	private String typecode = "";
	private long id = 0;

	public EventDTO(double temp, double pressure, int rev, String date, String typecode, long id) {
		this.temp = temp;
		this.pressure = pressure;
		this.rev = rev;
		this.date = date;
		this.typecode = typecode;
		this.id = id;
	}

	public double getTemp() {
		return temp;
	}

	public double getPressure() {
		return pressure;
	}

	public int getRev() {
		return rev;
	}

	public String getDate() {
		return date;
	}

	public String getTypecode() {
		return typecode;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "EventDTO [temp=" + temp + ", pressure=" + pressure + ", rev=" + rev + ", date=" + date + ", typecode="
				+ typecode + ", id=" + id + "]";
	}

}
