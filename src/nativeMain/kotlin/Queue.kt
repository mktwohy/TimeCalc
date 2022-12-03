typealias Queue<T> = ArrayDeque<T>
inline fun <T> Queue<T>.enqueue(element: T) = addLast(element)
inline fun <T> Queue<T>.dequeue(): T? = removeFirst()
//inline fun <T> Queue<T>.peek(): T? = firstOrNull()