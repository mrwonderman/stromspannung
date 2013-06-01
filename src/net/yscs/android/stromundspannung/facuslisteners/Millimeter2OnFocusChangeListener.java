package net.yscs.android.stromundspannung.facuslisteners;

import net.yscs.android.stromundspannung.Calculations;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

public class Millimeter2OnFocusChangeListener implements OnFocusChangeListener {

	private final EditText editText;

	public Millimeter2OnFocusChangeListener(EditText editText) {
		super();
		this.editText = editText;
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (hasFocus) {
			editText.setText(Calculations.validateStringInput(editText
					.getText().toString()));
		} else {
			if (editText.getText().length() > 0) {
				editText.setText(editText.getText() + " mm²");
			}
		}
	}

}
