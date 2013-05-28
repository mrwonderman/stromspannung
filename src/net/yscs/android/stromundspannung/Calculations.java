package net.yscs.android.stromundspannung;

import java.util.ArrayList;
import java.util.List;

public class Calculations {

	/**
	 * Calculates RGesamt from two values. It doesn't care if you have the
	 * params like <i>25 OHm</i>, it uses RegEx to only get the digits.
	 * 
	 * @param val1
	 * @param val2
	 * @return
	 */
	public static double calcRGEsamt(String val1, String val2) {
		Double d1 = Double.parseDouble(validateStringInput(val1));
		Double d2 = Double.parseDouble(validateStringInput(val2));

		double e = Math.pow(d1, -1);
		double f = Math.pow(d2, -1);
		double d = e + f;
		double er = Math.pow(d, -1);
		return er;
	}

	public static double calcRErsatzBeiRn(List<Double> listOfRs) {
		List<Double> tmpdoubles = new ArrayList<Double>();
		for (Double d1 : listOfRs) {
			tmpdoubles.add(1 / d1);
		}
		Double d = 0.0;
		for (Double d2 : tmpdoubles) {
			d += d2;
		}
		return 1 / d;
	}

	public static double calcR2(String string, String string2) {
		Double d1 = Double.parseDouble(string);
		Double d2 = Double.parseDouble(string2);

		double e = Math.pow(d1, -1);
		double f = Math.pow(d2, -1);
		double d = e - f;
		double er = Math.pow(d, -1);
		return er;
	}

	public static double clacSpannung(String val1, String val2) {
		Double d1 = Double.parseDouble(validateStringInput(val1));
		Double d2 = Double.parseDouble(validateStringInput(val2));
		return d1 * d2;
	}

	public static String validateStringInput(String s) {
		return s.replaceAll("[^\\.0123456789]", "");
	}

	public static double calcStrom(String val1, String val2) {
		Double d1 = Double.parseDouble(validateStringInput(val1));
		Double d2 = Double.parseDouble(validateStringInput(val2));
		return d1 / d2;
	}

	public static double calcWiderstand(String val1, String val2) {
		Double d1 = Double.parseDouble(validateStringInput(val1));
		Double d2 = Double.parseDouble(validateStringInput(val2));
		return d1 / d2;
	}

	public static double calcElWiderstand(String val1, String val2, String val3) {
		Double d1 = Double.parseDouble(validateStringInput(val1));
		Double d2 = Double.parseDouble(validateStringInput(val2));
		Double d3 = Double.parseDouble(validateStringInput(val3));
		return (d1 + d2) / d3;
	}
}
