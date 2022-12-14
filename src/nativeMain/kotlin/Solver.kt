fun calcTime(postFixExpression: List<String>): String {
    val stack = Stack<String>()

    for (token in postFixExpression) {
        when(token) {
            "+" -> {
                val a = stack.pop()!!.toTime()
                val b = stack.pop()!!.toTime()
                stack.push((b + a).toString())
            }
            "-" -> {
                val a = stack.pop()!!.toTime()
                val b = stack.pop()!!.toTime()
                stack.push((b - a).toString())
            }
            else -> stack.push(token)
        }
    }
    return stack.pop()!!
}

fun String.toTokens(): List<String> =
    this.splitWithDelimiter('(', ')', '+', '-')
        .map { it.trim() }
        .filter { it.isNotBlank() }

/** An implementation of the [Shunting Yard Algorithm](https://en.wikipedia.org/wiki/Shunting_yard_algorithm) */
fun List<String>.toPostFix(operators: List<String> = listOf("+", "-")): List<String> {
    val operatorStack = Stack<String>()
    val outputQueue = Queue<String>()

    infix fun String?.hasGreaterPrecedenceThan(other: String?): Boolean {
        val thisIndex = operators.indexOfOrNull(this) ?: return false
        val otherIndex = operators.indexOfOrNull(other) ?: return true
        return thisIndex < otherIndex
    }

    fun popOperatorToOutput() {
        operatorStack.pop()?.let { outputQueue.enqueue(it) }
    }

    for (token in this) {
        when(token) {
            "(" -> operatorStack.push(token)
            ")" -> {
                while (operatorStack.peek() != "(") {
                    popOperatorToOutput()
                }
                operatorStack.pop()
            }
            in operators -> {
                while(operatorStack.peek() hasGreaterPrecedenceThan token) {
                    popOperatorToOutput()
                }
                operatorStack.push(token)
            }
            else -> outputQueue.enqueue(token)
        }
    }
    while (operatorStack.peek() != null) {
        popOperatorToOutput()
    }
    return outputQueue
}
