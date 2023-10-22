import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs
import kotlin.math.min

private val paperField: MutableList<MutableList<Int>> = mutableListOf()
private val remainPaper: IntArray = IntArray(6) { 5 }
private var answer = 987654321

fun main() {
    input()
    putPapers(0, 0)
    when {
        (answer == 987654321) -> println(-1)
        else -> println(answer)
    }
}

private fun input() {
    BufferedReader(InputStreamReader(System.`in`))
        .use { reader ->
            for (i in 0..9) {
                val line = reader.readLine()
                    .split(" ")
                    .map { it.toInt() }
                    .toMutableList()
                paperField.add(line)
            }
        }
}

private fun putPapers(y: Int, x: Int) {
    if (isAllFilled()) {
        answer = min(answer, countUsedPaper())
        return
    }
    if (y == 10 || x == 10) {
        return
    }
    if (paperField[y][x] == 0) {
        val nextPosition = nextPosition(y, x)
        putPapers(nextPosition.first, nextPosition.second)
        return
    }
    val putablePaperSizes = getPutablePaperSizes(y, x)
    for (paperSize in putablePaperSizes) {
        flipPaper(y, x, paperSize)
        remainPaper[paperSize]--
        val nextPosition = nextPosition(y, x)
        putPapers(nextPosition.first, nextPosition.second)
        remainPaper[paperSize]++
        flipPaper(y, x, paperSize)
    }
}

private fun isAllFilled(): Boolean {
    for (y in 0..9) {
        for (x in 0..9) {
            if (paperField[y][x] == 1) {
                return false
            }
        }
    }
    return true
}

private fun countUsedPaper(): Int {
    var usedPaperCount = 0
    remainPaper.forEach {
        usedPaperCount += (5 - it)
    }
    return usedPaperCount
}

private fun nextPosition(y: Int, x: Int): Pair<Int, Int> {
    if (x == 9) {
        return y + 1 to 0
    }
    return y to x + 1
}

private fun getPutablePaperSizes(y: Int, x: Int): List<Int> {
    val putablePaperSizes = mutableListOf<Int>()
    for (paperSize in 0..4) {
        if (y + paperSize > 9 || x + paperSize > 9) {
            continue
        }
        var isPutable = true
        for (i in y..y + paperSize) {
            if (!isPutable) {
                break
            }
            for (j in x..x + paperSize) {
                if (paperField[i][j] == 0) {
                    isPutable = false
                    break
                }
            }
        }
        if (isPutable && remainPaper[paperSize + 1] > 0) {
            putablePaperSizes.add(paperSize + 1)
        }
    }
    return putablePaperSizes
}

private fun flipPaper(y: Int, x: Int, paperSize: Int) {
    for (i in y until y + paperSize) {
        for (j in x until x + paperSize) {
            paperField[i][j] = abs(paperField[i][j] - 1)
        }
    }
}
