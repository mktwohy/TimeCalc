import kotlin.test.Test
import kotlin.test.assertEquals

class CalcTest {
    @Test
    fun test1() {
        val input = "1:00am + 2".toTokens().toPostFix()
        val expected = "3:00am"
        val actual = calcTime(input)
        assertEquals(expected, actual)
    }

    @Test
    fun test2() {
        val input = "1:00pm + (2 - 1)".toTokens().toPostFix()
        val expected = "2:00pm"
        val actual = calcTime(input)
        assertEquals(expected, actual)
    }

    @Test
    fun test3() {
        val input = "8:24am + (8:40 - 1:10)".toTokens().toPostFix()
        val expected = "3:54pm"
        val actual = calcTime(input)
        assertEquals(expected, actual)
    }
}