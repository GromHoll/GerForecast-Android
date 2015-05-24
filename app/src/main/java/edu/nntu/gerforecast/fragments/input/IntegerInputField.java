package edu.nntu.gerforecast.fragments.input;

import android.text.InputType;

public class IntegerInputField extends InputField<Integer> {

    public IntegerInputField(String name, int value) {
        super(name, value);
    }

    @Override
    public int getInputType() {
        return InputType.TYPE_CLASS_NUMBER;
    }
}
