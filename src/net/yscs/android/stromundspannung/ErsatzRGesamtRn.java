package net.yscs.android.stromundspannung;

import java.util.ArrayList;
import java.util.List;
import net.yscs.android.stromundspannung.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ErsatzRGesamtRn extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.ersatz_rn, container, false);

		final EditText result3 = (EditText) view.findViewById(R.id.ewider3);

		final TextView rns = (TextView) view.findViewById(R.id.rnns);
		final EditText rn = (EditText) view.findViewById(R.id.addRn);
		Button addRnButton = (Button) view.findViewById(R.id.addRnButton);
		final List<Double> doubles = new ArrayList<Double>();
		addRnButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (rn.length() > 0) {
					doubles.add(Double.parseDouble(rn.getText().toString()));
					String s = "";
					for (Double double1 : doubles) {
						s = s + String.valueOf(double1) + " , ";
					}
					rns.setText(s);
					rn.setText("");
				}
			}
		});

		Button berechnen3 = (Button) view.findViewById(R.id.berechnen3);
		berechnen3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (doubles.size() > 0) {
					List<Double> ds = new ArrayList<Double>();
					for (Double double1 : doubles) {
						ds.add(1 / double1);
					}
					Double dd = 0.0;
					for (Double dk : ds) {
						dd += dk;
					}
					result3.setText(String.valueOf(1 / dd) + " Ohm");
				}
			}
		});
		Button del6 = (Button) view.findViewById(R.id.del6);
		del6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				doubles.clear();
				rns.setText("noch keine Widerstände hinzugefügt.");
				result3.setText("");
			}
		});
		return view;
	}

}
