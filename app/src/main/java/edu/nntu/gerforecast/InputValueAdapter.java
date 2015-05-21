package edu.nntu.gerforecast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

// TODO Change String to POJO
public class InputValueAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] values;

    public InputValueAdapter(Context context, String[] values) {
        super(context, R.layout.fragment_input_value, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.element_input_value_decimal, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.rowLabel);
        textView.setText(values[position]);
        return rowView;
    }

}
