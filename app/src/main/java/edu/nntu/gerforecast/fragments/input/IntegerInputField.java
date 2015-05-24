package edu.nntu.gerforecast.fragments.input;

import android.text.InputType;

public abstract class IntegerInputField extends InputField<Integer> {

    public IntegerInputField(String name) {
        this(name, true);
    }

    public IntegerInputField(String name, boolean isEnabled) {
        super(name, isEnabled);
    }

    @Override
    public int getInputType() {
        return InputType.TYPE_CLASS_NUMBER;
    }

    public void setStringValue(String str) {
        int value = (str == null || str.isEmpty()) ? 0 : Integer.parseInt(str);
        setValue(value);
    }
}
