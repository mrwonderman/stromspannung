package net.yscs.android.stromundspannung;

import java.util.ArrayList;

import net.yscs.android.stromundspannung.focuslisteners.GradOnFocusChangeListener;
import net.yscs.android.stromundspannung.focuslisteners.OhmOnFocusChangeListener;
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

public class WiderstandsaenderungTemp extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.temp_widerstandsaenderung,
				container, false);

		Spinner spinner = (Spinner) view.findViewById(R.id.spinner1);

		final EditText material = (EditText) view.findViewById(R.id.material);

		final EditText anfangstemp = (EditText) view
				.findViewById(R.id.anfangs_temp);
		anfangstemp.setOnFocusChangeListener(new GradOnFocusChangeListener(
				anfangstemp));

		final EditText anfangswiderstand = (EditText) view
				.findViewById(R.id.anfangs_widerstand);
		anfangswiderstand
				.setOnFocusChangeListener(new OhmOnFocusChangeListener(
						anfangswiderstand));

		final EditText betriebstemp = (EditText) view
				.findViewById(R.id.betriebs_temp);
		betriebstemp.setOnFocusChangeListener(new GradOnFocusChangeListener(
				betriebstemp));

		final EditText betriebswiderstand = (EditText) view
				.findViewById(R.id.betriebs_widerstand);
		betriebswiderstand
				.setOnFocusChangeListener(new OhmOnFocusChangeListener(
						betriebswiderstand));

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
		// Collections.sort(dropdownKoeffiziente);

		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				material.setText(koeffiziente.get(arg2).getKelvin() + " K");
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, dropdownKoeffiziente);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		Button del1 = (Button) view.findViewById(R.id.temp_del_1);
		Button del2 = (Button) view.findViewById(R.id.temp_del_2);
		Button del3 = (Button) view.findViewById(R.id.temp_del_3);
		Button del4 = (Button) view.findViewById(R.id.temp_del_4);

		del1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				anfangstemp.setText("");
			}
		});
		del2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				anfangswiderstand.setText("");
			}
		});
		del3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				betriebstemp.setText("");
			}
		});
		del4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				betriebswiderstand.setText("");
			}
		});

		Button button = (Button) view
				.findViewById(R.id.ersatzwiderstandberechen);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if ((anfangstemp.getText().length() > 0)
						&& (anfangswiderstand.getText().length() > 0)
						&& (betriebstemp.getText().length() > 0)) {
					betriebswiderstand.setText(Calculations
							.calcBEtriebsWiderstand(anfangstemp.getText()
									.toString(), anfangswiderstand.getText()
									.toString(), betriebstemp.getText()
									.toString(), material.getText().toString())
							+ " Ohm");
				} else if ((anfangstemp.getText().length() > 0)
						&& (betriebstemp.getText().length() > 0)
						&& (betriebswiderstand.getText().length() > 0)) {
					anfangswiderstand.setText(Calculations
							.calcAnfangswiderstand(anfangstemp.getText()
									.toString(), betriebstemp.getText()
									.toString(), betriebswiderstand.getText()
									.toString(), material.getText().toString())
							+ " Ohm");
				} else if ((anfangstemp.getText().length() > 0)
						&& (anfangswiderstand.getText().length() > 0)
						&& (betriebswiderstand.getText().length() > 0)) {
					betriebstemp.setText(Calculations.calcBetriebsTemperatur(
							anfangstemp.getText().toString(), anfangswiderstand
									.getText().toString(), betriebswiderstand
									.getText().toString(), material.getText()
									.toString())
							+ " ¡C");
				}
			}
		});
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
