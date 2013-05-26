package net.yscs.android.stromundspannung;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import net.yscs.android.stromundspannung.R;

public class ErsatzRGesamt extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.ersetz_rgesamt, container, false);

		final EditText result2 = (EditText) view.findViewById(R.id.ewider2);

		final EditText r11 = (EditText) view.findViewById(R.id.r11);
		final EditText r22 = (EditText) view.findViewById(R.id.r22);

		Button berechnen2 = (Button) view.findViewById(R.id.berechnen2);
		berechnen2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (r11.length() > 0 && r22.getText().length() > 0) {
					calcRGEsamt(r11.getText().toString(), r22.getText()
							.toString());
				}
			}

			private void calcRGEsamt(String string, String string2) {
				Double d1 = Double.parseDouble(string);
				Double d2 = Double.parseDouble(string2);

				double e = Math.pow(d1, -1);
				double f = Math.pow(d2, -1);
				double d = e + f;
				double er = Math.pow(d, -1);
				result2.setText(String.valueOf(er) + " Ohm");

			}
		});

		Button del5 = (Button) view.findViewById(R.id.del5);
		del5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				r11.setText("");
				r22.setText("");
				result2.setText("");
			}
		});
		return view;
	}

}
