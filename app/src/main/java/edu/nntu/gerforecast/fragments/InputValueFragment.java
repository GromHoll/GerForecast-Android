package edu.nntu.gerforecast.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import edu.nntu.gerforecast.fragments.input.DecimalInputField;
import edu.nntu.gerforecast.fragments.input.InputField;
import edu.nntu.gerforecast.fragments.input.InputValueAdapter;
import edu.nntu.gerforecast.MainActivity;
import edu.nntu.gerforecast.R;
import edu.nntu.gerforecast.fragments.input.IntegerInputField;


public class InputValueFragment extends MainActivity.PlaceholderFragment {

    public static InputValueFragment newInstance(int sectionNumber) {
        InputValueFragment fragment = new InputValueFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_input_value, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onStart() {
        super.onStart();
        InputField[] values = new InputField[] {new DecimalInputField("One more time"), new IntegerInputField("One more int time", 1)};
        InputValueAdapter adapter = new InputValueAdapter(getActivity(), values);
        ListView listView = (ListView) getActivity().findViewById(R.id.inputListValuesView);
        listView.setAdapter(adapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
