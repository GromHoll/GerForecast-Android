package edu.nntu.gerforecast.math.data;

import java.util.Arrays;

public class InputValues implements Cloneable {

    public static final double DEFAULT_INITIAL_EQUIPMENT_COST = 10000;
    public static final double DEFAULT_EQUIPMENT_SALES_RATIO = 0.05;
    public static final double DEFAULT_INCOME_TAX_RATE = 0.2;
    public static final double DEFAULT_DISCOUNT_RATE = 0.13;
    public static final double DEFAULT_INTEREST_ON_LOAN = 0.18;
    public static final double DEFAULT_PRODUCT_COST = 48;
    public static final double DEFAULT_PRODUCT_MATERIAL_COST = 33;
    public static final double DEFAULT_LABOR_COST = 45000;
    public static final double DEFAULT_GENERAL_PRODUCTION_EXPENSES = 21000;
    public static final double DEFAULT_MANAGEMENT_EXPENSES = 10000;
    public static final double DEFAULT_ADS_EXPENSES = 12000;
    public static final double DEFAULT_EXPECTED_RATE_OF_INFLATION_1 = 0.10;
    public static final double DEFAULT_EXPECTED_RATE_OF_INFLATION_2 = 0.11;
    public static final double DEFAULT_EXPECTED_RATE_OF_INFLATION_3 = 0.09;
    public static final double DEFAULT_EXPECTED_RATE_OF_INFLATION_4 = 0.10;
    public static final double DEFAULT_EXPECTED_RATE_OF_INFLATION_5 = 0.10;
    public static final double DEFAULT_EXPECTED_RATE_OF_INFLATION_6 = 0.10;
    public static final double DEFAULT_SALES_TURNOVER_RATIO = 8;
    public static final double DEFAULT_STOCKS_TURNOVER_RATIO = 12;
    public static final double DEFAULT_SUPPLIES_TURNOVER_RATIO = 6;
    public static final double DEFAULT_SALARIES_TURNOVER_RATIO = 24;

    public static final int DEFAULT_PRODUCTS_SOLD_PER_YEARS = 12000;
    public static final int DEFAULT_YEARS_NUMBER = 5;

    /** F - Первоначальная стоимость оборудования */
    private double initialEquipmentCost = DEFAULT_INITIAL_EQUIPMENT_COST;

    /** KP - Коэффициент реализации оборудования */
    private double equipmentSalesRatio = DEFAULT_EQUIPMENT_SALES_RATIO;

    /** Q - Количество проданных изделий в год */
    private int productsSoldPerYears = DEFAULT_PRODUCTS_SOLD_PER_YEARS;

    /** T - Ставка налога на прибыль */
    private double incomeTaxRate = DEFAULT_INCOME_TAX_RATE;

    /** E - Ставка налога на прибыль */
    private double discountRate = DEFAULT_DISCOUNT_RATE;

    /** r - Процент по кредиту */
    private double interestOnLoan = DEFAULT_INTEREST_ON_LOAN;

    /** iMax - Количество расчитываемых лет */
    private int yearsNumber;

    /** S(i) - Погашение кредита */
    private double[] repaymentOfCredit;

    /** Y - Цена продаж за единицу */
    private double productCost = DEFAULT_PRODUCT_COST;

    /** l - Цена материалов за единицу */
    private double productMaterialCost = DEFAULT_PRODUCT_MATERIAL_COST;

    /** ZT - Расходы на оплату труда */
    private double laborCosts = DEFAULT_LABOR_COST;

    /** ZO - Общепроизводственные расходы */
    private double generalProductionExpenses = DEFAULT_GENERAL_PRODUCTION_EXPENSES;

    /** ZY - Управленческие расходы */
    private double managementExpenses = DEFAULT_MANAGEMENT_EXPENSES;

    /** ZP - Расходы на рекламу и сбыт */
    private double adsExpenses = DEFAULT_ADS_EXPENSES;

    /** pi 1 - Ожидаемый темп инфляции по №12 */
    private double expectedRateOfInflation_1 = DEFAULT_EXPECTED_RATE_OF_INFLATION_1;

    /** pi 2 - Ожидаемый темп инфляции по №13 */
    private double expectedRateOfInflation_2 = DEFAULT_EXPECTED_RATE_OF_INFLATION_2;

    /** pi 3 - Ожидаемый темп инфляции по №14 */
    private double expectedRateOfInflation_3 = DEFAULT_EXPECTED_RATE_OF_INFLATION_3;

