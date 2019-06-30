/*package input;

import java.math.BigDecimal;
import java.util.Scanner;

public class DeliveryDataReadInput implements InputReader {
    @Override

    ;
    /*public BigDecimal inputDeliveryData(int inputParameter)
    {
        // 1 - distance; 2 - weight; 0 - exit (return)
        BigDecimal deliveryDataResult;
        Scanner scanner = new Scanner(System.in);

        // Message:
        System.out.print("Enter the ");
        if (inputParameter == 1)
            System.out.print("distance in km ");
        else if (inputParameter == 2)
            System.out.print("weight in kg ");
        System.out.print("or press \"0\" to exit: ");
        // End of message

        // Read data
        String inputString = scanner.nextLine();
        if (inputString.length() < 1 || inputString.matches("^[a-zA-Z]+")) {
            System.out.println("Input error: chars and empty data are not allowed...");
            return BigDecimal.ZERO;
        } else if (!inputString.toLowerCase().equals("0")) {
            deliveryDataResult = new BigDecimal(inputString);
        } else {
            System.out.println("\nBye!");
            deliveryDataResult = new BigDecimal(BigDecimal.ZERO);
        }
        return deliveryDataResult;
    }*/