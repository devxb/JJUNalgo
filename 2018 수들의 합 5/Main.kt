import java.io.BufferedReader
import java.io.InputStreamReader

private var N = 0L

fun main() {
    BufferedReader(InputStreamReader(System.`in`))
        .use { reader ->
            N = reader.readLine().toLong()
        }

    println(combine())
}

private fun combine(): Int {
    var answer = 0
    var left = 1L
    var right = 1L
    while (left <= right && right <= N) {
        val currentNum = sum(left, right)
        if (currentNum == N) {
            answer++
            left++
            continue
        }
        if (currentNum < N) {
            right++
            continue
        }
        left++
    }
    return answer
}

private fun sum(left: Long, right: Long): Long {
    if ((right - left + 1) % 2L == 0L) {
        return (right + left) * ((right - left + 1) / 2L)
    }
    return (right + left) * ((right - left) / 2L) + (right + left) / 2L
}
