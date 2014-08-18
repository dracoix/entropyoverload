package discretebytes;

import static discretebytes.DiscreteHash.*;
import static discretebytes.RandomHash.*;
import static discretebytes.RandomUtility.*;
import discretebytes.RandomUtility.*;
import discretebytes.RandomUtility.BasicPump;
import discretebytes.RandomUtility.LargePump;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class TestBed {

    public static void main(String[] args) {

        RandomUtility ru = new RandomUtility();

        LargePump lp = ru.new LargePump(System.nanoTime());

        long d = 0;
        BigDecimal bi = new BigDecimal(0, MathContext.UNLIMITED);

        while (d >= 0) {
            d++;
            bi = bi.add(BigDecimal.valueOf((toBoolean((lp.spill()))) ? 1 : 0));
            if ((d % Short.MAX_VALUE) == 0) {

                System.out.println(bi.divide(new BigDecimal(d, MathContext.UNLIMITED), Integer.MAX_VALUE/11256, RoundingMode.HALF_UP).doubleValue());
            }
        }
        //bi.multiply(BigInteger.valueOf(1));

    }

}
