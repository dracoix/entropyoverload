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
