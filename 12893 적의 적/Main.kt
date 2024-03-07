import java.io.BufferedReader
import java.io.InputStreamReader

var N = 0
var M = 0
var graph = mutableListOf<MutableList<Int>>()
lateinit var check: Array<Int>
var result = true

fun main() {
    read()
    for (i in 1..N) {
        if (check[i] != -1) {
            continue
        }
        check[i] = 1
        dfs(i)
        if (!result) {
            break
        }
    }
    println(
        when (result) {
            true -> 1
            false -> 0
        }
    )
}

private fun read() {
    BufferedReader(InputStreamReader(System.`in`))
        .use { reader ->
            var line = reader.readLine().split(" ").map { it.toInt() }
            N = line[0]
            M = line[1]

            check = Array(N + 1) { -1 }
            for (i in 0..N) {
                graph.add(mutableListOf())
            }

            for (i in 0 until M) {
                line = reader.readLine().split(" ").map { it.toInt() }
                graph[line[0]].add(line[1])
                graph[line[1]].add(line[0])
            }
        }
}

private fun dfs(idx: Int) {
    for (nextIdx in graph[idx]) {
        if (check[nextIdx] == -1) {
            if (check[idx] == 1) {
                check[nextIdx] = 0
            } else {
                check[nextIdx] = 1
            }
            dfs(nextIdx)
        } else if (check[idx] == check[nextIdx]) {
            result = false
        }
    }
}
