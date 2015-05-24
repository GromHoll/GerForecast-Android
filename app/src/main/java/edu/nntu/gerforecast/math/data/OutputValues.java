package edu.nntu.gerforecast.math.data;

import java.util.Arrays;

public class OutputValues {

    /** iMax - Количество расчитываемых лет */
    public final int yearsNumber;

    /* Прогноз реализация */
    /** K(i) - Прогноз цен на продукцию */
    public double[] productCostForecast;
    /** N(i) - Прогноз выручки */
    public double[] productRevenueForecast;

    /* Структура расходов */
    /** OL(i) - Расходы на сырье и материалы */
    public double[] productMaterialExpenses;
    /** OT(i) - Оплата труда (включая ЕСН) */
    public double[] laborExpenses;
    /** OO(i) - Общепроизводственные расходы */
    public double[] generalProductionExpenses;
    /** OY(i) - Управленческие расходы */
    public double[] managementExpenses;
    /** OP(i) - Расходы на рекламу и сбыт */
    public double[] adsExpenses;
    /** OA(i) - Амортизация оборудования */
    public double[] amortizationOfEquipment;
    /** R(i) - Итого расходов */
    public double[] totalExpenses;

    /* План расчета с банком */
    /** U(i) - Остаток задолженности */
    public double[] outstandingBalance;
    /** W(i) - Размер выплат за  период */
    public double[] amountOfPayments;

    /* Прогноз изменения в оборотном капитале */
    /** D(i) - Дебиторская задолженность */
    public double[] accountsReceivable;
    /** B(i) - Запасы */
    public double[] stocks;
    /** G(i) - Задолженность перед поставщиком */
    public double[] payablesToSuppliers;
    /** T(i) - Задолженность по оплате труда */
    public double[] arrearsOfWages;
    /** C(i) - Потребность в оборотном капитале */
    public double[] workingCapitalRequirements;
    /** H(i) - Изменения оборотного капитала */
    public double[] changesInWorkingCapital;

    /* Прогноз прибылей/убытков */
    /** DA(i) - Доход от реализации активов */
    public double[] revenueFromSaleOfAssets;
    /** BA(i) - Всего доходов */
    public double[] totalIncome;
    /** P(i) - Прибыль до выплаты налогов */
    public double[] profitBeforeTaxes;
    /** TP(i) - Налог на прибыль */
    public double[] taxOnProfits;
    /** PR(i) - Чистая прибыль */
    public double[] netProfit;
    /** PN(i) - Нераспределенная прибыль */
    public double[] retainedProfit;

    /* Прогноз движения денежных средств */
    /** CI(i) - Сальдо от инвестиционной деятельности */
    public double[] balanceFromInvestingActivities;
    /** CO(i) - Сальдо от операционной деятельности */
    public double[] balanceFromOperatingActivities;
    /** CF(i) - Сальдо от операционной деятельности */
    public double[] balanceFromFinanceActivities;
    /** CC(i) - Суммарное сальдо */
    public double[] balanceFromAllActivities;
    /** O(i) - Остаток денежных средств */
    public double[] cashBalance;

    /* Прогнозный баланс */
    /** IO(i) - Износ оборудования */
    public double[] depreciationOfEquipment;
    /** AK(i) - Итог активов */
    public double[] totalAssets;
    /** HP(i) - Нераспределенная прибыль */
    public double[] retainedProfitPassive;
    /** BK(i) - Банковский кредит */
    public double[] bankLoan;
    /** PS(i) - Итог Пассивовв */
    public double[] totalLiabilitiesPassive;

    /* Основные финансовые показатели */
    /** CR(i) - Текущая ликвидность */
    public double[] currentRatio;
    /** IC(i) - Покрытие процентов */
    public double[] interestCoverage;
    /** ROS(i) - Прибыль/выручка */
    public double[] ros;
    /** ROA(i) - Прибыль/активы */
    public double[] roa;

    /* Оценка инвестиционного проекта */
    /** NCF(i) - ЧДП (операции и инвестиции) */
    public double[] operationsAndInvestments;
    /** TD(i) - Текущий чистый доход */
    public double[] currentIncome;
    /** KD(i) - Коэффициент дисконтирования */
    public double[] discountCoefficient;
    /** CCF(i) - Дисконтированный денежный поток */
    public double[] discountedCashFlow;
    /** NPV(i) - Текущий ЧДД */
    public double[] currentOperationsAndInvestments;

