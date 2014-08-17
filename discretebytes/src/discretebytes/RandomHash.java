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
public class RandomHash {
    /* 
     Original concept by 'Marsaglia, G. 2003 XORShift'
    
     Research by Sebastiano Vigna - 
     "An experimental exploration of Marsaglia's xorshift generators, scrambled"
    
     Expanded by Rathbun D.
     */

    private static final long M32 = 2685821657736338717L;
    
    public static long flash() {
        return seed(System.nanoTime());
    }

    public static long snap() {
        return seed(System.currentTimeMillis());
    }
    
    public static long seed(long x) {
        return xorshift(x) * M32;
    } 

    public static long encode(long x) {
        return xorshift(x);
    }

    public static long decode(long x) {
        return xorshift_inverse(x);
    }

    private static long xorshift(long x) {
        x ^= x >>> 12;
        x ^= x << 25;
        x ^= x >>> 27;
        return x;
    }

    private static long xorshift_inverse(long x) {
        x ^= x << 27;
        x ^= x >>> 25;
        x ^= x << 12;
        return x;
    }
    
}
