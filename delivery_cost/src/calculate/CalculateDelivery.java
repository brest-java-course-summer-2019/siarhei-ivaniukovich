package calculate;

import java.math.BigDecimal;
import java.util.Map;

public class CalculateDelivery implements Calculate {

    private BigDecimal calculateMultiplyResult;

   /* @Override
    public void mainCalculate(BigDecimal multiplier, BigDecimal multiplicand) {
        calculateMultiplyResult = multiplier.multiply(multiplicand);
        return;
    }*/

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
        returnableValue = inputPriceMap.entrySet()
                .stream()
                .filter(mp -> mp.getKey().intValue() >= inputValue.intValue())
                .findFirst().get().getValue();

        return returnableValue;
    }

}