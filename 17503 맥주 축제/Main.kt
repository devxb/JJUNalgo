import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

var festivalDay = 0L
var favorite = 0L
var beerCount = 0L
var beers = mutableListOf<Pair<Long, Long>>()

fun main() {
    read()
    println(binarySearch())
}

private fun read() {
    BufferedReader(InputStreamReader(System.`in`))
        .use { reader ->
            var line = reader.readLine().split(" ").map { it.toLong() }
            festivalDay = line[0]
            favorite = line[1]
            beerCount = line[2]

            for (i in 0 until beerCount) {
                line = reader.readLine().split(" ").map { it.toLong() }
                beers.add(line[0] to line[1])
            }
            beers.sortByDescending { (favorite, _) -> favorite }
        }
}

private fun binarySearch(): Long {
    var left: Long = 0L
    var right: Long = Int.MAX_VALUE.toLong()
    var ans = right
    var canDrink = false
    while (left <= right) {
        val mid = (left + right) / 2
        if (isFilledFavorite(mid)) {
            ans = min(ans, mid)
            canDrink = true
            right = mid - 1
            continue
        }
        left = mid + 1
    }
    if (!canDrink) {
        return -1
    }
    return ans
}

private fun isFilledFavorite(level: Long): Boolean {
    var drinkedFavorite = 0L
    var drinkCount = festivalDay
    for (beer in beers) {
        if (drinkCount == 0L) {
            break
        }
        if (beer.second > level) {
            continue
        }
        drinkedFavorite += beer.first
        drinkCount--
    }
    return drinkedFavorite >= favorite && drinkCount == 0L
}
