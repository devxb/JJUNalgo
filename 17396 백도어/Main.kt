import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.min

private const val INF = Long.MAX_VALUE;
private var N = 0
private var M = 0
private lateinit var watchedList: List<Int>
private lateinit var graph: MutableList<MutableList<Pair<Int, Long>>>

fun main() {
    input()
    println(dijkstra())
}

private fun input() {
    BufferedReader(InputStreamReader(System.`in`))
        .use { reader ->
            var line = reader.readLine().split(" ")
                .map { it.toInt() }
            N = line[0]
            M = line[1]
            watchedList = reader.readLine().split(" ")
                .map { it.toInt() }
            graph = mutableListOf<MutableList<Pair<Int, Long>>>()
                .apply {
                    for (i in 0 until N) {
                        this.add(mutableListOf())
                    }
                }
            for (i in 1..M) {
                line = reader.readLine().split(" ")
                    .map { it.toInt() }
                graph[line[0]].add(line[1] to line[2].toLong())
                graph[line[1]].add(line[0] to line[2].toLong())
            }
        }
}

private fun dijkstra(): Long {
    val check: Array<Long> = Array(N) { INF }
    val queue: PriorityQueue<Pair<Long, Int>> =
        PriorityQueue { o1, o2 -> o1.cost().compareTo(o2.cost()) }

    var ans = INF;
    queue.add(0L to 0)
    while (queue.isNotEmpty()) {
        val currentPosition = queue.poll()
        if (check[currentPosition.idx()] <= currentPosition.cost() || currentPosition.cost() >= ans) {
            continue
        }
        check[currentPosition.idx()] = currentPosition.cost()
        if (currentPosition.idx() == N - 1) {
            ans = min(ans, currentPosition.cost())
            continue
        }
        graph[currentPosition.idx()].filter { (nextIdx, cost) ->
            currentPosition.cost() + cost < check[nextIdx] && (watchedList[nextIdx] == 0 || nextIdx == N - 1)
        }.forEach { (nextIdx, cost) ->
            queue.add(currentPosition.cost() + cost to nextIdx)
        }
    }
    return when (ans) {
        INF -> -1
        else -> ans
    }
}

private fun <A, B> Pair<A, B>.cost(): A = this.first

private fun <A, B> Pair<A, B>.idx(): B = this.second
