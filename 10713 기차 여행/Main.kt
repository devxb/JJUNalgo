import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max
import kotlin.math.min

private var N = 0
private var M = 0
private lateinit var visitOrder: List<Int>
private lateinit var visited: MutableList<Int>
private val subwayCost: MutableMap<Int, Cost> = mutableMapOf()

fun main() {
    input()
    checkMove()
    sweepMove()
    println(calcMinCost())
}

private fun input() {
    BufferedReader(InputStreamReader(System.`in`))
        .use { reader ->
            var line = reader.readToIntList()
            N = line[0]
            M = line[1]
            visited = MutableList(N + 2) { 0 }

            visitOrder = reader.readToIntList()

            for (i in 1 until N) {
                line = reader.readToIntList()
                subwayCost[i] = Cost(line[0].toLong(), line[1].toLong(), line[2].toLong())
            }
        }
}

private class Cost(val ticket: Long, val card: Long, val icCard: Long)

private fun BufferedReader.readToIntList(): List<Int> = this.readLine()
    .split(" ")
    .map { it.toInt() }

private fun checkMove() {
    for (i in 0 until visitOrder.size - 1) {
        val from = min(visitOrder[i], visitOrder[i + 1])
        val to = max(visitOrder[i], visitOrder[i + 1])
        visited[from]++
        visited[to]--
    }
}

private fun sweepMove() {
    for (i in 1 until N) {
        visited[i] = visited[i - 1] + visited[i]
    }
}

private fun calcMinCost(): Long {
    var totalCost = 0L
    for (i in 1 until N) {
        totalCost += min(
            subwayCost[i]!!.ticket * visited[i],
            (subwayCost[i]!!.card * visited[i]) + subwayCost[i]!!.icCard
        )
    }
    return totalCost
}