    public OutputValues(InputValues input) {
        yearsNumber = input.getYearsNumber() + 1;

        productRevenueForecast = new double[yearsNumber];
        productCostForecast = new double[yearsNumber];

        Arrays.fill(productRevenueForecast, 0);
        Arrays.fill(productCostForecast, 0);

        productMaterialExpenses = new double[yearsNumber];
        laborExpenses = new double[yearsNumber];
        generalProductionExpenses = new double[yearsNumber];
        managementExpenses = new double[yearsNumber];
        adsExpenses = new double[yearsNumber];
        amortizationOfEquipment = new double[yearsNumber];
        totalExpenses = new double[yearsNumber];

        Arrays.fill(productMaterialExpenses, 0);
        Arrays.fill(laborExpenses, 0);
        Arrays.fill(generalProductionExpenses, 0);
        Arrays.fill(managementExpenses, 0);
        Arrays.fill(adsExpenses, 0);
        Arrays.fill(amortizationOfEquipment, 0);
        Arrays.fill(totalExpenses, 0);

        outstandingBalance = new double[yearsNumber];
        amountOfPayments = new double[yearsNumber];

        Arrays.fill(outstandingBalance, 0);
        Arrays.fill(amountOfPayments, 0);

        accountsReceivable = new double[yearsNumber];
        stocks = new double[yearsNumber];
        payablesToSuppliers = new double[yearsNumber];
        arrearsOfWages = new double[yearsNumber];
        workingCapitalRequirements = new double[yearsNumber + 1];
        changesInWorkingCapital = new double[yearsNumber + 1];

        Arrays.fill(accountsReceivable, 0);
        Arrays.fill(stocks, 0);
        Arrays.fill(payablesToSuppliers, 0);
        Arrays.fill(arrearsOfWages, 0);
        Arrays.fill(workingCapitalRequirements, 0);
        Arrays.fill(changesInWorkingCapital, 0);

        revenueFromSaleOfAssets = new double[yearsNumber + 1];
        totalIncome = new double[yearsNumber + 1];
        profitBeforeTaxes = new double[yearsNumber + 1];
        taxOnProfits = new double[yearsNumber + 1];
        netProfit = new double[yearsNumber + 1];
        retainedProfit = new double[yearsNumber + 1];

        Arrays.fill(revenueFromSaleOfAssets, 0);
        Arrays.fill(totalIncome, 0);
        Arrays.fill(profitBeforeTaxes, 0);
        Arrays.fill(taxOnProfits, 0);
        Arrays.fill(netProfit, 0);
        Arrays.fill(retainedProfit, 0);

        balanceFromInvestingActivities = new double[yearsNumber + 1];
        balanceFromOperatingActivities = new double[yearsNumber + 1];
        balanceFromFinanceActivities = new double[yearsNumber + 1];
        balanceFromAllActivities = new double[yearsNumber + 1];
        cashBalance = new double[yearsNumber + 1];

        Arrays.fill(balanceFromInvestingActivities, 0);
        Arrays.fill(balanceFromOperatingActivities, 0);
        Arrays.fill(balanceFromFinanceActivities, 0);
        Arrays.fill(balanceFromAllActivities, 0);
        Arrays.fill(cashBalance, 0);

        depreciationOfEquipment = new double[yearsNumber + 1];
        totalAssets = new double[yearsNumber + 1];
        retainedProfitPassive = new double[yearsNumber + 1];
        bankLoan = new double[yearsNumber + 1];
        totalLiabilitiesPassive = new double[yearsNumber + 1];

        Arrays.fill(depreciationOfEquipment, 0);
        Arrays.fill(totalAssets, 0);
        Arrays.fill(retainedProfitPassive, 0);
        Arrays.fill(bankLoan, 0);
        Arrays.fill(totalLiabilitiesPassive, 0);

        currentRatio = new double[yearsNumber];
        interestCoverage = new double[yearsNumber];
        ros = new double[yearsNumber];
        roa = new double[yearsNumber];

        Arrays.fill(currentRatio, 0);
        Arrays.fill(interestCoverage, 0);
        Arrays.fill(ros, 0);
        Arrays.fill(roa, 0);

        operationsAndInvestments = new double[yearsNumber + 1];
        currentIncome = new double[yearsNumber + 1];
        discountCoefficient = new double[yearsNumber + 1];
        discountedCashFlow = new double[yearsNumber + 1];
        currentOperationsAndInvestments = new double[yearsNumber + 1];

        Arrays.fill(operationsAndInvestments, 0);
        Arrays.fill(currentIncome, 0);
        Arrays.fill(discountCoefficient, 0);
        Arrays.fill(discountedCashFlow, 0);
        Arrays.fill(currentOperationsAndInvestments, 0);
    }

