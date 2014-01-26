package net.yscs.android.stromundspannung;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Calculations {

	private static final int ROUND = 3;

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
		return roundUp(er);
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
		return roundUp(1 / d);
	}

	public static double calcR2(String val1, String val2) {
		Double d1 = Double.parseDouble(validateStringInput(val1));
		Double d2 = Double.parseDouble(validateStringInput(val2));

		double e = Math.pow(d1, -1);
		double f = Math.pow(d2, -1);
		double d = e - f;
		double er = Math.pow(d, -1);
		return roundUp(er);
	}

	public static double clacSpannung(String val1, String val2) {
		Double d1 = Double.parseDouble(validateStringInput(val1));
		Double d2 = Double.parseDouble(validateStringInput(val2));
		return (d1 * d2);
	}

	public static String validateStringInput(String s) {
		return s.replaceAll("[^\\.0123456789]", "");
	}

	public static double calcStrom(String val1, String val2) {
		Double d1 = Double.parseDouble(validateStringInput(val1));
		Double d2 = Double.parseDouble(validateStringInput(val2));
		return (d1 / d2);
	}

	public static double calcWiderstand(String val1, String val2) {
		Double d1 = Double.parseDouble(validateStringInput(val1));
		Double d2 = Double.parseDouble(validateStringInput(val2));
		return (d1 / d2);
	}

	public static double calcElWiderstand(String val1, String val2, String val3) {
		Double d1 = Double.parseDouble(validateStringInput(val1));
		Double d2 = Double.parseDouble(validateStringInput(val2));
		Double d3 = Double.parseDouble(validateStringInput(val3));
		return roundUp((d1 * d2) / d3);
	}

	public static double calcBEtriebsWiderstand(String val1, String val2,
			String val3, String val4) {
		Double d1 = Double.parseDouble(validateStringInput(val1));
		Double d2 = Double.parseDouble(validateStringInput(val2));
		Double d3 = Double.parseDouble(validateStringInput(val3));
		Double d4 = Double.parseDouble(validateStringInput(val4));

		return roundUp(d2 + (d2 * d4 * (d3 - d1)));

	}

	public static double calcAnfangswiderstand(String val1, String val2,
			String val3, String val4) {
		Double d1 = Double.parseDouble(validateStringInput(val1));
		Double d2 = Double.parseDouble(validateStringInput(val2));
		Double d3 = Double.parseDouble(validateStringInput(val3));
		Double d4 = Double.parseDouble(validateStringInput(val4));

		return roundUp((d3 / (1 + d4 * (d2 - d1))));
	}

	public static double calcBetriebsTemperatur(String val1, String val2,
			String val3, String val4) {
		Double d1 = Double.parseDouble(validateStringInput(val1));
		Double d2 = Double.parseDouble(validateStringInput(val2));
		Double d3 = Double.parseDouble(validateStringInput(val3));
		Double d4 = Double.parseDouble(validateStringInput(val4));

		return roundUp((((d3 - d2) / (d2 * d4)) + d1));
	}

	public static double calcAnfangsTemperatur(String val1, String val2,
			String val3, String val4) {
		Double d1 = Double.parseDouble(validateStringInput(val1));
		Double d2 = Double.parseDouble(validateStringInput(val2));
		Double d3 = Double.parseDouble(validateStringInput(val3));
		Double d4 = Double.parseDouble(validateStringInput(val4));

		return roundUp(d2 - (d3 - d1) / (d1 * d4));
	}

	private static double roundUp(double d) {
		BigDecimal result = new BigDecimal(d);
		return result.setScale(ROUND, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static double mal2(String val1) {
		Double d1 = Double.parseDouble(validateStringInput(val1));
		return d1 * 2;
	}

	public static double calcQuerschnitt(String val1) {
		Double d1 = Double.parseDouble(validateStringInput(val1));
		return roundUp(Math.pow(d1, 2) * Math.PI, 4);
	}

	private static double roundUp(double d, int i) {
		BigDecimal result = new BigDecimal(d);
		return result.setScale(i, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static double calcRadius(String val1) {
		Double d1 = Double.parseDouble(validateStringInput(val1));
		return roundUp(Math.sqrt(d1 / Math.PI));
	}
}
