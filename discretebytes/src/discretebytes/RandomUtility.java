/*
 * The MIT License
 *
 * Copyright 2014 David Rathbun.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package discretebytes;

/**
 *
 * @author David 'dracoix' Rathbun
 */
public class RandomUtility {

    public static long flashUniqueHash() {
        return UniqueHash.toDigestedLong(System.nanoTime());
    }

    public static long flashRandomHash() {
        return RandomHash.flash();
    }

    public static class Slow {

        public static long asLong() {
            return flashUniqueHash();
        }

        public static long asLong(long seed) {
            return UniqueHash.toDigestedLong(seed);
        }

        public static byte asByte() {
            return DiscreteHash.toByte(asLong());
        }

        public static byte asByte(long seed) {
            return DiscreteHash.toByte(asLong(seed));
        }

        public static short asShort() {
            return DiscreteHash.toShort(asLong());
        }

        public static short asShort(long seed) {
            return DiscreteHash.toShort(asLong(seed));
        }

        public static int asInteger() {
            return DiscreteHash.toInteger(asLong());
        }

        public static int asInteger(long seed) {
            return DiscreteHash.toInteger(asLong(seed));
        }

        public static float asFloat() {
            return DiscreteHash.toFloat(asLong());
        }

        public static float asFloat(long seed) {
            return DiscreteHash.toFloat(asLong(seed));
        }

        public static double asDouble() {
            return DiscreteHash.toDouble(asLong());
        }

        public static double asDouble(long seed) {
            return DiscreteHash.toDouble(asLong(seed));
        }

        public static boolean asBoolean() {
            return DiscreteHash.toBoolean(asLong());
        }

        public static boolean asBoolean(long seed) {
            return DiscreteHash.toBoolean(asLong(seed));
        }
    }

    public static class Fast {

        public static long asLong() {
            return flashRandomHash();
        }

        public static long asLong(long seed) {
            return RandomHash.seed(seed);
        }

        public static byte asByte() {
            return DiscreteHash.toByte(asLong());
        }

        public static byte asByte(long seed) {
            return DiscreteHash.toByte(asLong(seed));
        }

        public static short asShort() {
            return DiscreteHash.toShort(asLong());
        }

        public static short asShort(long seed) {
            return DiscreteHash.toShort(asLong(seed));
        }

        public static int asInteger() {
            return DiscreteHash.toInteger(asLong());
        }

        public static int asInteger(long seed) {
            return DiscreteHash.toInteger(asLong(seed));
        }

        public static float asFloat() {
            return DiscreteHash.toFloat(asLong());
        }

        public static float asFloat(long seed) {
            return DiscreteHash.toFloat(asLong(seed));
        }

        public static double asDouble() {
            return DiscreteHash.toDouble(asLong());
        }

        public static double asDouble(long seed) {
            return DiscreteHash.toDouble(asLong(seed));
        }

        public static boolean asBoolean() {
            return DiscreteHash.toBoolean(asLong());
        }

        public static boolean asBoolean(long seed) {
            return DiscreteHash.toBoolean(asLong(seed));
        }
    }

    public class Pump {

        private long seed;
        private long drip;

        public Pump(long seed) {
            this.seed = seed;
            this.drip = Slow.asLong(seed);
        }

        public void pump() {
            this.drip = Fast.asLong(this.seed ^ this.drip);
        }
        
        public long drip()
        {
            return drip;
        }
    }

    public class Daemon extends Pump {

        public Daemon(long seed) {
            super(seed);
        }

        public short nextByte() {
            pump();
            return DiscreteHash.toByte(this.drip());
        }

        public short nextShort() {
            pump();
            return DiscreteHash.toShort(this.drip());
        }

        public int nextInteger() {
            pump();
            return DiscreteHash.toInteger(this.drip());
        }

        public long nextLong() {
            pump();
            return this.drip();
        }

        public double nextFloat() {
            pump();
            return DiscreteHash.toFloat(this.drip());
        }

        public double nextDouble() {
            pump();
            return DiscreteHash.toDouble(this.drip());
        }

        public boolean nextBoolean() {
            pump();
            return DiscreteHash.toBoolean(this.drip());
        }
    }
}
