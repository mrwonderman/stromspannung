package net.yscs.android.stromundspannung.facuslisteners;

import net.yscs.android.stromundspannung.Calculations;
import net.yscs.android.stromundspannung.R;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

public class OhmOnFocusChangeListener implements OnFocusChangeListener {

	private final EditText editText;

	public OhmOnFocusChangeListener(EditText editText) {
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
				editText.setText(Calculations.validateStringInput(editText
						.getText().toString())
						+ v.getContext().getResources()
								.getString(R.string._ohm));
			}
		}
	}

}
