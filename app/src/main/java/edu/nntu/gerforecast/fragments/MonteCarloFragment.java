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
import edu.nntu.gerforecast.math.data.ElasticityOutput;
import edu.nntu.gerforecast.math.data.OutputValues;


public class MonteCarloFragment extends MainActivity.PlaceholderFragment {

    private MainActivity mainActivity = null;

    public static MonteCarloFragment newInstance(int sectionNumber) {
        MonteCarloFragment fragment = new MonteCarloFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public MonteCarloFragment() { /* Required empty public constructor */ }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monte_carlo, container, false);


//        TableLayout table = (TableLayout) view.findViewById(R.id.elasticityTable);
//        updateTable(table, mainActivity.getOutputValues(), mainActivity.getElasticityOutputValues());

        return view;
    }

    private void setUpChart(LineChart chart) {
        chart.setDescription("");
        chart.setDrawGridBackground(false);
        chart.setNoDataText("Введите данные и нажмите \"рассчитать\"");
        chart.setHighlightEnabled(true);
        chart.setTouchEnabled(false);
        chart.setDragEnabled(false);
        chart.setScaleEnabled(false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity) activity;
    }


    private void updateTable(TableLayout table, OutputValues output, ElasticityOutput elasticityOutput) {
        if (elasticityOutput == null || output == null) { return; }

        int latestIndex = output.currentOperationsAndInvestments.length - 1;
        setTableValue(elasticityOutput.getQ(),  output, 1, latestIndex, table);
        setTableValue(elasticityOutput.getK(),  output, 2, latestIndex, table);
        setTableValue(elasticityOutput.getKs(), output, 3, latestIndex, table);
        setTableValue(elasticityOutput.getF(),  output, 4, latestIndex, table);
        setTableValue(elasticityOutput.getL(),  output, 5, latestIndex, table);
    }

    private void setTableValue(OutputValues custom, OutputValues origin, int row, int index, TableLayout table) {
//        TextView npvText = getCell(table, row, 1);
//        double npv = getPercents(custom.currentOperationsAndInvestments[index], origin.currentOperationsAndInvestments[index]);
//        npvText.setText(getText(npv));
//
//        TextView oText = getCell(table, row, 2);
//        double o = getPercents(custom.cashBalance[index], origin.cashBalance[index]);
//        oText.setText(getText(o));
    }

    private TextView getCell(TableLayout table, int row, int column) {
        String name = "elasticity_" + row + column;
        Resources res = getResources();
        int id = res.getIdentifier(name, "id", mainActivity.getPackageName());
        return (TextView) table.findViewById(id);
    }

    private String getText(double value) {
        return String.format(value < 100 ? "%.3f" : "%.0f", value);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
