package edu.nntu.gerforecast.fragments;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.nntu.gerforecast.fragments.input.DecimalInputField;
import edu.nntu.gerforecast.fragments.input.InputField;
import edu.nntu.gerforecast.fragments.input.InputValueAdapter;
import edu.nntu.gerforecast.MainActivity;
import edu.nntu.gerforecast.R;
import edu.nntu.gerforecast.fragments.input.IntegerInputField;
import edu.nntu.gerforecast.math.data.InputValues;

import static edu.nntu.gerforecast.math.data.InputValues.*;


public class InputValueFragment extends MainActivity.PlaceholderFragment {

    public static InputValueFragment newInstance(int sectionNumber) {
        InputValueFragment fragment = new InputValueFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_input_value, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onStart() {
        super.onStart();

        MainActivity activity = (MainActivity) getActivity();
        List<InputField> values = wrapInputValues(activity.getInputValue());

        InputValueAdapter adapter = new InputValueAdapter(activity, values);
        ListView listView = (ListView) getActivity().findViewById(R.id.inputListValuesView);
        listView.setAdapter(adapter);
    }

    private List<InputField> wrapInputValues(final InputValues input) {
        List<InputField> result = new ArrayList<>();
        result.addAll(Arrays.asList(
                new DecimalInputField("Первоначальная стоимость оборудования") {
                    public Double getValue() { return input.getInitialEquipmentCost(); }
                    public void setValue(Double value) { input.setInitialEquipmentCost(value); }
                },
                new DecimalInputField("Коэфф. реализации оборудования") {
                    public Double getValue() { return input.getEquipmentSalesRatio(); }
                    public void setValue(Double value) { input.setEquipmentSalesRatio(value); }
                },
                new IntegerInputField("Кол-во проданных изделий в год") {
                    public Integer getValue() { return input.getProductsSoldPerYears(); }
                    public void setValue(Integer value) { input.setProductsSoldPerYears(value); }
                },
                new DecimalInputField("Ставка налога на прибыль") {
                    public Double getValue() { return input.getIncomeTaxRate(); }
                    public void setValue(Double value) { input.setIncomeTaxRate(value); }
                },
                new DecimalInputField("Норма дисконта") {
                    public Double getValue() { return input.getDiscountRate(); }
                    public void setValue(Double value) { input.setDiscountRate(value); }
                },
                new DecimalInputField("Процент по кредиту") {
                    public Double getValue() { return input.getInterestOnLoan(); }
                    public void setValue(Double value) { input.setInterestOnLoan(value); }
                },
                new IntegerInputField("Кол-во лет", false) {
                    public Integer getValue() { return input.getYearsNumber(); }
                    public void setValue(Integer value) { }
                },
                new DecimalInputField("Погашение кредита (первый год)") {
                    public Double getValue() { return input.getRepaymentOfCredit(1); }
                    public void setValue(Double value) { input.setRepaymentOfCredit(value, 1); }
                },
                new DecimalInputField("Погашение кредита (второй год)") {
                    public Double getValue() { return input.getRepaymentOfCredit(2); }
                    public void setValue(Double value) { input.setRepaymentOfCredit(value, 2); }
                },
                new DecimalInputField("Погашение кредита (третий год)") {
                    public Double getValue() { return input.getRepaymentOfCredit(3); }
                    public void setValue(Double value) { input.setRepaymentOfCredit(value, 3); }
                },
                new DecimalInputField("Погашение кредита (четвертый год)") {
                    public Double getValue() { return input.getRepaymentOfCredit(4); }
                    public void setValue(Double value) { input.setRepaymentOfCredit(value, 4); }
                },
                new DecimalInputField("Погашение кредита (пятый год)") {
                    public Double getValue() { return input.getRepaymentOfCredit(5); }
                    public void setValue(Double value) { input.setRepaymentOfCredit(value, 5); }
                },
                new DecimalInputField("Цена продаж за единицу") {
                    public Double getValue() { return input.getProductCost(); }
                    public void setValue(Double value) { input.setProductCost(value); }
                },
                new DecimalInputField("Цена материалов за единицу") {
                    public Double getValue() { return input.getProductMaterialCost(); }
                    public void setValue(Double value) { input.setProductMaterialCost(value); }
                },
                new DecimalInputField("Расходы на оплату труда") {
                    public Double getValue() { return input.getLaborCosts(); }
                    public void setValue(Double value) { input.setLaborCosts(value); }
                },
                new DecimalInputField("Общепроизводственные расходы") {
                    public Double getValue() { return input.getGeneralProductionExpenses(); }
                    public void setValue(Double value) { input.setGeneralProductionExpenses(value); }
                },
                new DecimalInputField("Управленческие расходы") {
                    public Double getValue() { return input.getManagementExpenses(); }
                    public void setValue(Double value) { input.setManagementExpenses(value); }
                },
                new DecimalInputField("Расходы на рекламу и сбыт") {
                    public Double getValue() { return input.getAdsExpenses(); }
                    public void setValue(Double value) { input.setAdsExpenses(value); }
                },
                new DecimalInputField("Ожидаемый темп инфляции по №12") {
                    public Double getValue() { return input.getExpectedRateOfInflation_1(); }
                    public void setValue(Double value) { input.setExpectedRateOfInflation_1(value); }
                },
                new DecimalInputField("Ожидаемый темп инфляции по №13") {
                    public Double getValue() { return input.getExpectedRateOfInflation_2(); }
                    public void setValue(Double value) { input.setExpectedRateOfInflation_2(value); }
                },
                new DecimalInputField("Ожидаемый темп инфляции по №14") {
                    public Double getValue() { return input.getExpectedRateOfInflation_3(); }
                    public void setValue(Double value) { input.setExpectedRateOfInflation_3(value); }
                },
                new DecimalInputField("Ожидаемый темп инфляции по №15") {
                    public Double getValue() { return input.getExpectedRateOfInflation_4(); }
                    public void setValue(Double value) { input.setExpectedRateOfInflation_4(value); }
                },
                new DecimalInputField("Ожидаемый темп инфляции по №16") {
                    public Double getValue() { return input.getExpectedRateOfInflation_5(); }
                    public void setValue(Double value) { input.setExpectedRateOfInflation_5(value); }
                },
                new DecimalInputField("Ожидаемый темп инфляции по №17") {
                    public Double getValue() { return input.getExpectedRateOfInflation_6(); }
                    public void setValue(Double value) { input.setExpectedRateOfInflation_6(value); }
                },
                new DecimalInputField("Коэфф.оборачиваемости по продажам") {
                    public Double getValue() { return input.getSalariesTurnoverRatio(); }
                    public void setValue(Double value) { input.setSalariesTurnoverRatio(value); }
                },
                new DecimalInputField("Коэфф.оборачиваемости по запасам") {
                    public Double getValue() { return input.getStocksTurnoverRatio(); }
                    public void setValue(Double value) { input.setStocksTurnoverRatio(value); }
                },
                new DecimalInputField("Коэфф.оборачиваемости по поставкам") {
                    public Double getValue() { return input.getSuppliesTurnoverRatio(); }
                    public void setValue(Double value) { input.setSuppliesTurnoverRatio(value); }
                },
                new DecimalInputField("Коэфф.оборачиваемости по оплате труда") {
                    public Double getValue() { return input.getSalariesTurnoverRatio(); }
                    public void setValue(Double value) { input.setSalariesTurnoverRatio(value); }
                }
        ));
        return result;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
