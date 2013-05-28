package net.yscs.android.stromundspannung;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class WiderstandElLeiter extends Fragment {

	private EditText laengeText, querschnittText, resultText, spezwiderText;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.elwiderstand, container, false);

		final Spinner spinner = (Spinner) view.findViewById(R.id.spinner1);

		laengeText = (EditText) view.findViewById(R.id.erlaenge);
		querschnittText = (EditText) view.findViewById(R.id.elquerschnitt);
		resultText = (EditText) view.findViewById(R.id.ergelwider);
		spezwiderText = (EditText) view.findViewById(R.id.spezwider);

		final ArrayList<Werkstoff> werkstoffe = new ArrayList<Werkstoff>();
		werkstoffe.add(new Werkstoff("Silber", 0.0169));
		werkstoffe.add(new Werkstoff("Kupfer", 0.0175));
		werkstoffe.add(new Werkstoff("Aluminium", 0.029));
		werkstoffe.add(new Werkstoff("Konstantan", 0.5));
		werkstoffe.add(new Werkstoff("Chromnickel", 1.1));
		werkstoffe.add(new Werkstoff("Bronze", 0.0185));

		ArrayList<String> dropdownWerkstoffe = new ArrayList<String>();
		for (Werkstoff werkstoff : werkstoffe) {
			dropdownWerkstoffe.add(werkstoff.getWerkstoff());
		}

		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				spezwiderText.setText(String.valueOf(werkstoffe.get(arg2)
						.getWiderstand()) + " Ohm mm/m");
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

		Button berechnen = (Button) view.findViewById(R.id.calcelleiter);
		berechnen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (laengeText.length() > 0 && querschnittText.length() > 0
						&& spezwiderText.length() > 0) {
					String laenge = laengeText.getText().toString();
					String querschnitt = querschnittText.getText().toString();
					String widerstand = spezwiderText.getText().toString();
					spezwiderText.setText(Calculations
							.validateStringInput(widerstand) + " Ohm mm/m");
					resultText.setText(Calculations.calcElWiderstand(
							widerstand, laenge, querschnitt) + " Ohm");
				}
			}
		});

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, dropdownWerkstoffe);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		Button loeschen = (Button) view.findViewById(R.id.del7);
		loeschen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				clearUIFields();
			}

		});

		return view;
	}

	private void clearUIFields() {
		laengeText.setText("");
		querschnittText.setText("");
		resultText.setText("");
		spezwiderText.setText("");
	}

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
}
