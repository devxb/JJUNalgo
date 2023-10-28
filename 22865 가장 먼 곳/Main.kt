import java.util.*
import kotlin.math.min

private const val INF = Int.MAX_VALUE
private var N = 0
private lateinit var friends: List<Int>
private var M = 0
private lateinit var graph: List<MutableList<Pair<Int, Int>>>
private lateinit var check: Array<Array<Int>>

fun main() {
    input()
    friends.withIndex().forEach {
        dijkstra(it.index, it.value)
    }
    var result = -1
    var resultNode = -1
    for (i in 1..N) {
        if (friends[0] == i || friends[1] == i || friends[2] == i) {
            continue
        }
        val distance: Int = min(check[0][i], min(check[1][i], check[2][i]))
        if (result < distance) {
            result = distance
            resultNode = i
        }
    }
    println(resultNode)
}

private fun input() {
    N = readln().toInt()
    check = Array(3) { Array(N + 1) { INF } }
    friends = readln().split(" ")
        .map { it.toInt() }
    M = readln().toInt()
    graph = List(N + 1) { mutableListOf() }
    for (i in 0 until M) {
        val line = readln().split(" ").map { it.toInt() }
        graph[line[0]].add(line[1] to line[2])
        graph[line[1]].add(line[0] to line[2])
    }
}

private fun dijkstra(friend: Int, startNode: Int) {
    val pq = PriorityQueue<Pair<Int, Int>> { o1, o2 -> o1.first.compareTo(o2.first) }
    pq.add(0 to startNode)
    check[friend][startNode] = 0
    while (pq.isNotEmpty()) {
        val current = pq.poll()
        if (check[friend][current.second] < current.first) {
            continue
        }
        for (node in graph[current.second]) {
            val nextWeight = node.second + current.first
            if (check[friend][node.first] <= nextWeight) {
                continue
            }
            check[friend][node.first] = nextWeight
            pq.add(nextWeight to node.first)
        }
    }
}
