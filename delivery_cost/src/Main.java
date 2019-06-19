import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // 12345
        BigDecimal weight;
        BigDecimal distance = new BigDecimal("10000");
        BigDecimal pricePerKg = new BigDecimal("30");
        BigDecimal pricePerKm = new BigDecimal("50");

        Scanner scanner = new Scanner(System.in);

        // WEIGHT INPUT:
        System.out.println("Enter the weight in kg, or press \"q\" to exit");
        String inputString = scanner.nextLine();
        if (!inputString.toLowerCase().equals("q")) {
            weight = new BigDecimal(inputString);
        }
        else {
            System.out.println("\nBye!");
            return;
        }


        // DISTANCE INPUT:
        System.out.println("Enter the distance in km, or press \"q\" to exit");
        inputString = scanner.nextLine();
        if (!inputString.toLowerCase().equals("q")) {
            distance = new BigDecimal(inputString);
        }
        else {
            System.out.println("\nBye!");
            return;
        }

        System.out.println("Weight: " + weight);
        System.out.println("Distance: " + distance);

        BigDecimal price = weight.multiply(pricePerKg).add(distance.multiply(pricePerKm));
        System.out.println("result: " + price);


    }
}