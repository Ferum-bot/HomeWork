import kotlin.math.abs

/**
 * Copyright (c) 2009 ISP RAS.
 * 109004, A. Solzhenitsina, 25, Moscow, Russia.
 * All rights reserved.
 *
 * $Id$
 * Created on 08.10.2009
 *
 */
/**
 * @author Victor Kuliamin
 */
class Account {
    var isBlocked = false
    var bound = 1000000
    var balance = 0
    var maxCredit = -1000
    fun deposit(sum: Int): Boolean {
        if (abs(balance + sum) > bound) {
            return false
        }
        return if (isBlocked) false else if (sum < 0 || sum > bound) false else {
            balance += sum
            true
        }
    }

    fun withdraw(sum: Int): Boolean {
        if (abs(balance + sum) > bound) {
            return false
        }
        return if (isBlocked) false else if (sum < 0 || sum > bound) false else if (balance <= maxCredit + sum) false else {
            balance -= sum
            true
        }
    }

    @JvmName("getMaxCredit1")
    fun getMaxCredit(): Int {
        return -maxCredit
    }

    fun block() {
        isBlocked = true
    }

    fun unblock(): Boolean {
        if (balance < maxCredit) return false else isBlocked = false
        return true
    }

    fun setMaxCredit(mc: Int): Boolean {
        if (!isBlocked) {
            return false
        }
        maxCredit = if (mc < -bound || mc > bound) return false else -mc
        return true
    }
}