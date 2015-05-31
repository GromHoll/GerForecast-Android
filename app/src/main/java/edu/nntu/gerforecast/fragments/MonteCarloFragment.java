package edu.nntu.gerforecast.fragments;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.nntu.gerforecast.MainActivity;
import edu.nntu.gerforecast.R;
import edu.nntu.gerforecast.math.data.InputValues;
import edu.nntu.gerforecast.math.data.OutputValues;
import edu.nntu.gerforecast.math.scenario.MainScenario;


public class MonteCarloFragment extends MainActivity.PlaceholderFragment {

    public static final int DEFAULT_OUTPUTS_NUMBER = 20;

    public static abstract class ValueChanger implements TextWatcher {
        @Override
        public void afterTextChanged(Editable s) {}
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s != null && !s.toString().isEmpty()) {
                double value = Double.parseDouble(s.toString());
                saveValue(value);
            }
        }
        public abstract void saveValue(double value);
    }

    public interface Getter<T> {
        T get(Map.Entry<InputValues, OutputValues> result);
    }

    private MainActivity mainActivity = null;

    private double productsSoldPerYears = 0;
    private double initialEquipmentCost = 0;
    private double productCost = 0;
    private double productMaterialCost = 0;
    private double salesTurnoverRatio = 0;

    private double tableValues[][] = new double[7][3];

    private Map<InputValues, OutputValues> outputValues = new HashMap<>();

    private Getter<Double> productsSoldPerYearsGetter = new Getter<Double>() {
        public Double get(Map.Entry<InputValues, OutputValues> result) {
            return (double) result.getKey().getProductsSoldPerYears();
        }
    };
    private Getter<Double> productCosGetter = new Getter<Double>() {
        public Double get(Map.Entry<InputValues, OutputValues> result) {
            return result.getKey().getProductCost();
        }
    };
    private Getter<Double> productMaterialCostGetter = new Getter<Double>() {
        public Double get(Map.Entry<InputValues, OutputValues> result) {
            return result.getKey().getProductMaterialCost();
        }
    };
    private Getter<Double> salesTurnoverRatioGetter = new Getter<Double>() {
        public Double get(Map.Entry<InputValues, OutputValues> result) {
            return result.getKey().getSalesTurnoverRatio();
        }
    };
    private Getter<Double> initialEquipmentCostGetter = new Getter<Double>() {
        public Double get(Map.Entry<InputValues, OutputValues> result) {
            return result.getKey().getInitialEquipmentCost();
        }
    };
    private Getter<Double> npvGetter = new Getter<Double>() {
        public Double get(Map.Entry<InputValues, OutputValues> result) {
            int size = result.getValue().currentOperationsAndInvestments.length;
            return result.getValue().currentOperationsAndInvestments[size - 1];
        }
    };
    private Getter<Double> oGetter = new Getter<Double>() {
        public Double get(Map.Entry<InputValues, OutputValues> result) {
            int size = result.getValue().cashBalance.length;
            return result.getValue().cashBalance[size - 1];
        }
    };

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

        Button calculateButton = (Button) view.findViewById(R.id.recalculateMonte);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recalculate();
            }
        });

        TableLayout outTable = (TableLayout) view.findViewById(R.id.tableMonte);
        setDataToTable(outTable);

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
            public void saveValue(double value) {
                initialEquipmentCost = value;
            }
        });
        productCostET.addTextChangedListener(new ValueChanger() {
            @Override
            public void saveValue(double value) { productCost = value; }
        });
        productMaterialCostET.addTextChangedListener(new ValueChanger() {
            @Override
            public void saveValue(double value) {
                productMaterialCost = value;
            }
        });
        salesTurnoverRatioET.addTextChangedListener(new ValueChanger() {
            @Override
            public void saveValue(double value) {
                salesTurnoverRatio = value;
            }
        });
    }

    private void recalculate() {
        if (mainActivity.getOutputValues() == null) { return; }

        outputValues.clear();
        MainScenario scenario = mainActivity.getScenario();
        List<InputValues> inputs = generateInputs();
        for (InputValues input : inputs) {
            outputValues.put(input, scenario.calculate(input));
        }
        TableLayout table = (TableLayout) mainActivity.findViewById(R.id.tableMonte);
        updateOutputTable(table);
    }

    private List<InputValues> generateInputs() {
        List<InputValues> results = new ArrayList<>();
        try {
            for (int i = 0; i < DEFAULT_OUTPUTS_NUMBER; i++) {
                InputValues input = mainActivity.getInputValue().clone();
                double productsSoldPerYearsDelta = getRandomDelta(productsSoldPerYears);
                double initialEquipmentCostDelta = getRandomDelta(initialEquipmentCost);
                double productCostDelta = getRandomDelta(productCost);
                double productMaterialCostDelta = getRandomDelta(productMaterialCost);
                double salesTurnoverRatioDelta = getRandomDelta(salesTurnoverRatio);

                input.setProductsSoldPerYears((int) getValueWithDeviant(input.getProductsSoldPerYears(), productsSoldPerYearsDelta));
                input.setInitialEquipmentCost(getValueWithDeviant(input.getInitialEquipmentCost(), initialEquipmentCostDelta));
                input.setProductCost(getValueWithDeviant(input.getProductCost(), productCostDelta));
                input.setProductMaterialCost(getValueWithDeviant(input.getProductMaterialCost(), productMaterialCostDelta));
                input.setSalesTurnoverRatio(getValueWithDeviant(input.getSalesTurnoverRatio(), salesTurnoverRatioDelta));

                results.add(input);
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return results;
    }

    private double getRandomDelta(double deviant) {
        return (Math.random() - 0.5)*2*deviant;
    }

    private double getValueWithDeviant(double value, double deviant) {
        return value*(1 + deviant/100);
    }

    private void updateOutputTable(TableLayout table) {
        if (outputValues.isEmpty()) { return; }

        Map.Entry<InputValues, OutputValues> origin = getOrigin();

        tableValues[0][0] = median(outputValues, productsSoldPerYearsGetter);
        tableValues[1][0] = median(outputValues, productCosGetter);
        tableValues[2][0] = median(outputValues, productMaterialCostGetter);
        tableValues[3][0] = median(outputValues, salesTurnoverRatioGetter);
        tableValues[4][0] = median(outputValues, initialEquipmentCostGetter);
        tableValues[5][0] = median(outputValues, npvGetter);
        tableValues[6][0] = median(outputValues, oGetter);

        tableValues[0][1] = standardDeviant(outputValues, origin, productsSoldPerYearsGetter);
        tableValues[1][1] = standardDeviant(outputValues, origin, productCosGetter);
        tableValues[2][1] = standardDeviant(outputValues, origin, productMaterialCostGetter);
        tableValues[3][1] = standardDeviant(outputValues, origin, salesTurnoverRatioGetter);
        tableValues[4][1] = standardDeviant(outputValues, origin, initialEquipmentCostGetter);
        tableValues[5][1] = standardDeviant(outputValues, origin, npvGetter);
        tableValues[6][1] = standardDeviant(outputValues, origin, oGetter);

        for (int i = 0; i < 7; i++) {
            tableValues[i][2] = tableValues[i][1]/tableValues[i][0];
        }
        setDataToTable(table);
    }

    private Map.Entry<InputValues, OutputValues> getOrigin() {
        return new Map.Entry<InputValues, OutputValues>() {
            @Override
            public InputValues getKey() { return mainActivity.getInputValue(); }
            @Override
            public OutputValues getValue() { return mainActivity.getOutputValues(); }
            @Override
            public OutputValues setValue(OutputValues object) {
                throw new UnsupportedOperationException();
            }
        };
    }

    private double median(Map<InputValues, OutputValues> result, Getter<Double> getter) {
        double value = 0;
        for (Map.Entry<InputValues, OutputValues> output : result.entrySet()) {
            value += getter.get(output);
        }
        return value/DEFAULT_OUTPUTS_NUMBER;
    }

    private double standardDeviant(Map<InputValues, OutputValues> result,
                                   Map.Entry<InputValues, OutputValues> origin,
                                   Getter<Double> getter) {
        double value = 0;
        for (Map.Entry<InputValues, OutputValues> output : result.entrySet()) {
            value += Math.pow(getter.get(origin) - getter.get(output), 2);
        }
        return Math.sqrt(value/DEFAULT_OUTPUTS_NUMBER);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity) activity;
    }

    private void setDataToTable(TableLayout table) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 3; j++) {
                TextView view = getCell(table, i + 1, j + 1);
                String value = getText(tableValues[i][j]);
                view.setText(value);
            }
        }
    }

    private TextView getCell(TableLayout table, int row, int column) {
        String name = "outMonte_" + row + column;
        Resources res = getResources();
        int id = res.getIdentifier(name, "id", mainActivity.getPackageName());
        return (TextView) table.findViewById(id);
    }

    private String getText(double value) {
        return String.format(Math.abs(value) < 100 ? "%.3f" : "%.0f", value);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
