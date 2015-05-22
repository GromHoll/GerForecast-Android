package edu.nntu.gerforecast.fragments.input;

public abstract class InputField<F> {

    private final String name;
    private F value;

    protected InputField(String name, F value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public F getValue() {
        return value;
    }

    public void setValue(F value) {
        this.value = value;
    }

    public abstract int getInputType();
}
