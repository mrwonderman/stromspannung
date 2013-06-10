package net.yscs.android.stromundspannung;

import net.yscs.android.stromundspannung.focuslisteners.OhmOnFocusChangeListener;
import net.yscs.android.stromundspannung.focuslisteners.VoltOnFocusChangeListener;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class OhmschesGesetzStrom extends Fragment implements
		StructuredUiFragment {

	private EditText stromSpannungText, stromWiderstandText, ergStrom;
	private CheckBox mACalculator;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.calculate_strom, container, false);

		stromSpannungText = (EditText) view.findViewById(R.id.ergStromSpannung);
		stromSpannungText
				.setOnFocusChangeListener(new VoltOnFocusChangeListener(
						stromSpannungText));
		stromWiderstandText = (EditText) view
				.findViewById(R.id.ergStromWiderstand);
		stromWiderstandText
				.setOnFocusChangeListener(new OhmOnFocusChangeListener(
						stromWiderstandText));
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
									+ getString(R.string._ma));
						} else {
							ergStrom.setText(String.valueOf(parseDouble / 1000)
									+ getString(R.string._a));
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
				calculateAndDisplay();
			}
		});

		Button loeschen = (Button) view.findViewById(R.id.del1);
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
		stromSpannungText.setText("");
		stromWiderstandText.setText("");
		ergStrom.setText("");
		mACalculator.setChecked(false);
		mACalculator.setEnabled(false);

	}

	@Override
	public void calculateAndDisplay() {
		if (stromSpannungText.getText().length() > 0
				&& stromWiderstandText.getText().length() > 0) {
			String spannung = stromSpannungText.getText().toString();
			String widerstand = stromWiderstandText.getText().toString();

			ergStrom.setText(String.valueOf(Calculations.calcStrom(spannung,
					widerstand)) + getString(R.string._a));
			mACalculator.setEnabled(true);

		}
	}
}
