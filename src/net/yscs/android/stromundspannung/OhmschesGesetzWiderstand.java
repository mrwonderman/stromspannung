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

public class OhmschesGesetzWiderstand extends Fragment {

	private EditText widerstandSpannungText, widerstandStromText,
			ergWiderstand;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.calculate_widerstand, container,
				false);

		widerstandSpannungText = (EditText) view
				.findViewById(R.id.ergWiderstandSpannung);
		widerstandStromText = (EditText) view
				.findViewById(R.id.ergWiderstandStrom);
		ergWiderstand = (EditText) view.findViewById(R.id.ergebnisWiderstand);

		Button berechnen = (Button) view.findViewById(R.id.berechnenWiderstand);
		berechnen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (widerstandSpannungText.getText().length() > 0
						&& widerstandStromText.getText().length() > 0) {
					String spannung = widerstandSpannungText.getText()
							.toString();
					String strom = widerstandStromText.getText().toString();

					widerstandSpannungText.setText(Calculations
							.validateStringInput(spannung) + " V");
					widerstandStromText.setText(Calculations
							.validateStringInput(strom) + " A");

					ergWiderstand.setText(String.valueOf(Calculations
							.calcWiderstand(spannung, strom)) + "  Ohm");

				}
			}
		});

		Button loeschen = (Button) view.findViewById(R.id.del3);
		loeschen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				clearUIFields();
			}

		});
		return view;
	}

	private void clearUIFields() {
		widerstandSpannungText.setText("");
		widerstandStromText.setText("");
		ergWiderstand.setText("");
	}
}