    /** pi 4 - Ожидаемый темп инфляции по №15 */
    private double expectedRateOfInflation_4 = DEFAULT_EXPECTED_RATE_OF_INFLATION_4;

    /** pi 5 - Ожидаемый темп инфляции по №16 */
    private double expectedRateOfInflation_5 = DEFAULT_EXPECTED_RATE_OF_INFLATION_5;

    /** pi 6 - Ожидаемый темп инфляции по №17 */
    private double expectedRateOfInflation_6 = DEFAULT_EXPECTED_RATE_OF_INFLATION_6;

    /** KS - Коэффициент оборачиваемости по продажам */
    private double salesTurnoverRatio = DEFAULT_SALES_TURNOVER_RATIO;

    /** KZ - Коэффициент оборачиваемости по запасам */
    private double stocksTurnoverRatio = DEFAULT_STOCKS_TURNOVER_RATIO;

    /** KC - Коэффициент оборачиваемости по поставкам */
    private double suppliesTurnoverRatio = DEFAULT_SUPPLIES_TURNOVER_RATIO;

    /** KT - Коэффициент оборачиваемости по оплате труда */
    private double salariesTurnoverRatio = DEFAULT_SALARIES_TURNOVER_RATIO;


    public InputValues() {
        this(DEFAULT_YEARS_NUMBER);
    }

    public InputValues(int yearsNumber) {
        this.yearsNumber = yearsNumber;
        repaymentOfCredit = new double[yearsNumber + 1];
        Arrays.fill(repaymentOfCredit, 0);
    }

    public double getInitialEquipmentCost() {
        return initialEquipmentCost;
    }

    public double getEquipmentSalesRatio() {
        return equipmentSalesRatio;
    }

    public int getProductsSoldPerYears() {
        return productsSoldPerYears;
    }

    public double getIncomeTaxRate() {
        return incomeTaxRate;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public double getInterestOnLoan() {
        return interestOnLoan;
    }

    public int getYearsNumber() {
        return yearsNumber;
    }

    public double getRepaymentOfCredit(int index) {
        if (yearsNumber == index) {
            double res = initialEquipmentCost;
            for (double v : repaymentOfCredit) {
                res -= v;
            }
            return res;
        }
        return repaymentOfCredit[index];
    }

    public double getProductCost() {
        return productCost;
    }

    public double getProductMaterialCost() {
        return productMaterialCost;
    }

    public double getLaborCosts() {
        return laborCosts;
    }

    public double getGeneralProductionExpenses() {
        return generalProductionExpenses;
    }

    public double getManagementExpenses() {
        return managementExpenses;
    }

    public double getAdsExpenses() {
        return adsExpenses;
    }

    public double getExpectedRateOfInflation_1() {
        return expectedRateOfInflation_1;
    }

    public double getExpectedRateOfInflation_2() {
        return expectedRateOfInflation_2;
    }

    public double getExpectedRateOfInflation_3() {
        return expectedRateOfInflation_3;
    }

    public double getExpectedRateOfInflation_4() {
        return expectedRateOfInflation_4;
    }

    public double getExpectedRateOfInflation_5() {
        return expectedRateOfInflation_5;
    }

    public double getExpectedRateOfInflation_6() {
        return expectedRateOfInflation_6;
    }

    public double getSalesTurnoverRatio() {
        return salesTurnoverRatio;
    }

    public double getStocksTurnoverRatio() {
        return stocksTurnoverRatio;
    }

    public double getSuppliesTurnoverRatio() {
        return suppliesTurnoverRatio;
    }

    public double getSalariesTurnoverRatio() {
        return salariesTurnoverRatio;
    }

    public InputValues setInitialEquipmentCost(double initialEquipmentCost) {
        this.initialEquipmentCost = initialEquipmentCost;
        return this;
    }

    public InputValues setEquipmentSalesRatio(double equipmentSalesRatio) {
        this.equipmentSalesRatio = equipmentSalesRatio;
        return this;
    }

    public InputValues setProductsSoldPerYears(int productsSoldPerYears) {
        this.productsSoldPerYears = productsSoldPerYears;
        return this;
    }

    public InputValues setIncomeTaxRate(double incomeTaxRate) {
        this.incomeTaxRate = incomeTaxRate;
        return this;
    }

    public InputValues setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
        return this;
    }

    public InputValues setInterestOnLoan(double interestOnLoan) {
        this.interestOnLoan = interestOnLoan;
        return this;
    }

    public InputValues setYearsNumber(int yearsNumber) {
        this.yearsNumber = yearsNumber;
        return this;
    }

