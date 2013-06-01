package net.yscs.android.stromundspannung;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ErsatzRGesamtRn extends Fragment implements StructuredUiFragment {

	private EditText result, rn;
	private TextView rns;
	private Button addRn;
	private List<Double> listOfRs;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.ersatz_rn, container, false);

		listOfRs = new ArrayList<Double>();

		result = (EditText) view.findViewById(R.id.ewider3);
		rns = (TextView) view.findViewById(R.id.rnns);
		rn = (EditText) view.findViewById(R.id.addRn);
		addRn = (Button) view.findViewById(R.id.addRnButton);
		addRn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (rn.length() > 0) {
					listOfRs.add(Double.parseDouble(rn.getText().toString()));
					addRAndDisplay();
				}
			}

		});

		Button berechnen = (Button) view.findViewById(R.id.berechnen3);
		berechnen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				calculateAndDisplay();
			}
		});

		Button loeschen = (Button) view.findViewById(R.id.del6);
		loeschen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				clearUiFields();
			}
		});
		return view;
	}

	private void addRAndDisplay() {
		String tmpString = "";
		for (Double d : listOfRs) {
			tmpString = tmpString + String.valueOf(d) + " , ";
		}
		rns.setText(tmpString.substring(0, tmpString.length() - 3));
		rn.setText("");
	}

	@Override
	public void clearUiFields() {
		listOfRs.clear();
		rns.setText("noch keine WiderstŠnde hinzugefŸgt.");
		result.setText("");

	}

	@Override
	public void calculateAndDisplay() {
		if (listOfRs.size() > 0) {
			result.setText(String.valueOf(Calculations
					.calcRErsatzBeiRn(listOfRs)) + " Ohm");
		}
	}
}
