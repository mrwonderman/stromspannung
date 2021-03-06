package net.yscs.android.stromundspannung;

import net.yscs.android.stromundspannung.focuslisteners.OhmOnFocusChangeListener;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;

import com.beardedhen.androidbootstrap.BootstrapButton;

public class ErsatzRGesamt extends Fragment implements StructuredUiFragment {

	private EditText result, r1, r2;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.ersatz_rgesamt, container, false);

		result = (EditText) view.findViewById(R.id.ewider2);
		r1 = (EditText) view.findViewById(R.id.r11);
		r2 = (EditText) view.findViewById(R.id.r22);
		MainActivity.setTypeFaceForViewGroup((ViewGroup) result.getRootView(),
				getActivity());
		r1.setOnFocusChangeListener(new OhmOnFocusChangeListener(r1));
		r2.setOnFocusChangeListener(new OhmOnFocusChangeListener(r2));

		BootstrapButton berechnen = (BootstrapButton) view
				.findViewById(R.id.berechnen2);
		berechnen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				calculateAndDisplay();
			}
		});

		BootstrapButton leoschen = (BootstrapButton) view
				.findViewById(R.id.del5);
		leoschen.setOnClickListener(new OnClickListener() {

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

	@Override
	public void calculateAndDisplay() {
		if (r1.length() > 0 && r2.length() > 0) {
			result.setText(String.valueOf(Calculations.calcRGEsamt(r1.getText()
					.toString(), r2.getText().toString()))
					+ getString(R.string._ohm));
		}
	}
}
