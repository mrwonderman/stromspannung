package net.yscs.android.stromundspannung;

import net.yscs.android.stromundspannung.facuslisteners.OhmOnFocusChangeListener;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ErsatzRTwo extends Fragment implements StructuredUiFragment {

	private EditText result, r1, r2;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.ersetz_rtwo, container, false);

		result = (EditText) view.findViewById(R.id.ewider);

		r1 = (EditText) view.findViewById(R.id.r1);
		r2 = (EditText) view.findViewById(R.id.r2);

		r1.setOnFocusChangeListener(new OhmOnFocusChangeListener(r1));
		r2.setOnFocusChangeListener(new OhmOnFocusChangeListener(r2));

		Button berechnen = (Button) view
				.findViewById(R.id.ersatzwiderstandberechen);
		berechnen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (r1.length() > 0 && r2.getText().length() > 0) {
					result.setText(String.valueOf(Calculations.calcR2(r1
							.getText().toString(), r2.getText().toString()))
							+ " Ohm");
				}
			}
		});

		Button loeschen = (Button) view.findViewById(R.id.del4);
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
		r1.setText("");
		r2.setText("");
		result.setText("");
	}

}