import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

var vacationDay = 0
var workDay = 0
lateinit var workDays: Array<Boolean>
lateinit var dp: Array<Array<Long>>
val prices = arrayOf(10_000L, 25_000L, 37_000L)
val days = arrayOf(1, 3, 5)
val coupons = arrayOf(0, 1, 2)

fun main() {
    read()
    dp[0][0] = 0L
    book(0, 0)
    var ans = Long.MAX_VALUE
    for (result in dp[vacationDay]) {
        ans = min(ans, result)
    }
    println(ans)
}

private fun read() {
    BufferedReader(InputStreamReader(System.`in`))
        .use { reader ->
            val line = reader.readLineToSplitedInt()
            vacationDay = line[0]
            workDay = line[1]
            workDays = Array(vacationDay + 1) { false }
            if (workDay > 0) {
                reader.readLineToSplitedInt()
                    .forEach { workDays[it] = true }
            }
            dp = Array(vacationDay + 1) {
                Array(vacationDay + 1) {
                    Long.MAX_VALUE
                }
            }
        }
}

private fun BufferedReader.readLineToSplitedInt(): List<Int> {
    return this.readLine().split(" ").map { it.toInt() }
}

private fun book(day: Int, coupon: Int) {
    if (day >= vacationDay) {
        return
    }
    if(coupon >= 3 && dp[day + 1][coupon - 3] > dp[day][coupon]) {
        dp[day + 1][coupon - 3] = dp[day][coupon]
        book(day + 1, coupon - 3)
    }
    for (i in 0..2) {
        val nextDay = day + days[i]
        if (nextDay > vacationDay) {
            continue
        }
        val nextCoupon = coupon + coupons[i]
        val nextPrice = dp[day][coupon] + prices[i]
        if (workDays[nextDay] && i == 0) {
            if (dp[nextDay][nextCoupon] <= dp[day][coupon]) {
                continue
            }
            dp[nextDay][nextCoupon] = dp[day][coupon]
            book(nextDay, nextCoupon)
            continue
        }
        if (dp[nextDay][nextCoupon] <= nextPrice) {
            continue
        }
        dp[nextDay][nextCoupon] = nextPrice
        book(nextDay, nextCoupon)
    }
}
