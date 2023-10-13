import java.io.BufferedReader
import java.io.InputStreamReader

const val MAX_PRIME_NUM = 9000 * 2
var N = 0
var M = 0
lateinit var cows: List<Int>
val checked = Array(9) { false }
val isPrime = Array(MAX_PRIME_NUM) { 0 }
val allPossibleWeights: MutableSet<Int> = mutableSetOf()

fun main() {
    input()
    markPrimeNumber()
    makeAllPossibleWeights(0, 0)
    when (allPossibleWeights.size) {
        0 -> println(-1)
        else -> allPossibleWeights.sorted().forEach { print("${it} ") }
    }
}

fun input() = BufferedReader(InputStreamReader(System.`in`))
    .use { reader ->
        val line = reader.readLine().split(" ").map { it.toInt() }
        N = line[0]
        M = line[1]
        cows = reader.readLine().split(" ").map { it.toInt() }
    }

fun markPrimeNumber() {
    for (i in 2 until MAX_PRIME_NUM) {
        if (isPrime[i] == 0) {
            isPrime[i] = 1
            for (j: Int in i + i until MAX_PRIME_NUM step (i)) {
                isPrime[j] = -1
            }
        }
    }
}

fun makeAllPossibleWeights(sum: Int, selected: Int) {
    if (selected == M) {
        if (isPrime[sum] == 1) {
            allPossibleWeights.add(sum)
        }
        return
    }
    for (i in cows.indices) {
        if (checked[i]) {
            continue
        }
        checked[i] = true
        makeAllPossibleWeights(sum + cows[i], selected + 1)
        checked[i] = false
    }
}
