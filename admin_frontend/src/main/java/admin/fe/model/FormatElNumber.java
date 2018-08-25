package admin.fe.model;

import java.text.NumberFormat;
/*utility class of custom xel-method */
public class FormatElNumber {
    public static String formatStock(double stock) {
        final NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        return nf.format(stock);
    }
}
