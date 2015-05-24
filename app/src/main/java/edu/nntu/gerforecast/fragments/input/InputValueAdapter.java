package edu.nntu.gerforecast.fragments.input;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.nntu.gerforecast.MainActivity;
import edu.nntu.gerforecast.R;
import edu.nntu.gerforecast.math.data.InputValues;

public class InputValueAdapter extends ArrayAdapter<InputField> {

    private final MainActivity mainActivity;
    private final List<InputField> values;

    public InputValueAdapter(MainActivity mainActivity, List<InputField> values) {
        super(mainActivity, R.layout.fragment_input_value, values);
        this.mainActivity = mainActivity;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.element_input_value_number, parent, false);
        InputField value = values.get(position);

        TextView textView = (TextView) rowView.findViewById(R.id.rowLabel);
        textView.setText(value.getName());

        EditText editText = (EditText) rowView.findViewById(R.id.inputField);
        editText.setInputType(value.getInputType());
        editText.setEnabled(value.isEnabled());
        editText.setText(value.getValue().toString());

        return rowView;
    }
}
