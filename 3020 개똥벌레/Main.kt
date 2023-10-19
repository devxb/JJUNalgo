import java.io.BufferedReader
import java.io.InputStreamReader

private var N = 0
private var height = 0
private val up: MutableList<Int> = mutableListOf()
private val down: MutableList<Int> = mutableListOf()

fun main() {
    input()
    var answer = 987654321 to 0
    for (i in 1..height) {
        val upCollision = getCollisionCount(height - i + 1, up)
        val downCollision = getCollisionCount(i, down)
        if (answer.first > upCollision + downCollision) {
            answer = upCollision + downCollision to 0
        }
        if (answer.first == upCollision + downCollision) {
            answer = answer.first to answer.second + 1
        }
    }
    println("${answer.first} ${answer.second}")
}

fun input() {
    BufferedReader(InputStreamReader(System.`in`))
        .use { reader ->
            val line = reader.readLine().split(" ").map { it.toInt() }
            N = line[0]
            height = line[1]
            for (i in 0 until N) {
                if (i % 2 == 0) {
                    down.add(reader.readLine().toInt())
                    continue
                }
                up.add(reader.readLine().toInt())
            }
        }
    up.sort()
    down.sort()
}

fun getCollisionCount(height: Int, list: List<Int>): Int {
    var left = 0
    var right = list.size - 1
    while (left <= right) {
        val mid = (left + right) / 2
        if (list[mid] >= height) {
            right = mid - 1
            continue
        }
        left = mid + 1
    }
    return list.size - left
}
