package edu.nntu.gerforecast.fragments.input;

import android.text.InputType;

public class DecimalInputField extends InputField<Double> {

    public DecimalInputField(String name, double value) {
        super(name, value);
    }

    @Override
    public int getInputType() {
        return InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER;
    }
}
