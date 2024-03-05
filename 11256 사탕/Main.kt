import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    BufferedReader(InputStreamReader(System.`in`))
        .use { reader ->
            val testCaseCount = reader.readLine().toInt()
            for (testCase in 1..testCaseCount) {
                var line = reader.readLine().split(" ")
                var candyCount = line[0].toInt()
                val boxCount = line[1].toInt()
                val boxes: MutableList<Int> = mutableListOf()
                for (i in 0 until boxCount) {
                    line = reader.readLine().split(" ")
                    boxes.add(line[0].toInt() * line[1].toInt())
                }
                boxes.sortDescending()
                for (i in 0 until boxes.size) {
                    candyCount -= boxes[i]
                    if (candyCount <= 0) {
                        println(i + 1)
                        break;
                    }
                }
            }
        }
}
