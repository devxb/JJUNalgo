import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

var vertex = 0
var edge = 0
var graph = mutableListOf<MutableList<Pair>>()
val queue: PriorityQueue<Pair> = PriorityQueue()
var ans = 0

fun main() {
    read()
    println(spanningTree())
}

private fun read() {
    BufferedReader(InputStreamReader(System.`in`))
        .use { reader ->
            var line = reader.readLine().split(" ")
            vertex = line[0].toInt()
            edge = line[1].toInt()

            graph.add(mutableListOf())
            for (i in 0..vertex) {
                graph.add(mutableListOf())
            }

            for (i in 1..edge) {
                line = reader.readLine().split(" ")
                val from = line[0].toInt()
                val to = line[1].toInt()
                val weight = line[2].toInt()

                graph[from].add(Pair(to, weight))
                graph[to].add(Pair(from, weight))
            }
        }
}

private fun spanningTree(): Int {
    queue.add(Pair(0, 1))
    val check = Array(vertex + 2) { Int.MAX_VALUE }
    var ans = 0
    while (queue.isNotEmpty()) {
        val current = queue.poll()
        if (check[current.second] != Int.MAX_VALUE) {
            continue;
        }
        check[current.second] = current.first
        ans += current.first
        for (next in graph[current.second]) {
            if (check[next.first] != Int.MAX_VALUE) {
                continue
            }
            queue.add(Pair(next.second, next.first))
        }
    }
    return ans
}

class Pair(
    val first: Int,
    val second: Int
) : Comparable<Pair> {
    override fun compareTo(other: Pair): Int {

        if (first < other.first) {
            return -1
        }
        if (first > other.first) {
            return 1
        }
        return 0
    }
}
