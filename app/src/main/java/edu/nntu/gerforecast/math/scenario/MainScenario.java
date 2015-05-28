package edu.nntu.gerforecast.math.scenario;

import edu.nntu.gerforecast.math.data.ElasticityOutput;
import edu.nntu.gerforecast.math.data.InputValues;
import edu.nntu.gerforecast.math.data.OutputValues;

public class MainScenario {

    public OutputValues calculate(InputValues input) {
        OutputValues output = new OutputValues(input);

        calculateRealizationForecast(input, output);
        calculateExpenses(input, output);
        calculateBankForecast(input, output);
        calculateCapitalForecast(input, output);
        calculateProfitsAndLostForecast(input, output);
        calculateCashFlowForecast(input, output);
        calculateBalanceForecast(input, output);
        calculateFinancialHighlights(input, output);
        calculateEstimationOfInvestmentProjects(input, output);

        return output;
    }

    private void calculateRealizationForecast(InputValues input, OutputValues output) {
        output.productCostForecast[1] = input.getProductCost();
        for (int i = 2; i < output.yearsNumber; i++) {
            output.productCostForecast[i] = output.productCostForecast[i-1] * (1 + input.getExpectedRateOfInflation_1());
        }
        for (int i = 1; i < output.yearsNumber; i++) {
            output.productRevenueForecast[i] = output.productCostForecast[i] * input.getProductsSoldPerYears();
        }
    }

    private void calculateExpenses(InputValues input, OutputValues output) {
        double pmc = input.getProductMaterialCost();
        for (int i = 1; i < output.yearsNumber; i++) {
            output.productMaterialExpenses[i] = - pmc * input.getProductsSoldPerYears();
            pmc *= (1 + input.getExpectedRateOfInflation_2());
        }

        double lc = input.getLaborCosts();
        for (int i = 1; i < output.yearsNumber; i++) {
            output.laborExpenses[i] = - lc;
            lc *= (1 + input.getExpectedRateOfInflation_3());
        }

        double gpe = input.getGeneralProductionExpenses();
        for (int i = 1; i < output.yearsNumber; i++) {
            output.generalProductionExpenses[i] = - gpe;
            gpe *= (1 + input.getExpectedRateOfInflation_4());
        }

        double me = input.getManagementExpenses();
        for (int i = 1; i < output.yearsNumber; i++) {
            output.managementExpenses[i] = - me;
            me *= (1 + input.getExpectedRateOfInflation_5());
        }

        double ae = input.getAdsExpenses();
        for (int i = 1; i < output.yearsNumber; i++) {
            output.adsExpenses[i] = - ae;
            ae *= (1 + input.getExpectedRateOfInflation_6());
        }

        for (int i = 1; i < output.yearsNumber; i++) {
            output.amortizationOfEquipment[i] = - input.getInitialEquipmentCost()/input.getYearsNumber();
        }

        for (int i = 1; i < output.yearsNumber; i++) {
            output.totalExpenses[i] = output.productMaterialExpenses[i]
                                    + output.laborExpenses[i]
                                    + output.generalProductionExpenses[i]
                                    + output.managementExpenses[i]
                                    + output.adsExpenses[i]
                                    + output.amortizationOfEquipment[i];
        }

    }

    private void calculateBankForecast(InputValues input, OutputValues output) {
        output.outstandingBalance[0] = input.getInitialEquipmentCost();
        for (int i = 1; i <= input.getYearsNumber(); i++) {
            output.outstandingBalance[i] = output.outstandingBalance[i - 1] - input.getRepaymentOfCredit(i);
        }
        for (int i = 1; i <= input.getYearsNumber(); i++) {
            output.amountOfPayments[i] = output.outstandingBalance[i - 1]*input.getInterestOnLoan();
        }
    }

