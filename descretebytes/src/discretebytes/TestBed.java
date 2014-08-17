package discretebytes;

import static discretebytes.DiscreteHash.*;
import static discretebytes.RandomHash.*;

public class TestBed {

    public static void main(String[] args) {

        long p = encode(RandomHash.flash());
        System.out.println(StringUtility.toBinaryString(p));
        while (crush(p) > Integer.MAX_VALUE) {
            p = crush(p);
            System.out.println(StringUtility.toBinaryString(p));
        }

        while (p > 0) {
            p = explode(p);
            System.out.println(StringUtility.toBinaryString(p));
        }
    }

}
