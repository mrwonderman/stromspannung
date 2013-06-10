package net.yscs.android.stromundspannung.model;

public class Werkstoff {
	String Werkstoff;
	double widerstand;

	public Werkstoff(String werkstoff, double widerstand) {
		super();
		Werkstoff = werkstoff;
		this.widerstand = widerstand;
	}

	public String getWerkstoff() {
		return Werkstoff;
	}

	public void setWerkstoff(String werkstoff) {
		Werkstoff = werkstoff;
	}

	public double getWiderstand() {
		return widerstand;
	}

	public void setWiderstand(double widerstand) {
		this.widerstand = widerstand;
	}

}
