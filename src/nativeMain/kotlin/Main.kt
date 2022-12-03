fun main(args: Array<String>) {
    when {
        args.isEmpty() -> run()
        else -> "$args is not a legal argument"
    }
}

fun run() {
    println("type 'q' or 'quit' to quit.\n")

    while (true) {
        val input = readLine() ?: ""
        if (input == "quit" || input == "q") break

        val expression = input.splitInput().toPostFix()
        val result = calcTime(expression)
        println("  = $result\n")
    }
}

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

fun String.splitInput(): List<String> =
    this.splitWithDelimiter('(', ')', '+', '-')
        .map { it.trim() }
        .filter { it.isNotBlank() }

/** An implementation of the [Shunting Yard Algorithm](https://en.wikipedia.org/wiki/Shunting_yard_algorithm) */
fun List<String>.toPostFix(operators: List<String> = listOf("+", "-")): List<String> {
    infix fun String?.hasGreaterPrecedenceThan(other: String?): Boolean {
        val thisIndex = operators.indexOfOrNull(this) ?: return false
        val otherIndex = operators.indexOfOrNull(other) ?: return true
        return thisIndex < otherIndex
    }

    val operatorStack = Stack<String>()
    val outputQueue = Queue<String>()

    fun popOperatorToOutput() = operatorStack.pop()?.let { outputQueue.enqueue(it) }

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
