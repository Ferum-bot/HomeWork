import root.GCD
import kotlin.test.Test
import kotlin.test.assertEquals

class GCDTest {

    private val gcd: GCD
    get() = GCD()

    @Test
    fun `test positive values of parameters`() {
        val firstParameter = 5
        val secondParameter = 15

        val actualResult = gcd.gcd(firstParameter, secondParameter)
        val expectedResult = 5

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `test negative and positive values of parameters`() {
        val firstParameter = -5
        val secondParameter = 20

        val actualResult = gcd.gcd(firstParameter, secondParameter)
        val expectedResult = 5

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `test negative values of parameters`() {
        val firstParameter = -3
        val secondParameter = -9

        val actualResult = gcd.gcd(firstParameter, secondParameter)
        val expectedResult = 3

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `test zero values of parameters`() {
        val firstParameter1 = 0
        val secondParameter1 = 122

        val firstParameter2 = 8
        val secondParameter2 = 0

        val firstParameter3 = 0
        val secondParameter3 = 0

        val actualResult1 = gcd.gcd(firstParameter1, secondParameter1)
        val expectedResult1 = secondParameter1

        val actualResult2 = gcd.gcd(firstParameter2, secondParameter2)
        val expectedResult2 = firstParameter2

        val actualResult3 = gcd.gcd(firstParameter3, secondParameter3)
        val expectedResult3 = 0

        assertEquals(expectedResult1, actualResult1)
        assertEquals(expectedResult2, actualResult2)
        assertEquals(expectedResult3, actualResult3)
    }

    @Test
    fun `test mutually simple arguments`() {
        val firstParameter1 = 17
        val secondParameter1 = 31

        val firstParameter2 = 3
        val secondParameter2 = 8

        val actualResult1 = gcd.gcd(firstParameter1, secondParameter1)
        val expectedResult1 = 1

        val actualResult2 = gcd.gcd(firstParameter2, secondParameter2)
        val expectedResult2 = 1

        assertEquals(expectedResult1, actualResult1)
        assertEquals(expectedResult2, actualResult2)
    }

    @Test
    fun `test equal arguments`() {
        val firstParameter = 8
        val secondParameter = 8

        val actualResult = gcd.gcd(firstParameter, secondParameter)
        val expectedResult = 8

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `test when first argument divides second and on the contrary`() {
        val firstParameter1 = 10
        val secondParameter1 = 5

        val firstParameter2 = 8
        val secondParameter2 = 24

        val actualResult1 = gcd.gcd(firstParameter1, secondParameter1)
        val expectedResult1 = 5

        val actualResult2 = gcd.gcd(firstParameter2, secondParameter2)
        val expectedResult2 = 8

        assertEquals(expectedResult1, actualResult1)
        assertEquals(expectedResult2, actualResult2)
    }

    @Test
    fun `test non equals arguments`() {
        val firstParameter = 24
        val secondParameter = 16

        val actualResult = gcd.gcd(firstParameter, secondParameter)
        val expectedResult = 8

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `test boundary values of arguments`() {
        val firstParameter1 = Int.MIN_VALUE
        val secondParameter1 = 7

        val firstParameter2 = -3
        val secondParameter2 = Int.MAX_VALUE

        val actualResult1 = gcd.gcd(firstParameter1, secondParameter1)
        val expectedResult1 = 1

        val actualResult2 = gcd.gcd(firstParameter2, secondParameter2)
        val expectedResult2 = 1

        assertEquals(expectedResult1, actualResult1)
        assertEquals(expectedResult2, actualResult2)
    }
}