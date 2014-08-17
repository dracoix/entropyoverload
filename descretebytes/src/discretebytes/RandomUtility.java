/*
 * Copyright (C) 2014 David 'dracoix' Rathbun
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
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
