package edu.nntu.gerforecast.fragments.input;

public abstract class InputField<F> {

    private final String name;
    private final boolean isEnabled;

    protected InputField(String name) {
        this(name, true);
    }

    protected InputField(String name, boolean isEnabled) {
        this.name = name;
        this.isEnabled = isEnabled;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public String getName() {
        return name;
    }

    public abstract F getValue();

    public abstract void setStringValue(String str);

    public abstract void setValue(F value);

    public abstract int getInputType();
}
