package net.yscs.android.stromundspannung;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class OhmschesGesetzSpannung extends Fragment {

	private EditText spannungStromText, spannungWiderstandText, ergSpannung;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.calculate_spannung, container,
				false);
		spannungStromText = (EditText) view.findViewById(R.id.ergSpannungStrom);
		spannungWiderstandText = (EditText) view
				.findViewById(R.id.ergSpannungWiderstand);
		ergSpannung = (EditText) view.findViewById(R.id.ergebnisSpannung);

		Button berechnenSpannungButton = (Button) view
				.findViewById(R.id.berechnenSpannung);
		berechnenSpannungButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (spannungStromText.getText().length() > 0
						&& spannungWiderstandText.getText().length() > 0) {
					String strom = spannungStromText.getText().toString();
					String widerstand = spannungWiderstandText.getText()
							.toString();

					spannungStromText.setText(Calculations
							.validateStringInput(strom) + " A");
					spannungWiderstandText.setText(Calculations
							.validateStringInput(widerstand) + " Ohm");

					ergSpannung.setText(String.valueOf(Calculations
							.clacSpannung(strom, widerstand)) + " V");
				}
			}

		});

		Button loeschen = (Button) view.findViewById(R.id.del2);
		loeschen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				clearUIFields();
			}

		});
		return view;
	}

	private void clearUIFields() {
		spannungStromText.setText("");
		spannungWiderstandText.setText("");
		ergSpannung.setText("");
	}
}
