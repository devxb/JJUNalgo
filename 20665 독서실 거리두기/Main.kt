import java.io.BufferedReader
import java.io.InputStreamReader

var seatCount: Int = 0
var bookedCount: Int = 0
val bookInfos: MutableList<BookInfo> = mutableListOf()
val seats: MutableList<Pair<Int, Int>> = mutableListOf()
val bookedFavorites: MutableList<BookInfo> = mutableListOf()
var favoriteSeat: Int = 0
var answer: Int = 21 * 60 - 9 * 60


fun main() {
    read()
    bookInfos.sort()
    bookInfos.forEach {
        val seatIdx = findSeatableIdx(it)
        if (seatIdx == favoriteSeat) {
            bookedFavorites.add(it)
        }
        seats[seatIdx] = it.startTime to it.endTime
    }
    bookedFavorites.forEach {
        answer -= (it.endTime - it.startTime)
    }
    println(answer)
}

private fun findSeatableIdx(bookInfo: BookInfo): Int {
    if (isAllEmpty(bookInfo)) {
        return 1
    }
    var disToIdx = 0 to 0
    for (i in 1..seatCount) {
        if (!isSeatable(i, bookInfo)) {
            continue
        }
        var tempLeft = 987654321 to 987654321
        for (j in i + 1..seatCount) {
            if (!isSeatable(j, bookInfo)) {
                tempLeft = j - i to i
                break
            }
        }
        var tempRight = 987654321 to 987654321
        for (j in i - 1 downTo 1) {
            if (!isSeatable(j, bookInfo)) {
                tempRight = i - j to i
                break
            }
        }
        val temp = when {
            tempLeft.first <= tempRight.first -> tempLeft
            else -> tempRight
        }
        if (temp.first != 987654321 && disToIdx.first < temp.first) {
            disToIdx = temp
        }
    }
    return disToIdx.second
}

fun isAllEmpty(bookInfo: BookInfo): Boolean {
    for (i in 1..seatCount) {
        if (!isSeatable(i, bookInfo)) {
            return false
        }
    }
    return true
}

private fun isSeatable(seatIdx: Int, bookInfo: BookInfo): Boolean {
    val seat = seats[seatIdx]
    return seat.second <= bookInfo.startTime
}

private fun read() {
    BufferedReader(InputStreamReader(System.`in`))
        .use { reader ->
            val line = reader.readLine().split(" ").map { it.toInt() }
            seatCount = line[0]
            for (i in 0..seatCount) {
                seats.add(9 * 60 to 9 * 60)
            }
            bookedCount = line[1]
            favoriteSeat = line[2]
            for (i in 1..bookedCount) {
                val times = reader.readLine().split(" ")
                bookInfos.add(BookInfo.from(times[0], times[1]))
            }
        }
}

class BookInfo(
    val startTime: Int,
    val endTime: Int
) : Comparable<BookInfo> {

    companion object {
        fun from(startTime: String, endTime: String): BookInfo {
            return BookInfo(startTime.toIntTime(), endTime.toIntTime())
        }

        private fun String.toIntTime(): Int {
            val hour = this.substring(0, 2)
            val minutes = this.substring(2, 4)
            return hour.toInt() * 60 + minutes.toInt()
        }
    }

    override fun compareTo(other: BookInfo): Int {
        if (startTime == other.startTime) {
            if (endTime <= other.endTime) {
                return -1
            }
            return 1
        }
        if (startTime < other.startTime) {
            return -1
        }
        return 1
    }
}
