import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

val picked = listOf(1, 3)
val dp = mutableListOf<MutableList<Int>>()

fun main() {
    init()
    val line = BufferedReader(InputStreamReader(System.`in`)).readLine()
    println(
        when (play(0, line.toInt())) {
            0 -> "SK"
            else -> "CY"
        }
    )
}

private fun init() {
    for (i in 0..1000) {
        dp.add(mutableListOf(-1, -1))
    }
}

private fun play(turn: Int, leftStones: Int): Int {
    if (dp[leftStones][turn] > -1) {
        return dp[leftStones][turn]
    }
    if (leftStones == 0) {
        return when (turn) {
            1 -> 0
            else -> 1
        }
    }
    var canWin = false
    for (pick in picked) {
        if (leftStones - pick < 0) {
            continue
        }
        dp[leftStones - pick][turn.nextTurn()] = play(turn.nextTurn(), leftStones - pick)
        canWin = when (dp[leftStones - pick][turn.nextTurn()]) {
            turn -> true
            else -> false
        }
    }
    return when (canWin) {
        true -> turn
        else -> turn.nextTurn()
    }
}

private fun Int.nextTurn(): Int = abs(this - 1)
