package edu.nntu.gerforecast.fragments.input;

import android.text.InputType;

public abstract class DecimalInputField extends InputField<Double> {

    public DecimalInputField(String name) {
        super(name);
    }

    @Override
    public int getInputType() {
        return InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER;
    }
}
