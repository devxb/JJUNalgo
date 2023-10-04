import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val line = reader.readLine().split(" ")

    val n = line[0].toInt()
    val k = line[1].toInt()

    val heights = reader.readLine().split(" ")
        .asSequence()
        .map { it.toInt() }
        .toList()

    println(Algorithm(n, k, heights).getMinimumCost())
}

class Algorithm(private val n: Int, private val k: Int, private val heights: List<Int>) {

    val maxWeights: PriorityQueue<Pair<Int, Int>>
    val cutted: Array<Boolean>

    init {
        maxWeights = PriorityQueue<Pair<Int, Int>> { o1, o2 -> o2.first.compareTo(o1.first) }
            .also {
                for (i in 0..heights.size - 2) {
                    val element = heights[i + 1] - heights[i] to i
                    it.add(element)
                }
            }

        cutted = Array(300000) { false }
            .also {
                for (i in 1..k - 1) {
                    val cuttingPoint = maxWeights.poll()
                    it[cuttingPoint.second] = true
                }
            }
    }

    internal fun getMinimumCost(): Int {
        var leftIdx = 0
        var ans = 0
        for (i in 0..n - 1) {
            if (cutted[i]) {
                ans += heights[i] - heights[leftIdx]
                leftIdx = i + 1
            }
        }
        return ans + heights[n - 1] - heights[leftIdx]
    }

}
