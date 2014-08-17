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

import java.nio.ByteBuffer;

/**
 *
 * @author David 'dracoix' Rathbun
 */
public class StringUtility {

    // Proper padded representations of data literals.
    // This is exploded for optimized use.
    private static final String[] hexbin = {
        "0000", "0001", "0010", "0011",
        "0100", "0101", "0110", "0111",
        "1000", "1001", "1010", "1011",
        "1100", "1101", "1110", "1111",};

    public static String toBinaryString(byte[] b) {
        // Big Endian
        String o = "";
        for (int i = 0; i < b.length; i++) {
            o += hexbin[((b[i] & 255) >>> 4)] + hexbin[(b[i] & 15)];
        }
        return o;
    }

    public static String toHexadecimal(byte[] b) {
        String o = "";
        for (int i = 0; i < b.length; i++) {
            o += (String.format("%02x", b[i]));
        }
        return o;
    }

    public static String toHexadecimal(byte a) {
        return toHexadecimal(ByteBuffer.wrap(new byte[1]).put(a).array());
    }

    public static String toHexadecimal(short a) {
        return toHexadecimal(ByteBuffer.wrap(new byte[2]).putShort(a).array());
    }

    public static String toHexadecimal(int a) {
        return toHexadecimal(ByteBuffer.wrap(new byte[4]).putInt(a).array());
    }

    public static String toHexadecimal(long a) {
        return toHexadecimal(ByteBuffer.wrap(new byte[8]).putLong(a).array());
    }

    public static String toBinaryString(byte a) {
        return toBinaryString(ByteBuffer.wrap(new byte[1]).put(a).array());
    }

    public static String toBinaryString(short a) {
        return toBinaryString(ByteBuffer.wrap(new byte[2]).putShort(a).array());
    }

    public static String toBinaryString(int a) {
        return toBinaryString(ByteBuffer.wrap(new byte[4]).putInt(a).array());
    }

    public static String toBinaryString(long a) {
        return toBinaryString(ByteBuffer.wrap(new byte[8]).putLong(a).array());
    }

}
