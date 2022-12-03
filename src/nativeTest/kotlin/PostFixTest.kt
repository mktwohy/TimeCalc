import kotlin.test.Test
import kotlin.test.assertEquals

class PostFixTest {
    @Test
    fun test1() {
        val input = "1 + 2"
        val expected = listOf("1", "2", "+")
        val actual = input.splitInput().toPostFix()
        assertEquals(expected, actual)
    }

    @Test
    fun test2() {
        val input = "1 + 2 - 3"
        val expected = listOf("1", "2", "+", "3", "-")
        val actual = input.splitInput().toPostFix()
        assertEquals(expected, actual)
    }

    @Test
    fun test3() {
        val input = "1 + (2 - 3)"
        val expected = listOf("1", "2", "3", "-", "+")
        val actual = input.splitInput().toPostFix()
        assertEquals(expected, actual)
    }

    @Test
    fun test4() {
        val input = "1:00 + (2 - 3)"
        val expected = listOf("1:00", "2", "3", "-", "+")
        val actual = input.splitInput().toPostFix()
        assertEquals(expected, actual)
    }
}