    public InputValues setRepaymentOfCredit(double repaymentOfCredit, int index) {
        if (yearsNumber != index) {
            this.repaymentOfCredit[index] = repaymentOfCredit;
        }
        return this;
    }

    public InputValues setProductCost(double productCost) {
        this.productCost = productCost;
        return this;
    }

    public InputValues setProductMaterialCost(double productMaterialCost) {
        this.productMaterialCost = productMaterialCost;
        return this;
    }

    public InputValues setLaborCosts(double laborCosts) {
        this.laborCosts = laborCosts;
        return this;
    }

    public InputValues setGeneralProductionExpenses(double generalProductionExpenses) {
        this.generalProductionExpenses = generalProductionExpenses;
        return this;
    }

    public InputValues setManagementExpenses(double managementExpenses) {
        this.managementExpenses = managementExpenses;
        return this;
    }

    public InputValues setAdsExpenses(double adsExpenses) {
        this.adsExpenses = adsExpenses;
        return this;
    }

    public InputValues setExpectedRateOfInflation_1(double expectedRateOfInflation_1) {
        this.expectedRateOfInflation_1 = expectedRateOfInflation_1;
        return this;
    }

    public InputValues setExpectedRateOfInflation_2(double expectedRateOfInflation_2) {
        this.expectedRateOfInflation_2 = expectedRateOfInflation_2;
        return this;
    }

    public InputValues setExpectedRateOfInflation_3(double expectedRateOfInflation_3) {
        this.expectedRateOfInflation_3 = expectedRateOfInflation_3;
        return this;
    }

    public InputValues setExpectedRateOfInflation_4(double expectedRateOfInflation_4) {
        this.expectedRateOfInflation_4 = expectedRateOfInflation_4;
        return this;
    }

    public InputValues setExpectedRateOfInflation_5(double expectedRateOfInflation_5) {
        this.expectedRateOfInflation_5 = expectedRateOfInflation_5;
        return this;
    }

    public InputValues setExpectedRateOfInflation_6(double expectedRateOfInflation_6) {
        this.expectedRateOfInflation_6 = expectedRateOfInflation_6;
        return this;
    }

    public InputValues setSalesTurnoverRatio(double salesTurnoverRatio) {
        this.salesTurnoverRatio = salesTurnoverRatio;
        return this;
    }

    public InputValues setStocksTurnoverRatio(double stocksTurnoverRatio) {
        this.stocksTurnoverRatio = stocksTurnoverRatio;
        return this;
    }

    public InputValues setSuppliesTurnoverRatio(double suppliesTurnoverRatio) {
        this.suppliesTurnoverRatio = suppliesTurnoverRatio;
        return this;
    }

    public InputValues setSalariesTurnoverRatio(double salariesTurnoverRatio) {
        this.salariesTurnoverRatio = salariesTurnoverRatio;
        return this;
    }

    @Override
    public InputValues clone() throws CloneNotSupportedException {
        InputValues newInputs = new InputValues(yearsNumber);

        newInputs.initialEquipmentCost = initialEquipmentCost;
        newInputs.equipmentSalesRatio = equipmentSalesRatio;
        newInputs.productsSoldPerYears = productsSoldPerYears;
        newInputs.incomeTaxRate = incomeTaxRate;
        newInputs.discountRate = discountRate;
        newInputs.interestOnLoan = interestOnLoan;
        newInputs.repaymentOfCredit = Arrays.copyOf(repaymentOfCredit, repaymentOfCredit.length);
        newInputs.productCost = productCost;
        newInputs.productMaterialCost = productMaterialCost;
        newInputs.laborCosts = laborCosts;
        newInputs.generalProductionExpenses = generalProductionExpenses;
        newInputs.managementExpenses = managementExpenses;
        newInputs.adsExpenses = adsExpenses;
        newInputs.expectedRateOfInflation_1 = expectedRateOfInflation_1;
        newInputs.expectedRateOfInflation_2 = expectedRateOfInflation_2;
        newInputs.expectedRateOfInflation_3 = expectedRateOfInflation_3;
        newInputs.expectedRateOfInflation_4 = expectedRateOfInflation_4;
        newInputs.expectedRateOfInflation_5 = expectedRateOfInflation_5;
        newInputs.expectedRateOfInflation_6 = expectedRateOfInflation_6;
        newInputs.salesTurnoverRatio = salesTurnoverRatio;
        newInputs.stocksTurnoverRatio = stocksTurnoverRatio;
        newInputs.suppliesTurnoverRatio = suppliesTurnoverRatio;
        newInputs.salariesTurnoverRatio = salariesTurnoverRatio;

        return newInputs;
    }
}