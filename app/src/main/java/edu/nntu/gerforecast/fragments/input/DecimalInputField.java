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

    public void setStringValue(String str) {
        double value = (str == null || str.isEmpty()) ? 0 : Double.parseDouble(str);
        setValue(value);
    }
}
