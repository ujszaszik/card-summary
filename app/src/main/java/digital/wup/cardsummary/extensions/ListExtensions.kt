package digital.wup.cardsummary.extensions

fun <T> List<T>.first(): T {
    if (size > zero()) return first()
    else throw UnsupportedOperationException()
}

fun <T> List<T>.second(): T {
    if (size > one()) return get(1)
    else throw UnsupportedOperationException()
}

fun <T> List<T>.third(): T {
    if (size > two()) return get(2)
    else throw UnsupportedOperationException()
}

fun <T> List<T>.replaceIfIndexIsLowerThen(maxIndex: Int, block: (T) -> T): List<T> {
    val result = mutableListOf<T>()
    forEachIndexed { index, t ->
        if (index < maxIndex) result.add(block.invoke(t))
        else result.add(t)
    }
    return result
}

fun List<String>.concat(delimiter: String): String {
    return reduce { acc, s -> acc + delimiter + s }
}