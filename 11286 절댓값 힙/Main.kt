import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.abs

val pq = PriorityQueue(
    compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second }
)

fun main() {
    BufferedReader(InputStreamReader(System.`in`))
        .use { reader ->
            val N = reader.readLine().toInt()
            for (i in 1..N) {
                val num = reader.readLine().toInt()
                if (num == 0) {
                    if (pq.isEmpty()) {
                        println(0)
                        continue
                    }
                    println(pq.poll().second)
                    continue
                }
                pq.add(abs(num) to num)
            }
        }
}
