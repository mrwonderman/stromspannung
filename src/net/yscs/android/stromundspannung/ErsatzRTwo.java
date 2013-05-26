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

public class ErsatzRTwo extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.ersetz_rtwo, container, false);

		final EditText result = (EditText) view.findViewById(R.id.ewider);

		final EditText r1 = (EditText) view.findViewById(R.id.r1);
		final EditText r2 = (EditText) view.findViewById(R.id.r2);

		Button berechnen = (Button) view
				.findViewById(R.id.ersatzwiderstandberechen);
		berechnen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (r1.length() > 0 && r2.getText().length() > 0) {
					rewriteResult(r1.getText().toString(), r2.getText()
							.toString());
				}
			}

			private void rewriteResult(String string, String string2) {
				Double d1 = Double.parseDouble(string);
				Double d2 = Double.parseDouble(string2);

				double e = Math.pow(d1, -1);
				double f = Math.pow(d2, -1);
				double d = e - f;
				double er = Math.pow(d, -1);
				result.setText(String.valueOf(er) + " Ohm");

			}
		});

		Button del4 = (Button) view.findViewById(R.id.del4);
		del4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				r1.setText("");
				r2.setText("");
				result.setText("");
			}
		});
		return view;
	}

}
