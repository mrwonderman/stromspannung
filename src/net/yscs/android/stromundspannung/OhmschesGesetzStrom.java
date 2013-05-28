package net.yscs.android.stromundspannung;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.CompoundButton.OnCheckedChangeListener;
import net.yscs.android.stromundspannung.R;

public class OhmschesGesetzStrom extends Fragment {

	private EditText stromSpannungText, stromWiderstandText, ergStrom;
	private CheckBox mACalculator;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.calculate_strom, container, false);

		stromSpannungText = (EditText) view.findViewById(R.id.ergStromSpannung);
		stromWiderstandText = (EditText) view
				.findViewById(R.id.ergStromWiderstand);
		ergStrom = (EditText) view.findViewById(R.id.ergebnisStrom);

		mACalculator = (CheckBox) view.findViewById(R.id.mAConverter);
		mACalculator.setEnabled(false);
		mACalculator.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (ergStrom.length() > 0) {
					String strom = ergStrom.getText().toString();

					String validateStringInput = Calculations
							.validateStringInput(strom);
					if (!validateStringInput.equals("")) {
						double parseDouble = Double
								.parseDouble(validateStringInput);
						if (isChecked) {
							ergStrom.setText(String.valueOf(parseDouble * 1000)
									+ " mA");
						} else {
							ergStrom.setText(String.valueOf(parseDouble / 1000)
									+ " A");
						}
					}
				}
			}
		});

		Button berechnenStromButton = (Button) view
				.findViewById(R.id.berechnenStrom);
		berechnenStromButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (stromSpannungText.getText().length() > 0
						&& stromWiderstandText.getText().length() > 0) {
					String spannung = stromSpannungText.getText().toString();
					String widerstand = stromWiderstandText.getText()
							.toString();

					stromSpannungText.setText(Calculations
							.validateStringInput(spannung) + " V");
					stromWiderstandText.setText(Calculations
							.validateStringInput(widerstand) + " Ohm");
					ergStrom.setText(String.valueOf(Calculations.calcStrom(
							spannung, widerstand)) + " A");
					mACalculator.setEnabled(true);

				}
			}
		});

		Button loeschen = (Button) view.findViewById(R.id.del1);
		loeschen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				clearUIFields();
			}

		});
		return view;
	}

	private void clearUIFields() {
		stromSpannungText.setText("");
		stromWiderstandText.setText("");
		ergStrom.setText("");
		mACalculator.setChecked(false);
		mACalculator.setEnabled(false);
	}
}
