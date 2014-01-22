package net.yscs.android.stromundspannung;

import net.yscs.android.stromundspannung.focuslisteners.AmpereOnFocusChangeListener;
import net.yscs.android.stromundspannung.focuslisteners.OhmOnFocusChangeListener;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;

public class OhmschesGesetzSpannung extends Fragment implements
		StructuredUiFragment {

	private EditText spannungStromText, spannungWiderstandText, ergSpannung;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.calculate_spannung, container,
				false);
		spannungStromText = (EditText) view.findViewById(R.id.ergSpannungStrom);
		MainActivity.setTypeFaceForViewGroup(
				(ViewGroup) spannungStromText.getRootView(), getActivity());
		spannungStromText
				.setOnFocusChangeListener(new AmpereOnFocusChangeListener(
						spannungStromText));

		TextView t = (TextView) view.findViewById(R.id.textView7);
		t.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/Roboto-Light.ttf"));

		spannungWiderstandText = (EditText) view
				.findViewById(R.id.ergSpannungWiderstand);
		spannungWiderstandText
				.setOnFocusChangeListener(new OhmOnFocusChangeListener(
						spannungWiderstandText));
		ergSpannung = (EditText) view.findViewById(R.id.ergebnisSpannung);

		BootstrapButton berechnenSpannungBootstrapButton = (BootstrapButton) view
				.findViewById(R.id.berechnenSpannung);
		berechnenSpannungBootstrapButton
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						calculateAndDisplay();
					}

				});

		BootstrapButton loeschen = (BootstrapButton) view
				.findViewById(R.id.del2);
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
		spannungStromText.setText("");
		spannungWiderstandText.setText("");
		ergSpannung.setText("");

	}

	@Override
	public void calculateAndDisplay() {
		if (spannungStromText.getText().length() > 0
				&& spannungWiderstandText.getText().length() > 0) {
			String strom = spannungStromText.getText().toString();
			String widerstand = spannungWiderstandText.getText().toString();

			ergSpannung.setText(String.valueOf(Calculations.clacSpannung(strom,
					widerstand)) + getString(R.string._v));
		}
	}
}
