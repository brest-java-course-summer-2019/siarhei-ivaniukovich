import files.CSVDataReader;
import files.DataReader;
import input.CorrectInputValue;
import input.InputValue;
import input.ReceiveValue;
import calculate.Calculate;
import calculate.CalculateDelivery;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;


public class Main {

    public static final String PRICE_PER_KM_PATH = "price_per_kg.csv";

    public static void main(String[] args) throws IOException {

        DataReader dataReader = new CSVDataReader();
        Map<Integer, BigDecimal> pricePerKgMap = dataReader.readData(PRICE_PER_KM_PATH);
        if (pricePerKgMap == null || pricePerKgMap.isEmpty()) {
            throw new FileNotFoundException("File price_per_kg.csv is not found.");
        }

        ReceiveValue receiveValue = new ReceiveValue();
        InputValue weightValue = receiveValue.receiveValue("Enter weight in kg or 'q' for cancel: ");
        InputValue distanceValue = receiveValue.receiveValue("Enter distance in km  or 'q' for cancel: ");

        System.out.println("###: "+pricePerKgMap.get(100));

        CorrectInputValue correctWeightValue = (CorrectInputValue) weightValue;
        CorrectInputValue correctDistanceValue = (CorrectInputValue) distanceValue;
        System.out.println("Weight: " + correctWeightValue.getValue());
        System.out.println("Distance: " + correctDistanceValue.getValue());
        System.out.println("$$$: "+pricePerKgMap.entrySet().stream().filter(mp->mp.getKey().intValue() >= correctWeightValue.getValue().intValue())
                .collect(Collectors.toMap(mp->mp.getKey(), mp->mp.getValue())));
        System.out.println("DOT: "+pricePerKgMap.entrySet().stream()
                .filter(mp->mp.getKey().intValue() >= correctWeightValue.getValue().intValue())
                .collect(Collectors.toMap(mp->mp.getKey(), mp->mp.getValue())));

        //BigDecimal price = weight.multiply(pricePerKg).add(distance.multiply(pricePerKm));
        //System.out.println("result: " + price);
       /* // Vars
        BigDecimal weight;
        BigDecimal distance = new BigDecimal("10000");
        BigDecimal pricePerKg = new BigDecimal("30");
        BigDecimal pricePerKm = new BigDecimal("50");


        // Added with inputMethod:
        weight=inputMethod(1);
        if(weight.equals(BigDecimal.ZERO))
            return;
        distance=inputMethod(2);
        if(distance.equals(BigDecimal.ZERO))
            return;

        // Legacy code next:
        System.out.println("Weight: " + weight);
        System.out.println("Distance: " + distance);

        BigDecimal price = weight.multiply(pricePerKg).add(distance.multiply(pricePerKm));
        System.out.println("result: " + price);
        */
    }


}
