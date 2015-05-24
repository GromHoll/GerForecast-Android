package edu.nntu.gerforecast.fragments;

import android.app.Activity;
import android.content.res.Resources;
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
import edu.nntu.gerforecast.math.data.InputValues;

import static edu.nntu.gerforecast.math.data.InputValues.*;


public class InputValueFragment extends MainActivity.PlaceholderFragment {

    private InputField[] values = new InputField[] {
            new DecimalInputField("Первоначальная стоимость оборудования", DEFAULT_INITIAL_EQUIPMENT_COST),
            new DecimalInputField("Коэфф. реализации оборудования", DEFAULT_EQUIPMENT_SALES_RATIO),
            new IntegerInputField("Кол-во проданных изделий в год", DEFAULT_PRODUCTS_SOLD_PER_YEARS),
            new DecimalInputField("Ставка налога на прибыль", DEFAULT_INCOME_TAX_RATE),
            new DecimalInputField("Норма дисконта", DEFAULT_DISCOUNT_RATE),
            new DecimalInputField("Процент по кредиту", DEFAULT_DISCOUNT_RATE),
            //new DecimalInputField("Кол-во лет"
            new DecimalInputField("Цена продаж за единицу", DEFAULT_PRODUCT_COST),
            new DecimalInputField("Цена материалов за единицу", DEFAULT_PRODUCT_MATERIAL_COST),
            new DecimalInputField("Расходы на оплату труда", DEFAULT_LABOR_COST),
            new DecimalInputField("Общепроизводственные расходы", DEFAULT_GENERAL_PRODUCTION_EXPENSES),
            new DecimalInputField("Управленческие расходы", DEFAULT_MANAGEMENT_EXPENSES),
            new DecimalInputField("Расходы на рекламу и сбыт", DEFAULT_ADS_EXPENSES),
            new DecimalInputField("Ожидаемый темп инфляции по №12", DEFAULT_EXPECTED_RATE_OF_INFLATION_1),
            new DecimalInputField("Ожидаемый темп инфляции по №13", DEFAULT_EXPECTED_RATE_OF_INFLATION_2),
            new DecimalInputField("Ожидаемый темп инфляции по №14", DEFAULT_EXPECTED_RATE_OF_INFLATION_3),
            new DecimalInputField("Ожидаемый темп инфляции по №15", DEFAULT_EXPECTED_RATE_OF_INFLATION_4),
            new DecimalInputField("Ожидаемый темп инфляции по №16", DEFAULT_EXPECTED_RATE_OF_INFLATION_5),
            new DecimalInputField("Ожидаемый темп инфляции по №17", DEFAULT_EXPECTED_RATE_OF_INFLATION_6),
            new DecimalInputField("Коэфф.оборачиваемости по продажам", DEFAULT_SALES_TURNOVER_RATIO),
            new DecimalInputField("Коэфф.оборачиваемости по запасам", DEFAULT_STOCKS_TURNOVER_RATIO),
            new DecimalInputField("Коэфф.оборачиваемости по поставкам", DEFAULT_SUPPLIES_TURNOVER_RATIO),
            new DecimalInputField("Коэфф.оборачиваемости по оплате труда", DEFAULT_SALARIES_TURNOVER_RATIO)
    };

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
        InputValueAdapter adapter = new InputValueAdapter(getActivity(), values);
        ListView listView = (ListView) getActivity().findViewById(R.id.inputListValuesView);
        listView.setAdapter(adapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
