package net.yscs.android.stromundspannung;

import java.util.ArrayList;

import net.yscs.android.stromundspannung.facuslisteners.GradOnFocusChangeListener;
import net.yscs.android.stromundspannung.facuslisteners.KelvinOnFocusChangeListener;
import net.yscs.android.stromundspannung.facuslisteners.OhmOnFocusChangeListener;
import net.yscs.android.stromundspannung.model.Koeffizient;
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

public class WiderstandsaenderungTemp extends Fragment implements
		StructuredUiFragment {

	private EditText material, anfangstemp, anfangswiderstand, betriebstemp,
			betriebswiderstand;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.temp_widerstandsaenderung,
				container, false);

		Spinner spinner = (Spinner) view.findViewById(R.id.spinner1);
		material = (EditText) view.findViewById(R.id.material);
		material.setOnFocusChangeListener(new KelvinOnFocusChangeListener(
				material));
		anfangstemp = (EditText) view.findViewById(R.id.anfangs_temp);
		anfangstemp.setOnFocusChangeListener(new GradOnFocusChangeListener(
				anfangstemp));
		anfangswiderstand = (EditText) view
				.findViewById(R.id.anfangs_widerstand);
		anfangswiderstand
				.setOnFocusChangeListener(new OhmOnFocusChangeListener(
						anfangswiderstand));
		betriebstemp = (EditText) view.findViewById(R.id.betriebs_temp);
		betriebstemp.setOnFocusChangeListener(new GradOnFocusChangeListener(
				betriebstemp));
		betriebswiderstand = (EditText) view
				.findViewById(R.id.betriebs_widerstand);
		betriebswiderstand
				.setOnFocusChangeListener(new OhmOnFocusChangeListener(
						betriebswiderstand));

		final ArrayList<Koeffizient> koeffiziente = new ArrayList<Koeffizient>();
		koeffiziente.add(new Koeffizient(getString(R.string.aluminium), 0.004));
		koeffiziente.add(new Koeffizient(getString(R.string.blei), 0.0038));
		koeffiziente.add(new Koeffizient(getString(R.string.kupfer), 0.004));
		koeffiziente.add(new Koeffizient(getString(R.string.wolfram), 0.0051));

		ArrayList<String> dropdownKoeffiziente = new ArrayList<String>();
		for (Koeffizient koeffizient : koeffiziente) {
			dropdownKoeffiziente.add(koeffizient.getName());
		}

		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				material.setText(koeffiziente.get(arg2).getKelvin()
						+ getString(R.string._k));
				calculateAndDisplay();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// nothing to do here =)
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

		Button berechnen = (Button) view
				.findViewById(R.id.ersatzwiderstandberechen);
		berechnen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				calculateAndDisplay();
			}
		});

		Button loeschen = (Button) view.findViewById(R.id.del4);
		loeschen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				clearUiFields();
			}
		});
		anfangstemp.setText(getString(R.string._20_c));
		return view;
	}

	@Override
	public void clearUiFields() {
		anfangstemp.setText("");
		anfangswiderstand.setText("");
		betriebstemp.setText("");
		betriebswiderstand.setText("");
	}

	@Override
	public void calculateAndDisplay() {
		if ((anfangstemp.getText().length() > 0)
				&& (anfangswiderstand.getText().length() > 0)
				&& (betriebstemp.getText().length() > 0)) {
			betriebswiderstand.setText(Calculations.calcBEtriebsWiderstand(
					anfangstemp.getText().toString(), anfangswiderstand
							.getText().toString(), betriebstemp.getText()
							.toString(), material.getText().toString())
					+ getString(R.string._ohm));
		} else if ((anfangstemp.getText().length() > 0)
				&& (betriebstemp.getText().length() > 0)
				&& (betriebswiderstand.getText().length() > 0)) {
			anfangswiderstand.setText(Calculations.calcAnfangswiderstand(
					anfangstemp.getText().toString(), betriebstemp.getText()
							.toString(), betriebswiderstand.getText()
							.toString(), material.getText().toString())
					+ getString(R.string._ohm));
		} else if ((anfangstemp.getText().length() > 0)
				&& (anfangswiderstand.getText().length() > 0)
				&& (betriebswiderstand.getText().length() > 0)) {
			betriebstemp.setText(Calculations.calcBetriebsTemperatur(
					anfangstemp.getText().toString(), anfangswiderstand
							.getText().toString(), betriebswiderstand.getText()
							.toString(), material.getText().toString())
					+ getString(R.string._c));
		} else if ((anfangswiderstand.getText().length() > 0)
				&& (betriebstemp.getText().length() > 0)
				&& (betriebswiderstand.getText().length() > 0)) {
			anfangstemp.setText(Calculations.calcAnfangsTemperatur(
					anfangswiderstand.getText().toString(), betriebstemp
							.getText().toString(), betriebswiderstand.getText()
							.toString(), material.getText().toString())
					+ getString(R.string._c));
		}
	}
}
