package calculate;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;


public class CalculateDelivery implements Calculate {

    private BigDecimal calculateMultiplyResult;



    @Override
    public void mainCalculate(BigDecimal multiplier, BigDecimal multiplicand) {
        calculateMultiplyResult = multiplier.multiply(multiplicand);
        return;
    }

    @Override
    public BigDecimal getValue() {
        return calculateMultiplyResult;
    }

    public void CalculateX(BigDecimal weight, BigDecimal distance, Map<Integer, BigDecimal> map) {
        map.entrySet().stream().filter(mp->mp.getKey().intValue() >= weight.intValue())
                .collect(Collectors.toMap(mp->mp.getKey(),mp->mp.getValue()));
    }

}