    private void calculateCapitalForecast(InputValues input, OutputValues output) {
        for (int i = 1; i <= input.getYearsNumber(); i++) {
            output.accountsReceivable[i] = - output.productRevenueForecast[i]/input.getSalesTurnoverRatio();
        }
        for (int i = 1; i <= input.getYearsNumber(); i++) {
            output.stocks[i] = output.productMaterialExpenses[i]/input.getStocksTurnoverRatio();
        }
        for (int i = 1; i <= input.getYearsNumber(); i++) {
            output.payablesToSuppliers[i] = - output.productMaterialExpenses[i]/input.getSuppliesTurnoverRatio();
        }
        for (int i = 1; i <= input.getYearsNumber(); i++) {
            output.arrearsOfWages[i] = - output.laborExpenses[i]/input.getSalariesTurnoverRatio();
        }
        for (int i = 1; i <= input.getYearsNumber(); i++) {
            output.workingCapitalRequirements[i] = output.accountsReceivable[i]
                                                 + output.stocks[i]
                                                 + output.payablesToSuppliers[i]
                                                 + output.arrearsOfWages[i];
        }
        for (int i = 1; i <= input.getYearsNumber() + 1; i++) {
            output.changesInWorkingCapital[i] = output.workingCapitalRequirements[i] - output.workingCapitalRequirements[i - 1];
        }
    }

    private void calculateProfitsAndLostForecast(InputValues input, OutputValues output) {
        output.revenueFromSaleOfAssets[output.yearsNumber] = input.getInitialEquipmentCost() * input.getEquipmentSalesRatio();
        for (int i = 0; i < output.yearsNumber; i++) {
            output.totalIncome[i] = output.productRevenueForecast[i] + output.revenueFromSaleOfAssets[i];
        }
        output.totalIncome[output.yearsNumber] = output.revenueFromSaleOfAssets[output.yearsNumber];

        for (int i = 0; i < output.yearsNumber; i++) {
            output.profitBeforeTaxes[i] = output.totalIncome[i] + output.totalExpenses[i];
        }
        output.profitBeforeTaxes[output.yearsNumber] = output.totalIncome[output.yearsNumber];

        for (int i = 0; i <= output.yearsNumber; i++) {
            output.taxOnProfits[i] = input.getIncomeTaxRate()*output.profitBeforeTaxes[i];
        }
        for (int i = 0; i <= output.yearsNumber; i++) {
            output.netProfit[i] = output.profitBeforeTaxes[i] - output.taxOnProfits[i];
        }
        for (int i = 0; i < output.yearsNumber; i++) {
            output.retainedProfit[i] = output.netProfit[i] - output.amountOfPayments[i];
        }
        output.retainedProfit[output.yearsNumber] = output.netProfit[output.yearsNumber];
    }

    private void calculateCashFlowForecast(InputValues input, OutputValues output) {
        for (int i = 0; i <= output.yearsNumber; i++) {
            output.balanceFromInvestingActivities[i] = output.revenueFromSaleOfAssets[i] + (i == 0 ? -input.getInitialEquipmentCost() : 0);
        }
        for (int i = 0; i < output.yearsNumber; i++) {
            output.balanceFromOperatingActivities[i] = output.productRevenueForecast[i]
                                                     + output.productMaterialExpenses[i]
                                                     + output.laborExpenses[i]
                                                     + output.generalProductionExpenses[i]
                                                     + output.managementExpenses[i]
                                                     + output.adsExpenses[i]
                                                     - output.taxOnProfits[i]
                                                     + output.changesInWorkingCapital[i];
        }
        output.balanceFromOperatingActivities[output.yearsNumber] = output.changesInWorkingCapital[output.yearsNumber]
                                                                  - output.taxOnProfits[output.yearsNumber];

        output.balanceFromFinanceActivities[0] = input.getInitialEquipmentCost() + output.amountOfPayments[0];
        for (int i = 1; i < output.yearsNumber; i++) {
            output.balanceFromFinanceActivities[i] = - (input.getRepaymentOfCredit(i) + output.amountOfPayments[i]);
        }
        for (int i = 0; i <= output.yearsNumber; i++) {
            output.balanceFromAllActivities[i] = output.balanceFromInvestingActivities[i]
                                               + output.balanceFromOperatingActivities[i]
                                               + output.balanceFromFinanceActivities[i];
        }
        for (int i = 1; i <= output.yearsNumber; i++) {
            output.cashBalance[i] = output.cashBalance[i - 1] + output.balanceFromAllActivities[i];
        }
    }

