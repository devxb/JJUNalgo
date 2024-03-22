import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

private var n = 0
private lateinit var ports: List<Int>

fun main() {
    read()
    println(lis())
}

private fun read() {
    BufferedReader(InputStreamReader(System.`in`)).use { reader ->
        n = reader.readLine().toInt()
        ports = reader.readLine().split(" ").map { it.toInt() }
    }
}

private fun lis(): Int {
    val list = mutableListOf<Int>()
    for (port in ports) {
        val idx = list.binarySearch(port)
        if (idx == list.size) {
            list.add(port)
            continue
        }
        list[idx] = port
    }
    return list.size
}

private fun List<Int>.binarySearch(key: Int): Int {
    var left = 0
    var right = this.size - 1
    while (left <= right) {
        val mid = (left + right) / 2
        if (this[mid] > key) {
            right = mid - 1
            continue
        }
        left = mid + 1
    }
    return left
}
