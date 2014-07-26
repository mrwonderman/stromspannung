package net.yscs.android.stromundspannung.model;

public class Werkstoff {
	String werkstoff;
	double widerstand;

	public Werkstoff(String werkstoff, double widerstand) {
		super();
		this.werkstoff = werkstoff;
		this.widerstand = widerstand;
	}

	public String getWerkstoff() {
		return werkstoff;
	}

	public void setWerkstoff(String werkstoff) {
		werkstoff = werkstoff;
	}

	public double getWiderstand() {
		return widerstand;
	}

	public void setWiderstand(double widerstand) {
		this.widerstand = widerstand;
	}

}
