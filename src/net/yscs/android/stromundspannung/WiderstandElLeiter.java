package net.yscs.android.stromundspannung;

import java.util.ArrayList;

import net.yscs.android.stromundspannung.focuslisteners.MeterOnFocusChangeListener;
import net.yscs.android.stromundspannung.focuslisteners.Millimeter2OnFocusChangeListener;
import net.yscs.android.stromundspannung.focuslisteners.OhmMillimeter2OnFocusChangeListener;
import net.yscs.android.stromundspannung.model.Werkstoff;
import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import com.beardedhen.androidbootstrap.BootstrapButton;

public class WiderstandElLeiter extends Fragment implements
		StructuredUiFragment {

	private EditText laengeText, querschnittText, resultText, spezwiderText;
	private Button mal2;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.elwiderstand, container, false);

		final Spinner spinner = (Spinner) view
				.findViewById(R.id.elwiderspinner);

		laengeText = (EditText) view.findViewById(R.id.elwiderlaenge);
		querschnittText = (EditText) view.findViewById(R.id.elwiderquer);
		resultText = (EditText) view.findViewById(R.id.elwidererg);
		spezwiderText = (EditText) view.findViewById(R.id.elwiderspez);
		spezwiderText
				.setOnFocusChangeListener(new OhmMillimeter2OnFocusChangeListener(
						spezwiderText));

		MainActivity.setTypeFaceForViewGroup(
				(ViewGroup) laengeText.getRootView(), getActivity());

		mal2 = (Button) view.findViewById(R.id.mal2);
		mal2.setEnabled(false);
		laengeText.setOnFocusChangeListener(new MeterOnFocusChangeListener(
				laengeText));
		TextWatcher mal2Watcher = new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (count > 0) {
					mal2.setEnabled(true);
				} else {
					mal2.setEnabled(false);
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// nothing to do here =)
			}

			@Override
			public void afterTextChanged(Editable s) {
				// still nothing.. \^_^/
			}
		};
		laengeText.addTextChangedListener(mal2Watcher);

		querschnittText
				.setOnFocusChangeListener(new Millimeter2OnFocusChangeListener(
						querschnittText));

		final ArrayList<Werkstoff> werkstoffe = new ArrayList<Werkstoff>();
		werkstoffe.add(new Werkstoff(getString(R.string.aluminium), 0.029));
		werkstoffe.add(new Werkstoff(getString(R.string.bronze), 0.0185));
		werkstoffe.add(new Werkstoff(getString(R.string.chromnickel), 1.1));
		werkstoffe.add(new Werkstoff(getString(R.string.konstantan), 0.5));
		werkstoffe.add(new Werkstoff(getString(R.string.kupfer), 0.0175));
		werkstoffe.add(new Werkstoff(getString(R.string.silber), 0.0169));

		ArrayList<String> dropdownWerkstoffe = new ArrayList<String>();
		for (Werkstoff werkstoff : werkstoffe) {
			dropdownWerkstoffe.add(werkstoff.getWerkstoff());
		}

		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				spezwiderText.setText(String.valueOf(werkstoffe.get(arg2)
						.getWiderstand()) + getString(R.string._ohm_mm_m));
				calculateAndDisplay();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// nothing to do here =)
			}
		});

		mal2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (laengeText.getText().length() > 0) {
					String result = String.valueOf(Calculations.mal2(laengeText
							.getText().toString()));
					if (!laengeText.isFocused()) {
						laengeText.setText(result + getString(R.string._m));
					} else {
						laengeText.setText(result);
					}
					calculateAndDisplay();
				}
			}
		});

		Button querschnittBerechnen = (Button) view
				.findViewById(R.id.durchmesser);
		querschnittBerechnen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				LayoutInflater inflater = LayoutInflater.from(getActivity());
				final View yourCustomView = inflater.inflate(
						R.layout.querschnitt_dialog, null);
				final EditText editText = (EditText) yourCustomView
						.findViewById(R.id.radius_eingabe);
				final AlertDialog dialog = new AlertDialog.Builder(
						getActivity())
						.setTitle(getString(R.string.radius_eingeben))
						.setView(yourCustomView).create();
				final Button berechnen = (Button) yourCustomView
						.findViewById(R.id.berechnenDialog);

				if (querschnittText.getText().length() > 0) {
					editText.setText(String.valueOf(Calculations
							.calcRadius(querschnittText.getText().toString())));
				}
				berechnen.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						String result = String.valueOf(Calculations
								.calcQuerschnitt(editText.getText().toString()));
						if (!querschnittText.isFocused()) {
							querschnittText.setText(result
									+ getString(R.string._mm_));
						} else {
							querschnittText.setText(result);
						}
						dialog.dismiss();
						calculateAndDisplay();
					}
				});
				Button button = (Button) yourCustomView
						.findViewById(R.id.abbrechen_dialog);
				button.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
				dialog.show();
			}
		});

		BootstrapButton berechnen = (BootstrapButton) view
				.findViewById(R.id.elwider_berechnen);
		berechnen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				calculateAndDisplay();
			}
		});

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, dropdownWerkstoffe);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		BootstrapButton loeschen = (BootstrapButton) view
				.findViewById(R.id.elwider_del);
		loeschen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				clearUiFields();
			}
		});
		return view;
	}

	@Override
	public void clearUiFields() {
		laengeText.setText("");
		querschnittText.setText("");
		resultText.setText("");
	}

	@Override
	public void calculateAndDisplay() {
		if (laengeText.length() > 0 && querschnittText.length() > 0
				&& spezwiderText.length() > 0) {
			String laenge = laengeText.getText().toString();
			String querschnitt = querschnittText.getText().toString();
			String widerstand = spezwiderText.getText().toString();
			spezwiderText.setText(Calculations.validateStringInput(widerstand)
					+ getString(R.string._ohm_mm_m));
			resultText.setText(Calculations.calcElWiderstand(widerstand,
					laenge, querschnitt) + getString(R.string._ohm));
		}
	}
}
