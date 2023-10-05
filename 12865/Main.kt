import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    var line = reader.readLine().split(" ")
    val N = line[0].toInt()
    val K = line[1].toInt()

    val stuffs: MutableList<Pair<Int, Int>> = mutableListOf()
    for (i in 1..N) {
        line = reader.readLine().split(" ")
        stuffs.add(line[0].toInt() to line[1].toInt())
    }
    stuffs.sortWith { o1, o2 -> o1.first.compareTo(o2.first) }

    println(Algorithm(N, K, stuffs).run())
}

class Algorithm(
    private val N: Int,
    private val maxWeight: Int,
    private val stuffs: List<Pair<Int, Int>>
) {

    private val minWeight = stuffs[0].first
    private val dp: Array<Array<Int>> =
        Array(N + 1) { Array(100_001) { 0 } } // [선택가능한 최대 index][무게]

    init {
        for (i in minWeight..maxWeight) {
            dp[0][i] = stuffs[0].second
        }
    }

    fun run(): Int = pick(1).let { dp[N - 1][maxWeight] }

    private fun pick(index: Int) {
        if (index == N) {
            return
        }
        for (i in minWeight..maxWeight) {
            if (i < stuffs[index].first) {
                dp[index][i] = dp[index - 1][i]
                continue
            }
            dp[index][i] = max(
                dp[index][i - 1],
                max(dp[index - 1][i], dp[index - 1][i - stuffs[index].first] + stuffs[index].second)
            )
        }
        pick(index + 1)
    }
}