    @Override
    public String toString() {
        return  "yearsNumber = " + yearsNumber +
                "\nproductCostForecast = " + Arrays.toString(productCostForecast) +
                "\nproductRevenueForecast = " + Arrays.toString(productRevenueForecast) +
                "\nproductMaterialExpenses = " + Arrays.toString(productMaterialExpenses) +
                "\nlaborExpenses = " + Arrays.toString(laborExpenses) +
                "\ngeneralProductionExpenses = " + Arrays.toString(generalProductionExpenses) +
                "\nmanagementExpenses = " + Arrays.toString(managementExpenses) +
                "\nadsExpenses = " + Arrays.toString(adsExpenses) +
                "\namortizationOfEquipment = " + Arrays.toString(amortizationOfEquipment) +
                "\ntotalExpenses = " + Arrays.toString(totalExpenses) +
                "\noutstandingBalance = " + Arrays.toString(outstandingBalance) +
                "\namountOfPayments = " + Arrays.toString(amountOfPayments) +
                "\naccountsReceivable = " + Arrays.toString(accountsReceivable) +
                "\nstocks = " + Arrays.toString(stocks) +
                "\npayablesToSuppliers = " + Arrays.toString(payablesToSuppliers) +
                "\narrearsOfWages = " + Arrays.toString(arrearsOfWages) +
                "\nworkingCapitalRequirements = " + Arrays.toString(workingCapitalRequirements) +
                "\nchangesInWorkingCapital = " + Arrays.toString(changesInWorkingCapital) +
                "\nrevenueFromSaleOfAssets = " + Arrays.toString(revenueFromSaleOfAssets) +
                "\ntotalIncome = " + Arrays.toString(totalIncome) +
                "\nprofitBeforeTaxes = " + Arrays.toString(profitBeforeTaxes) +
                "\ntaxOnProfits = " + Arrays.toString(taxOnProfits) +
                "\nnetProfit = " + Arrays.toString(netProfit) +
                "\nretainedProfit = " + Arrays.toString(retainedProfit) +
                "\nbalanceFromInvestingActivities = " + Arrays.toString(balanceFromInvestingActivities) +
                "\nbalanceFromOperatingActivities = " + Arrays.toString(balanceFromOperatingActivities) +
                "\nbalanceFromFinanceActivities = " + Arrays.toString(balanceFromFinanceActivities) +
                "\nbalanceFromAllActivities = " + Arrays.toString(balanceFromAllActivities) +
                "\ncashBalance = " + Arrays.toString(cashBalance) +
                "\ndepreciationOfEquipment = " + Arrays.toString(depreciationOfEquipment) +
                "\ntotalAssets = " + Arrays.toString(totalAssets) +
                "\nretainedProfitPassive = " + Arrays.toString(retainedProfitPassive) +
                "\nbankLoan = " + Arrays.toString(bankLoan) +
                "\ntotalLiabilitiesPassive = " + Arrays.toString(totalLiabilitiesPassive) +
                "\ncurrentRatio = " + Arrays.toString(currentRatio) +
                "\ninterestCoverage = " + Arrays.toString(interestCoverage) +
                "\nros = " + Arrays.toString(ros) +
                "\nroa = " + Arrays.toString(roa) +
                "\noperationsAndInvestments = " + Arrays.toString(operationsAndInvestments) +
                "\ncurrentIncome = " + Arrays.toString(currentIncome) +
                "\ndiscountCoefficient = " + Arrays.toString(discountCoefficient) +
                "\ndiscountedCashFlow = " + Arrays.toString(discountedCashFlow) +
                "\ncurrentOperationsAndInvestments = " + Arrays.toString(currentOperationsAndInvestments)
                ;
    }
}
