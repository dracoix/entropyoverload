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

    public static byte[] toDigested8Bytes(long a) {
        return ByteBuffer.allocate(8).putLong(toDigestedLong(a)).array();
    }

    public static byte[] toDigested8Bytes(String s) {
        return ByteBuffer.allocate(8).putLong(toDigestedLong(s)).array();
    }

    public static byte[] toDigested8Bytes(byte[] b) {
        return ByteBuffer.allocate(8).putLong(toDigestedLong(b)).array();
    }

    public static byte[] toDigested32Bytes(long a) {
        return digest(a);
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
