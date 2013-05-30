package net.yscs.android.stromundspannung;

import java.util.ArrayList;
import java.util.Collections;

import net.yscs.android.stromundspannung.WiderstandElLeiter.Werkstoff;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class WiderstandsaenderungTemp extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.temp_widerstandsaenderung,
				container, false);

		Spinner spinner = (Spinner) view.findViewById(R.id.spinner1);

		final EditText material = (EditText) view.findViewById(R.id.material);

		EditText anfangstemp = (EditText) view.findViewById(R.id.anfangs_temp);
		EditText anfangswiderstand = (EditText) view
				.findViewById(R.id.anfangs_widerstand);
		EditText betriebstemp = (EditText) view
				.findViewById(R.id.betriebs_temp);
		EditText betriebswiderstand = (EditText) view
				.findViewById(R.id.betriebs_widerstand);

		final ArrayList<Koeffizient> koeffiziente = new ArrayList<Koeffizient>();
		koeffiziente.add(new Koeffizient("Kupfer", 0.004));
		koeffiziente.add(new Koeffizient("Aluminium", 0.004));
		koeffiziente.add(new Koeffizient("Konstantan", 0.0001));
		koeffiziente.add(new Koeffizient("Blei", 0.0038));
		koeffiziente.add(new Koeffizient("Kohle", -0.0003));
		koeffiziente.add(new Koeffizient("Wolfram", 0.0051));

		ArrayList<String> dropdownKoeffiziente = new ArrayList<String>();
		for (Koeffizient koeffizient : koeffiziente) {
			dropdownKoeffiziente.add(koeffizient.getName());
		}
		Collections.sort(dropdownKoeffiziente);

		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				material.setText(koeffiziente.get(arg2).getKelvin() + " 1/K");
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, dropdownKoeffiziente);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		anfangstemp.setText("20¡C");
		return view;
	}

}

class Koeffizient {

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
