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


public class ElasticityFragment extends MainActivity.PlaceholderFragment implements MainActivity.ElasticityOutputChangeListener {

    private MainActivity mainActivity = null;

    public static ElasticityFragment newInstance(int sectionNumber) {
        ElasticityFragment fragment = new ElasticityFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ElasticityFragment() { /* Required empty public constructor */ }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_elasticity, container, false);

        LineChart financeDynamicQ = (LineChart) view.findViewById(R.id.chartFinanceDynamic_Q);
        LineChart financeProfileQ = (LineChart) view.findViewById(R.id.chartFinanceProfile_Q);
        setUpCharts(financeDynamicQ, financeProfileQ);
        if (mainActivity.getElasticityOutputValues() != null) {
            updateCharts(financeDynamicQ, financeProfileQ, mainActivity.getElasticityOutputValues().getQ(), "(Q)");
        }

        LineChart financeDynamicK = (LineChart) view.findViewById(R.id.chartFinanceDynamic_K);
        LineChart financeProfileK = (LineChart) view.findViewById(R.id.chartFinanceProfile_K);
        setUpCharts(financeDynamicK, financeProfileK);
        if (mainActivity.getElasticityOutputValues() != null) {
            updateCharts(financeDynamicK, financeProfileK, mainActivity.getElasticityOutputValues().getK(), "(K)");
        }

        LineChart financeDynamicKS = (LineChart) view.findViewById(R.id.chartFinanceDynamic_KS);
        LineChart financeProfileKS = (LineChart) view.findViewById(R.id.chartFinanceProfile_KS);
        setUpCharts(financeDynamicKS, financeProfileKS);
        if (mainActivity.getElasticityOutputValues() != null) {
            updateCharts(financeDynamicKS, financeProfileKS, mainActivity.getElasticityOutputValues().getKs(), "(KS)");
        }

        LineChart financeDynamicF = (LineChart) view.findViewById(R.id.chartFinanceDynamic_F);
        LineChart financeProfileF = (LineChart) view.findViewById(R.id.chartFinanceProfile_F);
        setUpCharts(financeDynamicF, financeProfileF);
        if (mainActivity.getElasticityOutputValues() != null) {
            updateCharts(financeDynamicF, financeProfileF, mainActivity.getElasticityOutputValues().getF(), "(F)");
        }

        LineChart financeDynamicL = (LineChart) view.findViewById(R.id.chartFinanceDynamic_L);
        LineChart financeProfileL = (LineChart) view.findViewById(R.id.chartFinanceProfile_L);
        setUpCharts(financeDynamicL, financeProfileL);
        if (mainActivity.getElasticityOutputValues() != null) {
            updateCharts(financeDynamicL, financeProfileL, mainActivity.getElasticityOutputValues().getL(), "(L)");
        }

        TableLayout table = (TableLayout) view.findViewById(R.id.elasticityTable);
        updateTable(table, mainActivity.getOutputValues(), mainActivity.getElasticityOutputValues());

        return view;
    }

    private void setUpCharts(LineChart ... charts) {
        for (LineChart chart : charts) {
            setUpChart(chart);
        }
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
        mainActivity.addElasticityOutputChangeListener(this);
    }

    @Override
    public void onElasticityOutputChanges(OutputValues outputValues, ElasticityOutput elasticityOutput) {
        LineChart financeDynamicQ = (LineChart) mainActivity.findViewById(R.id.chartFinanceDynamic_Q);
        LineChart financeProfileQ = (LineChart) mainActivity.findViewById(R.id.chartFinanceProfile_Q);
        updateCharts(financeDynamicQ, financeProfileQ, elasticityOutput.getQ(), "(Q)");

        LineChart financeDynamicK = (LineChart) mainActivity.findViewById(R.id.chartFinanceDynamic_K);
        LineChart financeProfileK = (LineChart) mainActivity.findViewById(R.id.chartFinanceProfile_K);
        updateCharts(financeDynamicK, financeProfileK, elasticityOutput.getK(), "(K)");

        LineChart financeDynamicKS = (LineChart) mainActivity.findViewById(R.id.chartFinanceDynamic_KS);
        LineChart financeProfileKS = (LineChart) mainActivity.findViewById(R.id.chartFinanceProfile_KS);
        updateCharts(financeDynamicKS, financeProfileKS, elasticityOutput.getKs(), "(KS)");

        LineChart financeDynamicF = (LineChart) mainActivity.findViewById(R.id.chartFinanceDynamic_F);
        LineChart financeProfileF = (LineChart) mainActivity.findViewById(R.id.chartFinanceProfile_F);
        updateCharts(financeDynamicF, financeProfileF, elasticityOutput.getF(), "(F)");

        LineChart financeDynamicL = (LineChart) mainActivity.findViewById(R.id.chartFinanceDynamic_L);
        LineChart financeProfileL = (LineChart) mainActivity.findViewById(R.id.chartFinanceProfile_L);
        updateCharts(financeDynamicL, financeProfileL, elasticityOutput.getL(), "(L)");

        TableLayout table = (TableLayout) mainActivity.findViewById(R.id.elasticityTable);
        updateTable(table, outputValues, elasticityOutput);
    }

    private void updateCharts(LineChart financeDynamic, LineChart financeProfile, OutputValues outputValues, String suffix) {
        if (outputValues == null) { return; }
        updateChart(financeDynamic, outputValues.cashBalance, "Финансовый профиль " + suffix);
        updateChart(financeProfile, outputValues.currentOperationsAndInvestments, "Динамика остатка денежных средств " + suffix);
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

    private void updateTable(TableLayout table, OutputValues output, ElasticityOutput elasticityOutput) {
        if (elasticityOutput == null || output == null) { return; }

        int latestIndex = output.currentOperationsAndInvestments.length - 1;
        setTableValue(elasticityOutput.getQ(),  output, 1, latestIndex, table);
        setTableValue(elasticityOutput.getK(),  output, 2, latestIndex, table);
        setTableValue(elasticityOutput.getKs(), output, 3, latestIndex, table);
        setTableValue(elasticityOutput.getF(),  output, 4, latestIndex, table);
        setTableValue(elasticityOutput.getL(),  output, 5, latestIndex, table);
    }

    private double getPercents(double customValue, double originValue) {
        return ((customValue - originValue)/originValue)*100;
    }

    private void setTableValue(OutputValues custom, OutputValues origin, int row, int index, TableLayout table) {
        TextView npvText = getCell(table, row, 1);
        double npv = getPercents(custom.currentOperationsAndInvestments[index], origin.currentOperationsAndInvestments[index]);
        npvText.setText(getText(npv));

        TextView oText = getCell(table, row, 2);
        double o = getPercents(custom.cashBalance[index], origin.cashBalance[index]);
        oText.setText(getText(o));
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
        if (mainActivity != null) {
            mainActivity.removeElasticityOutputChangeListener(this);
        }
    }
}
