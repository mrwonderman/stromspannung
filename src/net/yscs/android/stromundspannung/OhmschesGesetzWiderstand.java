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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.calculate_widerstand, container,
				false);

		final EditText widerstandSpannungText = (EditText) view
				.findViewById(R.id.ergWiderstandSpannung);
		final EditText widerstandStromText = (EditText) view
				.findViewById(R.id.ergWiderstandStrom);

		final EditText ergWiderstand = (EditText) view
				.findViewById(R.id.ergebnisWiderstand);

		Button berechnenWiderstandButton = (Button) view
				.findViewById(R.id.berechnenWiderstand);
		berechnenWiderstandButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (widerstandSpannungText.getText().length() > 0
						&& widerstandStromText.getText().length() > 0) {
					double spannung = Double.valueOf(widerstandSpannungText
							.getText().toString());
					double strom = Double.valueOf(widerstandStromText.getText()
							.toString());

					widerstandSpannungText.setText(widerstandSpannungText
							.getText() + " V");
					widerstandStromText.setText(widerstandStromText.getText()
							+ " A");

					ergWiderstand.setText(String.valueOf(spannung / strom)
							+ "  Ohm");

				}
			}
		});

		Button delete3 = (Button) view.findViewById(R.id.del3);
		delete3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				widerstandSpannungText.setText("");
				widerstandStromText.setText("");
				ergWiderstand.setText("");
			}
		});
		return view;
	}
}
