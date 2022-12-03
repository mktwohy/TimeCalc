fun <T> List<T>.indexOfOrNull(element: T) =
    this.indexOf(element).let { if (it == -1) null else it }

fun String.splitWithDelimiter(vararg delimiters: Char): List<String> {
    val delimiterStr = buildString { delimiters.forEach { append(it) } }
    val reg = Regex("(?<=[$delimiterStr])|(?=[$delimiterStr])")
    return this.split(reg)
}

fun <T> Array<T>.elementAfterOrNull(element: T): T? =
    this.toList().elementAfterOrNull(element)

fun <T> List<T>.elementAfterOrNull(element: T): T? =
    this.indexOfOrNull(element)?.let { index ->
        this.elementAtOrNull(index + 1)
    }
