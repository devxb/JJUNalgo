import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.max

var sequenceLength = 0
lateinit var sequence: List<String>
var ans = -1

fun main() {
    read()
    makeEvenPalindrome()
    println(ans)
}

private fun read() {
    BufferedReader(InputStreamReader(System.`in`))
        .use { reader ->
            sequenceLength = reader.readLine().toInt()
            sequence = reader.readLine().split(" ")
        }
}

fun makeEvenPalindrome() {
    var count = 0
    val list: LinkedList<String> = LinkedList()

    var fixedIter = 0
    for (i in sequence.indices) {
        if (i < fixedIter) {
            continue
        }
        var number = sequence[i]
        if (list.isEmpty()) {
            list.add(number)
            continue
        }
        var beforeNumber = list.last
        var hitCount = 0
        var copiedNumber = number
        while (isEvenPalindrome(beforeNumber, copiedNumber)) {
            hitCount++
            fixedIter = max(i + 1, fixedIter + 1)
            if (hitCount >= list.size || fixedIter >= sequence.size) {
                break
            }
            beforeNumber = list[list.size - hitCount - 1]
            copiedNumber = sequence[fixedIter]
        }
        if (hitCount == list.size) {
            list.clear()
            count++
            continue
        }
        fixedIter = 0
        list.add(number)
    }
    if (list.isEmpty()) {
        ans = count
    }
}

fun isEvenPalindrome(leftNumber: String, number: String): Boolean {
    val originalSequence = leftNumber + number
    val reversedSequence = leftNumber.reversed() + number.reversed()
    return originalSequence == reversedSequence.reversed()
}
