package edu.nntu.gerforecast.fragments;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Collections;

import edu.nntu.gerforecast.MainActivity;
import edu.nntu.gerforecast.R;
import edu.nntu.gerforecast.math.data.OutputValues;


public class ResultsFragment extends MainActivity.PlaceholderFragment implements MainActivity.OutputValuesChangeListener {

    private MainActivity mainActivity = null;

    public static ResultsFragment newInstance(int sectionNumber) {
        ResultsFragment fragment = new ResultsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ResultsFragment() { /* Required empty public constructor */ }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);
        LineChart financeDynamic = (LineChart) view.findViewById(R.id.chartFinanceDynamic);
        setUpChart(financeDynamic);
        LineChart financeProfile = (LineChart) view.findViewById(R.id.chartFinanceProfile);
        setUpChart(financeProfile);

        updateCharts(financeDynamic, financeProfile, mainActivity.getOutputValues());

        TableLayout table = (TableLayout) view.findViewById(R.id.resultTable);
        updateTable(table, mainActivity.getOutputValues());

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

        TableLayout table = (TableLayout) mainActivity.findViewById(R.id.resultTable);
        updateTable(table, outputValues);
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

    private void updateTable(TableLayout table, OutputValues outputValues) {
        if (outputValues == null) { return; }
        for (int i = 1; i <= outputValues.yearsNumber; i++) {
            TextView npv = getCell(table, 0, i);
            npv.setText(getTextForTable(i, outputValues.currentOperationsAndInvestments));
            TextView ros = getCell(table, 1, i);
            ros.setText(getTextForTable(i, outputValues.ros));
            TextView o = getCell(table, 2, i);
            o.setText(getTextForTable(i, outputValues.cashBalance));
            TextView cr = getCell(table, 3, i);
            cr.setText(getTextForTable(i, outputValues.currentRatio));
        }
    }

    private TextView getCell(TableLayout table, int row, int column) {
        String name = "result_" + (row + 1) + (column);
        Resources res = getResources();
        int id = res.getIdentifier(name, "id", mainActivity.getPackageName());
        return (TextView) table.findViewById(id);
    }

    private String getTextForTable(int index, double[] values) {
        return (index < values.length) ?
                String.format(Math.abs(values[index]) < 100 ? "%.3f" : "%.0f", values[index]) : "";
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mainActivity != null) {
            mainActivity.removeOutputValuesChangeListener(this);
        }
    }
}
