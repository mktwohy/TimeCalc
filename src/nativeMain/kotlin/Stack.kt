typealias Stack<T> = ArrayDeque<T>
inline fun <T> Stack<T>.push(element: T) = addLast(element)
inline fun <T> Stack<T>.pop(): T? = removeLastOrNull()
inline fun <T> Stack<T>.peek(): T? = lastOrNull()

//class MyStack<T>(private val arrayDeque: ArrayDeque<T> = ArrayDeque()): MutableList<T> by arrayDeque {
//    fun push(element: T) = arrayDeque.addLast(element)
//    fun pop(): T? = arrayDeque.removeLastOrNull()
//    fun peek(): T? = arrayDeque.lastOrNull()
//}