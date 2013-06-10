package net.yscs.android.stromundspannung.model;

public class Koeffizient {

	private String name;
	private double kelvin;

	public Koeffizient(String name, double kelvin) {
		super();
		this.name = name;
		this.kelvin = kelvin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getKelvin() {
		return kelvin;
	}

	public void setKelvin(double kelvin) {
		this.kelvin = kelvin;
	}

}
