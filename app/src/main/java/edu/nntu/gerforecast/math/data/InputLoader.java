package edu.nntu.gerforecast.math.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class InputLoader {

    public InputValues load(String filename) {
        Properties properties = loadProperties(filename);
        int yearsNumber = Integer.parseInt(properties.getProperty("yearsNumber"));
        InputValues input = new InputValues(yearsNumber);

        String repaymentOfCredit = properties.getProperty("repaymentOfCredit");
        String[] repaymentOfCreditArray = repaymentOfCredit.split(",");
        for (int i = 0; i < repaymentOfCreditArray.length; i++) {
            input.setRepaymentOfCredit(Double.parseDouble(repaymentOfCreditArray[i]), i);
        }

        input.setInitialEquipmentCost(Double.parseDouble(properties.getProperty("initialEquipmentCost")))
                .setEquipmentSalesRatio(Double.parseDouble(properties.getProperty("equipmentSalesRatio")))
                .setProductsSoldPerYears(Integer.parseInt(properties.getProperty("productsSoldPerYears")))
                .setIncomeTaxRate(Double.parseDouble(properties.getProperty("incomeTaxRate")))
                .setDiscountRate(Double.parseDouble(properties.getProperty("discountRate")))
                .setInterestOnLoan(Double.parseDouble(properties.getProperty("interestOnLoan")))
                .setProductCost(Double.parseDouble(properties.getProperty("productCost")))
                .setProductMaterialCost(Double.parseDouble(properties.getProperty("productMaterialCost")))
                .setLaborCosts(Double.parseDouble(properties.getProperty("laborCosts")))
                .setGeneralProductionExpenses(Double.parseDouble(properties.getProperty("generalProductionExpenses")))
                .setManagementExpenses(Double.parseDouble(properties.getProperty("managementExpenses")))
                .setAdsExpenses(Double.parseDouble(properties.getProperty("adsExpenses")))
                .setExpectedRateOfInflation_1(Double.parseDouble(properties.getProperty("expectedRateOfInflation_1")))
                .setExpectedRateOfInflation_2(Double.parseDouble(properties.getProperty("expectedRateOfInflation_2")))
                .setExpectedRateOfInflation_3(Double.parseDouble(properties.getProperty("expectedRateOfInflation_3")))
                .setExpectedRateOfInflation_4(Double.parseDouble(properties.getProperty("expectedRateOfInflation_4")))
                .setExpectedRateOfInflation_5(Double.parseDouble(properties.getProperty("expectedRateOfInflation_5")))
                .setExpectedRateOfInflation_6(Double.parseDouble(properties.getProperty("expectedRateOfInflation_6")))
                .setSalesTurnoverRatio(Double.parseDouble(properties.getProperty("salesTurnoverRatio")))
                .setStocksTurnoverRatio(Double.parseDouble(properties.getProperty("stocksTurnoverRatio")))
                .setSuppliesTurnoverRatio(Double.parseDouble(properties.getProperty("suppliesTurnoverRatio")))
                .setSalariesTurnoverRatio(Double.parseDouble(properties.getProperty("salariesTurnoverRatio")))
                ;
        return input;
    }

    private Properties loadProperties(String filename) {
        Properties properties = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(filename);
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }
}
