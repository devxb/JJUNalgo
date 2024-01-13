import java.io.BufferedReader
import java.io.InputStreamReader

val tree: MutableMap<String, Pair<String, String>> = mutableMapOf()

fun main() {
    BufferedReader(InputStreamReader(System.`in`))
        .use { reader ->
            val nodeCount = reader.readLine().toInt()
            for (i in 1..nodeCount) {
                val node = reader.readLine().split(" ")
                tree[node[0]] = node[1] to node[2]
            }
        }

    preOrder("A")
    println()
    midOrder("A")
    println()
    postOrder("A")
}

fun preOrder(current: String) {
    if (current == ".") {
        return
    }
    print(current)
    preOrder(tree[current]!!.first)
    preOrder(tree[current]!!.second)
}

fun midOrder(current: String) {
    if (current == ".") {
        return
    }
    midOrder(tree[current]!!.first)
    print(current)
    midOrder(tree[current]!!.second)
}

fun postOrder(current: String) {
    if (current == ".") {
        return
    }
    postOrder(tree[current]!!.first)
    postOrder(tree[current]!!.second)
    print(current)
}
