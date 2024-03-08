import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

var N = 0
var M = 0
lateinit var ramps: Array<Int>

fun main() {
    read()
    println(binarySearch())
}

private fun read() {
    BufferedReader(InputStreamReader(System.`in`))
        .use { reader ->
            N = reader.readLine().toInt()
            M = reader.readLine().toInt()
            ramps = reader.readLine()
                .split(" ")
                .map { it.toInt() }
                .toTypedArray()
        }
}

private fun binarySearch(): Int {
    var left = ramps[0]
    var right = N
    var ans = N
    while (left <= right) {
        val mid = (left + right) / 2
        if (isLight(mid)) {
            ans = min(ans, mid)
            right = mid - 1
            continue
        }
        left = mid + 1
    }
    return ans
}

private fun isLight(height: Int): Boolean {
    var beforeLight = 0
    for (ramp in ramps) {
        if (beforeLight < ramp - height) {
            return false
        }
        beforeLight = ramp + height
    }
    return beforeLight >= N
}
