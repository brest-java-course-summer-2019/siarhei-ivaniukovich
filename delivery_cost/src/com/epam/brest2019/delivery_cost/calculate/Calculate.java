package calculate;

import java.math.BigDecimal;
import java.util.Map;

public interface Calculate {
    public void calculcatePrice(BigDecimal inputValue, Map<Integer, BigDecimal> inputPriceMap);
    BigDecimal getValue();
}
