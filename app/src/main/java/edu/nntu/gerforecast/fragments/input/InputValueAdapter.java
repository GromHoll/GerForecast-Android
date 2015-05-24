package edu.nntu.gerforecast.fragments.input;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import edu.nntu.gerforecast.R;

// TODO Change String to POJO
public class InputValueAdapter extends ArrayAdapter<InputField> {

    private final Context context;
    private final InputField[] values;
    private final View[] rowViews;

    public InputValueAdapter(Context context, InputField[] values) {
        super(context, R.layout.fragment_input_value, values);
        this.context = context;
        this.values = values;
        this.rowViews = new View[values.length];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = rowViews[position];
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.element_input_value_number, parent, false);

            TextView textView = (TextView) rowView.findViewById(R.id.rowLabel);
            textView.setText(values[position].getName());

            EditText editText = (EditText) rowView.findViewById(R.id.inputField);
            editText.setInputType(values[position].getInputType());
            editText.setText(values[position].getValue().toString());

            rowViews[position] = rowView;
        }
        return rowView;
    }

}
