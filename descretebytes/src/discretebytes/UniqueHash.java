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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David 'dracoix' Rathbun
 */
public class UniqueHash {

    private static final String strSha = "SHA-256";

    private static byte[] digest(byte[] b) {
        try {
            return MessageDigest.getInstance(strSha).digest(b);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UniqueHash.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private static byte[] digest(long a) {
        return digest(ByteBuffer.allocate(8).putLong(a).array());
    }

    private static byte[] digest(String s) {
        return digest(s.getBytes());
    }

    public static byte[] toDigested8Bytes(String s) {
        return ByteBuffer.allocate(8).putLong(toDigestedLong(s)).array();
    }

    public static byte[] toDigested8Bytes(byte[] b) {
        return ByteBuffer.allocate(8).putLong(toDigestedLong(b)).array();
    }

    public static byte[] toDigested32Bytes(String s) {
        return digest(s);
    }

    public static byte[] toDigested32Bytes(byte[] b) {
        return digest(b);
    }

    public static String toDigestedString(long a) {
        return StringUtility.toHexadecimal(digest(a));
    }

    public static String toDigestedString(String s) {
        return StringUtility.toHexadecimal(digest(s));
    }

    public static String toDigestedString(byte[] b) {
        return StringUtility.toHexadecimal(digest(b));
    }

    public static long toDigestedLong(long a) {
        return DiscreteHash.toLong(digest(a));
    }

    public static long toDigestedLong(String s) {
        return DiscreteHash.toLong(digest(s));
    }

    public static long toDigestedLong(byte[] b) {
        return DiscreteHash.toLong(digest(b));
    }

    public static String toDigestedLongString(String s) {
        return StringUtility.toHexadecimal(toDigestedLong(s));
    }

    public static String toDigestedLongString(byte[] b) {
        return StringUtility.toHexadecimal(toDigestedLong(b));
    }
}
