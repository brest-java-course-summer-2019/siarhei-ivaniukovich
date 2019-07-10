package files;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public interface DataReader {
    Map<Integer, BigDecimal> readData(final String filePath) throws IOException;
}