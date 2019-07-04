import calculate.Calculate;
import calculate.CalculateDelivery;
import files.CSVDataReader;
import files.DataReader;
import input.CorrectInputValue;
import input.InputValue;
import input.ReceiveValue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class Main {

    public static final String PRICE_PER_KG_PATH = "price_per_kg.csv";
    public static final String PRICE_PER_KM_PATH = "price_per_km.csv";
    public static final String FILE_NOT_FOUND_MSG = "File not found: ";
    public static final String REQUEST_WEIGHT_VALUE_MSG = "Enter weight in kg or 'q' for cancel: ";
    public static final String REQUEST_DISTANCE_VALUE_MSG = "Enter distance in km or 'q' for cancel: ";
    public static final String MSG_DIVIDER = "-------------------------------------------------";

    public static void main(String[] args) throws IOException {
        BigDecimal calculatedPriceFromWeight;
        BigDecimal calculatedPriceFromDistance;
        DataReader dataReaderKg = new CSVDataReader();
        DataReader dataReaderKm = new CSVDataReader();

        System.out.println(MSG_DIVIDER);

        // Reading files to MAPs
        Map<Integer, BigDecimal> pricePerKgMap = dataReaderKg.readData(PRICE_PER_KG_PATH);
        if (pricePerKgMap == null || pricePerKgMap.isEmpty()) {
            throw new FileNotFoundException(FILE_NOT_FOUND_MSG + PRICE_PER_KG_PATH);
        }
        Map<Integer, BigDecimal> pricePerKmMap = dataReaderKm.readData(PRICE_PER_KM_PATH);
        if (pricePerKmMap == null || pricePerKgMap.isEmpty()) {
            throw new FileNotFoundException(FILE_NOT_FOUND_MSG + PRICE_PER_KM_PATH);
        }

        // Receiving input values from console for weight & distance (q for input cancel)
        ReceiveValue receiveValue = new ReceiveValue();
        InputValue weightValue = receiveValue.receiveValue(REQUEST_WEIGHT_VALUE_MSG);
        if (weightValue.getType() == InputValue.Types.EXIT) return;
        InputValue distanceValue = receiveValue.receiveValue(REQUEST_DISTANCE_VALUE_MSG);
        if (distanceValue.getType() == InputValue.Types.EXIT) return;

        // Check & get correct values for weight & distance
        CorrectInputValue correctWeightValue = (CorrectInputValue) weightValue;
        CorrectInputValue correctDistanceValue = (CorrectInputValue) distanceValue;

        // Calculating price with weight & distance by PRICE_PER_**_PATH file values
        Calculate calculateWeightPrice = new CalculateDelivery();
        Calculate calculateDistancePrice = new CalculateDelivery();
        ((CalculateDelivery) calculateWeightPrice).calculcatePrice(correctWeightValue.getValue(), pricePerKgMap);
        calculatedPriceFromWeight = calculateWeightPrice.getValue();
        ((CalculateDelivery) calculateDistancePrice).calculcatePrice(correctDistanceValue.getValue(), pricePerKmMap);
        calculatedPriceFromDistance = calculateDistancePrice.getValue();

        // Print calculation result to console
        System.out.println(MSG_DIVIDER);
        System.out.println("Calculated price from Weight: " + calculatedPriceFromWeight);
        System.out.println("Calculated price from Distance: " + calculatedPriceFromDistance);
        System.out.println("Summary price: " + calculatedPriceFromDistance.add(calculatedPriceFromWeight));
        System.out.println(MSG_DIVIDER);
    }


}
