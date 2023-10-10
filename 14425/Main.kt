import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val algorithm: Algorithm = BufferedReader(InputStreamReader(System.`in`)).use {
        val line = it.readLine()
        val N = line.split(" ")[0].toInt()
        val M = line.split(" ")[1].toInt()
        val strings: MutableList<String> = mutableListOf()
        for (i in 1..N + M) {
            strings.add(it.readLine())
        }
        Algorithm(N, M, strings)
    }

    println(algorithm.solve())
}

class Algorithm(val N: Int, val M: Int, strings: List<String>) {

    private val dict: MutableSet<String> = mutableSetOf()
    private val uncheckedStrings: MutableList<String> = mutableListOf()

    init {
        strings.withIndex().forEach {
            when {
                it.index < N -> dict.add(it.value)
                else -> uncheckedStrings.add(it.value)
            }
        }
    }

    fun solve(): Int {
        var containCount = 0
        uncheckedStrings.forEach {
            if (dict.contains(it)) {
                containCount++
            }
        }
        return containCount;
    }

}
