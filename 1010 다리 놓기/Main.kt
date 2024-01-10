import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max
import kotlin.math.min

var result = 0
val dp: Array<Array<Int>> = Array(31) { Array(31) { 0 } }

fun main() {
    fill()
    BufferedReader(InputStreamReader(System.`in`))
        .use { reader ->
            val case = reader.readLine().toInt()
            for (i in 1..case) {
                result = 0
                val line = reader.readLine().split(" ")
                    .map { it.toInt() }
                val left = min(line[0], line[1])
                val right = max(line[0], line[1])
                println(dp[right][left])
            }
        }
}

private fun fill() {
    for (i in 0..30) {
        dp[i][i] = 1
        dp[i][0] = 1
    }
    for (n in 1..30) {
        for (r in 1 until n) {
            dp[n][r] = dp[n - 1][r - 1] + dp[n - 1][r]
        }
    }
}
