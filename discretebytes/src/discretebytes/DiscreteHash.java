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
public class DiscreteHash {

    /**
     * Explodes hash by one bit and attempts to preserve uniqueness.
     *
     * @param hash the seed to use
     * @return a hash that is one bit longer
     */
    public static long explode(long hash) {
        long m = (getBitfieldMask(hash << 1));
        long k = m ^ ((m >>> 1));
        hash = RandomHash.decode(hash);
        hash = hash & m;                              // Apply Mask
        return hash | k;                           // Pad a lead bit (preserves container)
    }

    /**
     * Explodes hash by amount and attempts to preserve uniqueness.
     *
     * @param hash the seed to use
     * @param amount the number of bits to shift by
     * @return a hash that is longer by the amount provided
     */
    public static long explode(long hash, int amount) {
        long b = hash;
        for (int i = 0; i < amount; i++) {
            b = explode(hash);
        }
        return b;
    }

    /**
     * Crushes hash by one bit and attempts to preserve uniqueness.
     *
     * @param hash the seed to use
     * @return a hash that is one bit shorter
     */
    public static long crush(long hash) {
        long m = (getBitfieldMask(hash >>> 1));
        long k = m ^ ((m >>> 1));
        hash = RandomHash.encode(hash);
        hash = hash & m;                              // Apply Mask
        return hash | k;                           // Pad a lead bit (preserves container)
    }

    /**
     * Crushes hash by amount and attempts to preserve uniqueness.
     *
     * @param hash the seed to use
     * @param amount the number of bits to shift by
     * @return a hash that is shorter by the amount provided
     */
    public static long crush(long hash, int amount) {
        long b = hash;
        for (int i = 0; i < amount; i++) {
            b = crush(hash);
        }
        return b;
    }

    /**
     * Generates a filled mask representing the occupied space.
     *
     * @param data the value to generate a mask from.
     * @return a mask of ones with the bit length of the original data
     */
    public static long getBitfieldMask(long data) {
        long mask = data;
        for (int i = 0; i < 64; i++) {
            mask |= data >>> 1;
        }
        return mask;
    }

    public static byte toByte(short a) {
        return (byte) ((a >>> 8) ^ a);
    }

    public static byte toByte(int a) {
        return toByte(toShort(a));
    }

    public static byte toByte(long a) {
        return toByte(toShort(DiscreteHash.toInteger(a)));
    }

    public static short toShort(int a) {
        return (short) ((a >>> 16) ^ a);
    }

    public static short toShort(long a) {
        return toShort(DiscreteHash.toInteger(a));
    }

    public static int toInteger(long a) {
        return (int) ((a >>> 32) ^ a);
    }

    public static int toPositive(int a) {
        return (a < 0) ? -a : a;
    }

    public static long toPositive(long a) {
        return (a < 0) ? -a : a;
    }

    public static int toNegative(int a) {
        return (a < 0) ? a : -a;
    }

    public static long toNegative(long a) {
        return (a < 0) ? a : -a;
    }

    public static float toFloat(long a) {
        return (float) toPositive(DiscreteHash.toInteger(a)) / Integer.MAX_VALUE;
    }

    public static double toDouble(long a) {
        return (double) toPositive(DiscreteHash.toInteger(a)) / Integer.MAX_VALUE;
    }

    public static boolean toBoolean(long a) {
        return (DiscreteHash.toInteger(a) & 1) == 1;
    }

    /**
     * Generates a simple (non-crypto) 64 bit hash from a byte array. Recommended inputs are arrays
     * 16 elements or more.
     *
     * @param data the byte array to discrete compress
     * @return the representing hash of the data
     */
    public static long toLong(byte[] data) {
        // 
        long k = 0;     // Initialize output
        long c;         // Unsign mask correction
        int s;          // Shift amount
        for (int i = 0; i < data.length; i++) {
            s = 7 - (i % 8);    // Get current byte field position (preserves compatibility)
            s = s << 3;         // Multiply by 8
            c = data[i];        // Get byte at index
            c &= 255;           // Byte mask the long to prevent signed value
            c = (c << s);       // Shift to the correct byte position in the hash
            k ^= c;             // Combine
        }
        return k;
    }

    /**
     * Generates a simple (non-crypto) 32 bit hash from a byte array. Recommended inputs are arrays
     * 8 elements or more.
     *
     * @param data the byte array to discrete compress
     * @return the representing hash of the data
     */
    public static int toInteger(byte[] data) {
        int k = 0;      // Initialize output
        int c;          // Unsign mask correction
        int s;          // Shift amount
        for (int i = 0; i < data.length; i++) {
            s = 3 - (i % 4);    // Get current byte field position (preserves compatibility)
            s = s << 3;         // Multiply by 8
            c = data[i];        // Get byte at index
            c &= 255;           // Byte mask the long to prevent signed value
            c = (c << s);       // Shift to the correct byte field position in the hash
            k ^= c;             // Combine
        }
        return k;
    }

    /**
     * Generates a simple (non-crypto) 16 bit hash from a byte array. Recommended inputs are arrays
     * 4 elements or more.
     *
     * @param data the byte array to discrete compress
     * @return the representing hash of the data
     */
    public static short toShort(byte[] data) {
        int k = 0;      // Initialize output
        int c;          // Unsign mask correction
        int s;          // Shift amount
        for (int i = 0; i < data.length; i++) {
            s = 1 - (i % 2);     // Get current byte field position (preserves compatibility)
            s = s << 3;         // Multiply by 8
            c = data[i];        // Get byte at index
            c &= 255;           // Byte mask the long to prevent signed value
            c = (c << s);       // Shift to the correct byte field position in the hash
            k ^= c;             // Combine
        }
        return (short) (k & 65535);
    }

    /**
     * Generates a simple (non-crypto) 8 bit hash from a byte array. Recommended inputs are arrays 2
     * elements or more.
     *
     * @param data the byte array to discrete compress
     * @return the representing hash of the data
     */
    public static byte toByte(byte[] data) {
        int k = 0;      // Initialize output
        for (int i = 0; i < data.length; i++) {
            k ^= data[i];       // Combine
        }
        return (byte) (k & 255);
    }
}
