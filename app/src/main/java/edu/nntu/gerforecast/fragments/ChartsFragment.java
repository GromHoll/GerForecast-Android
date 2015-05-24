package edu.nntu.gerforecast.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Collections;

import edu.nntu.gerforecast.MainActivity;
import edu.nntu.gerforecast.R;
import edu.nntu.gerforecast.math.data.OutputValues;


public class ChartsFragment extends MainActivity.PlaceholderFragment implements MainActivity.OutputValuesChangeListener {

    private MainActivity mainActivity = null;

    public static ChartsFragment newInstance(int sectionNumber) {
        ChartsFragment fragment = new ChartsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ChartsFragment() { /* Required empty public constructor */ }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_charts, container, false);
        LineChart financeDynamic = (LineChart) view.findViewById(R.id.chartFinanceDynamic);
        setUpChart(financeDynamic);
        LineChart financeProfile = (LineChart) view.findViewById(R.id.chartFinanceProfile);
        setUpChart(financeProfile);

        updateCharts(financeDynamic, financeProfile, mainActivity.getOutputValues());
        return view;
    }

    private void setUpChart(LineChart chart) {
        chart.setDescription("");
        chart.setDrawGridBackground(false);
        chart.setNoDataText("Введите данные и нажмите \"рассчитать\"");
        chart.setHighlightEnabled(true);
        chart.setTouchEnabled(true);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity) activity;
        mainActivity.addOutputValuesChangeListener(this);
    }

    @Override
    public void onOutputValuesChanges(OutputValues outputValues) {
        LineChart financeDynamic = (LineChart) mainActivity.findViewById(R.id.chartFinanceDynamic);
        LineChart financeProfile = (LineChart) mainActivity.findViewById(R.id.chartFinanceProfile);
        updateCharts(financeDynamic, financeProfile, outputValues);
    }

    private void updateCharts(LineChart financeDynamic, LineChart financeProfile, OutputValues outputValues) {
        if (outputValues == null) { return; }
        updateChart(financeDynamic, outputValues.cashBalance, "Финансовый профиль");
        updateChart(financeProfile, outputValues.currentOperationsAndInvestments, "Динамика остатка денежных средств");
    }

    private void updateChart(LineChart chart, double[] values, String name) {
        if (chart != null) {
            LineData data = createDataSet(values, name);
            chart.setData(data);
            chart.invalidate();
        }
    }

    private LineData createDataSet(double[] values, String name) {
        ArrayList<String> xVals = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            xVals.add((i) + "");
        }

        ArrayList<Entry> yVals = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            yVals.add(new Entry((float) values[i], i));
        }

        LineDataSet set = new LineDataSet(yVals, name);
        set.setLineWidth(3f);
        set.setCircleSize(5f);
        set.setDrawCircleHole(true);
        set.setValueTextSize(10);

        return new LineData(xVals, Collections.singletonList(set));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mainActivity != null) {
            mainActivity.removeOutputValuesChangeListener(this);
        }
    }
}
