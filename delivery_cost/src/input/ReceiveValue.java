package input;

//import com.sun.tools.jdeprscan.scan.Scan;
import input.InputValue;
import input.CorrectInputValue;
import input.IncorrectInputValue;
import input.ExitInputValue;


import java.util.Scanner;
import java.math.BigDecimal;


public class ReceiveValue {

    public static final String QUIT_SYMBOL = "q";

    public InputValue receiveValue(String message) {
        Scanner scanner = new Scanner(System.in);
        InputValue inputValue = new IncorrectInputValue();
        while (inputValue.getType() == InputValue.Types.INCORRECT) {
            System.out.print(message);
            inputValue = parseReceivedValue(scanner.nextLine());
        }
        return inputValue;
    }

    private InputValue parseReceivedValue(String inputValue) {
        InputValue inputValueResult = new ExitInputValue();
        if (!inputValue.trim().toLowerCase().equals(QUIT_SYMBOL)) {
            try {
                BigDecimal value = new BigDecimal(inputValue);
                if (value.compareTo(BigDecimal.ZERO) > 0) {
                    inputValueResult = new CorrectInputValue(new BigDecimal(inputValue));
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.format("Incorrect value: %s%n", inputValue);
                inputValueResult = new IncorrectInputValue();
            }
        }
        return inputValueResult;
    }

}