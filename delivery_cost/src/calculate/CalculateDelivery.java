package calculate;

import java.math.BigDecimal;
import java.util.Map;
import java.util.NoSuchElementException;

public class CalculateDelivery implements Calculate {

    private BigDecimal calculateMultiplyResult;

    @Override
    public void calculcatePrice(BigDecimal inputValue, Map<Integer, BigDecimal> inputPriceMap) {
        calculateMultiplyResult = inputValue.multiply(getValueByKeyFromPriceMap(inputValue, inputPriceMap));
        return;
    }

    @Override
    public BigDecimal getValue() {
        return calculateMultiplyResult;
    }

    private BigDecimal getValueByKeyFromPriceMap(BigDecimal inputValue, Map<Integer, BigDecimal> inputPriceMap) {
        BigDecimal returnableValue;
        try {
            returnableValue = inputPriceMap
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.<Integer, BigDecimal>comparingByKey())
                    .filter(map -> map.getKey().intValue() >= inputValue.intValue())
                    .findFirst()
                    .get()
                    .getValue();
        }
        catch (NoSuchElementException exception) {
            returnableValue = inputPriceMap
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.<Integer, BigDecimal>comparingByKey())
                    .reduce((a,b) -> b)
                    .get()
                    .getValue();
        }
        return returnableValue;
    }

}