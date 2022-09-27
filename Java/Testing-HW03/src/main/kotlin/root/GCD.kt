package root

/**
 * Copyright (c) 2009 ISP RAS.
 * 109004, A. Solzhenitsina, 25, Moscow, Russia.
 * All rights reserved.
 *
 * $Id$
 * Created on Dec 22, 2015
 *
 */
/**
 * @author Victor Kuliamin
 */
class GCD {
    fun gcd(x: Int, y: Int): Int {
        var x = x
        var y = y
        var t: Int
        if (x < 0) x = -x
        if (y < 0) y = -y
        while (y != 0) {
            if (y > x) {
                x = y - x
                y = y - x
                x = x + y
            }
            if (y == 0) return x
            t = y
            y = x % y
            x = t
        }
        return x
    }
}