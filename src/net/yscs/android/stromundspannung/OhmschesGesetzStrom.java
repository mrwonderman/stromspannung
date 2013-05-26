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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.calculate_strom, container, false);

		final EditText stromSpannungText = (EditText) view
				.findViewById(R.id.ergStromSpannung);
		final EditText stromWiderstandText = (EditText) view
				.findViewById(R.id.ergStromWiderstand);

		final EditText ergStrom = (EditText) view
				.findViewById(R.id.ergebnisStrom);

		final CheckBox mACalculator = (CheckBox) view
				.findViewById(R.id.mAConverter);

		mACalculator.setEnabled(false);
		mACalculator.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (ergStrom.length() > 0) {
					if (isChecked) {
						String string = ergStrom.getText().toString();
						if (string.contains("A")) {
							string = string.replace("A", "");
						}
						double parseDouble = Double.parseDouble(string);
						ergStrom.setText(String.valueOf(parseDouble * 1000)
								+ " mA");
					} else {

						String string = ergStrom.getText().toString();
						if (string.contains("mA")) {
							string = string.replace("mA", "");
						}
						double parseDouble = Double.parseDouble(string);
						ergStrom.setText(String.valueOf(parseDouble / 1000)
								+ " A");
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
					double spannung = Double.valueOf(stromSpannungText
							.getText().toString());
					double widerstand = Double.valueOf(stromWiderstandText
							.getText().toString());
					stromSpannungText.setText(stromSpannungText.getText()
							+ " V");
					stromWiderstandText.setText(stromWiderstandText.getText()
							+ " Ohm");
					ergStrom.setText(String.valueOf(spannung / widerstand)
							+ " A");
					mACalculator.setEnabled(true);

				}
			}
		});

		Button delete1 = (Button) view.findViewById(R.id.del1);
		delete1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				stromSpannungText.setText("");
				stromWiderstandText.setText("");
				ergStrom.setText("");
				mACalculator.setChecked(false);
				mACalculator.setEnabled(false);
			}
		});
		return view;
	}
}
