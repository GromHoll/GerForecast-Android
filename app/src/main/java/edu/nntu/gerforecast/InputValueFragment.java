package edu.nntu.gerforecast;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;


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
        String[] values = new String[] {"Test", "OPOP", "1", "One more time"};
        InputValueAdapter adapter = new InputValueAdapter(getActivity(), values);
        ListView listView = (ListView) getActivity().findViewById(R.id.inputListValuesView);
        listView.setAdapter(adapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
