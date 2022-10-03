import org.junit.jupiter.api.Assertions.*
import kotlin.math.max
import kotlin.properties.Delegates
import kotlin.test.Test
import kotlin.test.assertEquals

internal class AccountTest {

    private val account: Account by lazy { Account() }

    @Test
    fun `common getBalance() test`() {
        val actualBalance = account.balance
        val expectedBalance = 0

        assertEquals(expectedBalance, actualBalance)
    }

    @Test
    fun `common getMaxCredit() test`() {
        val actualMaxCredit = account.getMaxCredit()
        val expectedMaxCredit = 1000

        assertEquals(expectedMaxCredit, actualMaxCredit)
    }

    @Test
    fun `common isBlocked() test`() {
        val actualIsBlocked = account.isBlocked
        val expectedIsBlocked = false

        assertEquals(expectedIsBlocked, actualIsBlocked)
    }

    @Test
    fun `common block() test`() {
        account.block()

        val actualIsBlocked = account.isBlocked
        val expectedIsBlocked = true

        assertEquals(expectedIsBlocked, actualIsBlocked)
    }

    @Test
    fun `common unblock() test`() {
        account.unblock()

        val actualIsBlocked = account.isBlocked
        val expectedIsBlocked = false

        assertEquals(expectedIsBlocked, actualIsBlocked)
    }

    @Test
    fun `common deposit() test`() {
        val sum = 100

        val result = account.deposit(sum)

        val actualBalance = account.balance
        val expectedBalance = 100

        assertEquals(expectedBalance, actualBalance)
        assertTrue(result)
    }

    @Test
    fun `common withdraw() test`() {
        val sum = 100

        val result = account.withdraw(sum)

        val actualBalance = account.balance
        val expectedBalance = -100

        assertEquals(expectedBalance, actualBalance)
        assertTrue(result)
    }

    @Test
    fun `common setMaxCredit() test`() {
        account.isBlocked = true
        val maxCredit = 2000

        val result = account.setMaxCredit(maxCredit)

        val actualCredit = account.getMaxCredit()
        val expectedCredit = 2000

        assertEquals(expectedCredit, actualCredit)
        assertTrue(result)
    }

    @Test
    fun `account is not blocked and balance must be not less credit balance`() {
        account.balance = -900
        account.isBlocked = false
        val sum = 150

        val result = account.withdraw(sum)
        val actualBalance = account.balance
        val expectedBalance = -900

        assertEquals(expectedBalance, actualBalance)
        assertFalse(result)
    }

    @Test
    fun `credit balance changing is prohibited is non blocked state`() {
        account.isBlocked = false
        val creditBalance = 1500

        val result = account.setMaxCredit(creditBalance)
        val actualCreditMax = account.getMaxCredit()
        val expectedCreditMax = 1000

        assertFalse(result)
        assertEquals(expectedCreditMax, actualCreditMax)
    }

    @Test
    fun `excess of setMaxCredit() method parameter`() {
        account.isBlocked = true
        val sum = Int.MAX_VALUE

        val result = account.setMaxCredit(sum)
        val actualCreditMax = account.getMaxCredit()
        val expectedCreditMax = 1000

        assertFalse(result)
        assertEquals(expectedCreditMax, actualCreditMax)
    }

    @Test
    fun `account is blocked and setMaxCredit() method parameter is less than bound`() {
        account.isBlocked = true
        val sum = 1234

        val result = account.setMaxCredit(sum)
        val actualCreditMax = account.getMaxCredit()
        val expectedCreditMax = 1234

        assertTrue(result)
        assertEquals(expectedCreditMax, actualCreditMax)
    }

    @Test
    fun `account is blocked and unblock() works if current balance more than credit limit`() {
        account.isBlocked = true
        account.balance = -950

        val result = account.unblock()
        val actualResult = account.isBlocked
        val expectedResult = false

        assertTrue(result)
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `deposit() and withdraw() methods with negative arguments must return false`() {
        val depositSum = -100
        val withdrawSum = -2

        val depositResult = account.deposit(depositSum)
        val withdrawResult = account.withdraw(withdrawSum)

        val actualBalance = account.balance
        val expectedBalance = 0

        assertFalse(depositResult)
        assertFalse(withdrawResult)
        assertEquals(expectedBalance, actualBalance)
    }

    @Test
    fun `deposit() with arguments entailing a change in the score greater than bound`() {
        account.balance = account.bound - 10
        val depositSum = 15

        val result = account.deposit(depositSum)
        val actualBalance = account.balance
        val expectedBalance = account.bound - 10

        assertFalse(result)
        assertEquals(expectedBalance, actualBalance)
    }

    @Test
    fun `withdraw() with arguments entailing a change in the score greater than bound`() {
        account.balance = -account.bound - 10
        val withdrawSum = 15

        val result = account.withdraw(withdrawSum)
        val actualBalance = account.balance
        val expectedBalance = -account.bound - 10

        assertFalse(result)
        assertEquals(expectedBalance, actualBalance)
    }

    @Test
    fun `withdraw() and deposit() for blocked account`() {
        account.isBlocked = true
        val withdrawSum = 15
        val depositSum = 8

        val withdrawResult = account.withdraw(withdrawSum)
        val depositResult = account.deposit(depositSum)

        val actualBalance = account.balance
        val expectedBalance = 0

        assertFalse(withdrawResult)
        assertFalse(depositResult)
        assertEquals(expectedBalance, actualBalance)
    }
}