    private void calculateBalanceForecast(InputValues input, OutputValues output) {
        for (int i = 1; i < output.yearsNumber; i++) {
            output.depreciationOfEquipment[i] = output.amortizationOfEquipment[i] + output.depreciationOfEquipment[i - 1];
        }
        for (int i = 0; i <= output.yearsNumber; i++) {
            if (i != output.yearsNumber) {
                output.totalAssets[i] = output.cashBalance[i] - output.accountsReceivable[i] - output.stocks[i] + input.getInitialEquipmentCost() + output.depreciationOfEquipment[i];
            } else {
                output.totalAssets[i] = output.cashBalance[i] + output.depreciationOfEquipment[i];
            }
        }
        for (int i = 1; i <= output.yearsNumber; i++) {
            output.retainedProfitPassive[i] = output.retainedProfitPassive[i - 1] + output.retainedProfit[i];
        }
        for (int i = 0; i < output.yearsNumber; i++) {
            if (i == 0) {
                output.bankLoan[i] = input.getInitialEquipmentCost();
            } else {
                output.bankLoan[i] = output.bankLoan[i - 1] - input.getRepaymentOfCredit(i);
            }
        }
        for (int i = 0; i <= output.yearsNumber; i++) {
            if (i != output.yearsNumber) {
                output.totalLiabilitiesPassive[i] = output.retainedProfitPassive[i] + output.bankLoan[i] + output.payablesToSuppliers[i] + output.arrearsOfWages[i];
            } else {
                output.totalLiabilitiesPassive[i] = output.retainedProfitPassive[i] + output.bankLoan[i];
            }
        }
    }

    private void calculateFinancialHighlights(InputValues input, OutputValues output) {
        for (int i = 1; i < output.yearsNumber; i++) {
            output.currentRatio[i] = (output.cashBalance[i] - output.accountsReceivable[i] - output.stocks[i])
                                   / (output.payablesToSuppliers[i] + output.arrearsOfWages[i]);
        }
        for (int i = 1; i < output.yearsNumber; i++) {
            output.interestCoverage[i] = output.netProfit[i]/output.amountOfPayments[i];
        }
        for (int i = 1; i < output.yearsNumber; i++) {
            output.ros[i] = output.netProfit[i]/output.productRevenueForecast[i];
        }
        for (int i = 1; i < output.yearsNumber; i++) {
            output.roa[i] = output.netProfit[i]/output.totalAssets[i];
        }
    }

    private void calculateEstimationOfInvestmentProjects(InputValues input, OutputValues output) {
        for (int i = 0; i <= output.yearsNumber; i++) {
            output.operationsAndInvestments[i] = output.balanceFromInvestingActivities[i] + output.balanceFromOperatingActivities[i];
        }
        for (int i = 0; i <= output.yearsNumber; i++) {
            if (i == 0) {
                output.currentIncome[i] = output.operationsAndInvestments[i];
            } else {
                output.currentIncome[i] = output.operationsAndInvestments[i] + output.currentIncome[i - 1];
            }
        }
        for (int i = 0; i <= output.yearsNumber; i++) {
            output.discountCoefficient[i] = 1/Math.pow(1 + input.getDiscountRate(), i);
        }
        for (int i = 0; i <= output.yearsNumber; i++) {
            output.discountedCashFlow[i] = output.operationsAndInvestments[i]*output.discountCoefficient[i];
        }
        for (int i = 0; i <= output.yearsNumber; i++) {
            if (i == 0) {
                output.currentOperationsAndInvestments[i] = output.discountedCashFlow[i];
            } else {
                output.currentOperationsAndInvestments[i] = output.discountedCashFlow[i] + output.currentOperationsAndInvestments[i - 1];
            }
        }
    }

    public ElasticityOutput calculateElasticity(InputValues input) {
        try {
            InputValues inQ = input.clone();
            inQ.setProductsSoldPerYears((int) (inQ.getProductsSoldPerYears() * 0.9));
            OutputValues q = calculate(inQ);

            InputValues inK = input.clone();
            inK.setProductCost((int) (inK.getProductCost() * 1.1));
            OutputValues k = calculate(inK);

            InputValues inL = input.clone();
            inL.setProductMaterialCost(inL.getProductMaterialCost() * 1.1);
            OutputValues l = calculate(inL);

            InputValues inKs = input.clone();
            inKs.setSalesTurnoverRatio(inKs.getSalesTurnoverRatio() * 2);
            OutputValues ks = calculate(inKs);

            InputValues inF = input.clone();
            inF.setInitialEquipmentCost(inF.getInitialEquipmentCost() * 1.2);
            OutputValues f = calculate(inF);

            return new ElasticityOutput(q, k, ks, l, f);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Input clone error");
        }
    }

}
