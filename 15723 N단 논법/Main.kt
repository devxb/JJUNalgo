import java.io.BufferedReader
import java.io.InputStreamReader

private var N: Int = 0
private var M: Int = 0
private val graph: MutableMap<String, String> = mutableMapOf()

fun main() {
    read()
}

private fun read() {
    BufferedReader(InputStreamReader(System.`in`))
        .use {
            N = it.readLine().toInt()
            for (i in 1..N) {
                val line = it.readLine().split(" ")
                graph[line[0]] = line[2]
            }
            M = it.readLine().toInt()
            for (i in 1..M) {
                val line = it.readLine().split(" ")
                println(dfs(line[0], line[2], mutableSetOf()))
            }
        }
}

private fun dfs(current: String, to: String, visited: MutableSet<String>): String {
    if (current == to) {
        return "T"
    }
    visited.add(current)
    val next = graph[current]
    if (next == null || visited.contains(next)) {
        return "F"
    }
    return dfs(next, to, visited)
}
