fun main() {
    println("type 'q' or 'quit' to quit.\n")

    fun String.parseVariable(): Pair<String?, String> {
        if ('=' in this) {
            return this
                .split('=')
                .map(String::trim)
                .let { it[0] to it[1] }
        }
        return null to this
    }

    fun String.insertVariables(variables: Map<String, String>): String {
        var res = this
        for ((name, value) in variables) {
            res = this.replace(name, value)
        }
        return res
    }

    val variables = mutableMapOf<String, String>()

    while (true) {
        val (variable, expression) = (readLine() ?: "").parseVariable()
        if (expression == "quit" || expression == "q") break

        val result = expression
            .insertVariables(variables)
            .replace("now", Time.now.toString(), ignoreCase = true)
            .toTokens()
            .toPostFix()
            .let(::calcTime)

        if (variable != null) {
            variables[variable] = result
        }
        println("  = $result\n")
    }
}
