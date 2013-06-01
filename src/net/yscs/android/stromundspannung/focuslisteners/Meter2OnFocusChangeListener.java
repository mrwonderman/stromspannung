package net.yscs.android.stromundspannung.focuslisteners;

import net.yscs.android.stromundspannung.Calculations;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

public class Meter2OnFocusChangeListener implements OnFocusChangeListener {

	private final EditText editText;

	public Meter2OnFocusChangeListener(EditText editText) {
		super();
		this.editText = editText;
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (hasFocus) {
			editText.setText(Calculations.validateStringInput(editText
					.getText().toString()));
		} else {
			editText.setText(editText.getText() + " m");
		}
	}

}
