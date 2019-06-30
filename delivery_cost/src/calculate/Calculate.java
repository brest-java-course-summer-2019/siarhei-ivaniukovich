package calculate;

import java.math.BigDecimal;

public interface Calculate {
    void mainCalculate(BigDecimal paramOne, BigDecimal paramTwo);
    BigDecimal getValue();
}
