package edu.nntu.gerforecast.fragments;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import edu.nntu.gerforecast.MainActivity;
import edu.nntu.gerforecast.R;
import edu.nntu.gerforecast.math.data.ElasticityOutput;
import edu.nntu.gerforecast.math.data.OutputValues;


public class MonteCarloFragment extends MainActivity.PlaceholderFragment {

    public static abstract class ValueChanger implements TextWatcher {
        @Override
        public void afterTextChanged(Editable s) {}
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s != null && !s.toString().isEmpty()) {
                double value = Double.parseDouble(s.toString());
            }
        }
        public abstract void saveValue(double value);
    }

    private MainActivity mainActivity = null;

    private double productsSoldPerYears = 0;
    private double initialEquipmentCost = 0;
    private double productCost = 0;
    private double productMaterialCost = 0;
    private double salesTurnoverRatio = 0;

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
        TableLayout table = (TableLayout) view.findViewById(R.id.tableInputMonte);
        setupTable(table);

//        TableLayout table = (TableLayout) view.findViewById(R.id.elasticityTable);
//        updateTable(table, mainActivity.getOutputValues(), mainActivity.getElasticityOutputValues());

        return view;
    }

    private void setupTable(TableLayout table) {
        EditText productsSoldPerYearsET = (EditText) table.findViewById(R.id.productsSoldPerYearsMonte);
        EditText initialEquipmentCostET = (EditText) table.findViewById(R.id.initialEquipmentCostMonte);
        EditText productCostET = (EditText) table.findViewById(R.id.productCostMonte);
        EditText productMaterialCostET = (EditText) table.findViewById(R.id.productMaterialCostMonte);
        EditText salesTurnoverRatioET = (EditText) table.findViewById(R.id.salesTurnoverRatioMonte);

        productsSoldPerYearsET.setText("" + productsSoldPerYears);
        initialEquipmentCostET.setText("" + initialEquipmentCost);
        productCostET.setText("" + productCost);
        productMaterialCostET.setText("" + productMaterialCost);
        salesTurnoverRatioET.setText("" + salesTurnoverRatio);

        productsSoldPerYearsET.addTextChangedListener(new ValueChanger() {
            @Override
            public void saveValue(double value) { productsSoldPerYears = value; }
        });
        initialEquipmentCostET.addTextChangedListener(new ValueChanger() {
            @Override
            public void saveValue(double value) { initialEquipmentCost = value; }
        });
        productCostET.addTextChangedListener(new ValueChanger() {
            @Override
            public void saveValue(double value) { productCost = value; }
        });
        productMaterialCostET.addTextChangedListener(new ValueChanger() {
            @Override
            public void saveValue(double value) { productMaterialCost = value; }
        });
        salesTurnoverRatioET.addTextChangedListener(new ValueChanger() {
            @Override
            public void saveValue(double value) { salesTurnoverRatio = value; }
        });
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
