package input;

import java.math.BigDecimal;

public class CorrectInputValue implements InputValue{
        private BigDecimal value;

        public CorrectInputValue(BigDecimal value) {
            this.value = value;
        }

        public BigDecimal getValue() {
            return value;
        }

        @Override
        public Types getType() {
            return Types.VALUE;
        }
}
