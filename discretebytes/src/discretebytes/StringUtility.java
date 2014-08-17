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
