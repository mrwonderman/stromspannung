package net.yscs.android.stromundspannung;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import net.yscs.android.stromundspannung.R;

public class OhmschesGesetzSpannung extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.calculate_spannung, container,
				false);
		final EditText spannungStromText = (EditText) view
				.findViewById(R.id.ergSpannungStrom);
		final EditText spannungWiderstandText = (EditText) view
				.findViewById(R.id.ergSpannungWiderstand);

		final EditText ergSpannung = (EditText) view
				.findViewById(R.id.ergebnisSpannung);

		Button berechnenSpannungButton = (Button) view
				.findViewById(R.id.berechnenSpannung);
		berechnenSpannungButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (spannungStromText.getText().length() > 0
						&& spannungWiderstandText.getText().length() > 0) {
					double strom = Double.valueOf(spannungStromText.getText()
							.toString());
					double widerstand = Double.valueOf(spannungWiderstandText
							.getText().toString());
					spannungStromText.setText(spannungStromText.getText()
							+ " A");
					spannungWiderstandText.setText(spannungWiderstandText
							.getText() + " Ohm");

					ergSpannung.setText(String.valueOf(strom * widerstand)
							+ " V");

				}

			}
		});

		Button delete2 = (Button) view.findViewById(R.id.del2);
		delete2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				spannungStromText.setText("");
				spannungWiderstandText.setText("");
				ergSpannung.setText("");
			}
		});
		return view;
	}

}
