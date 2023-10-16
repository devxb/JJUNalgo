import kotlin.math.abs

class Solution {
    private val inf = -987654321
    private lateinit var lionScores: IntArray
    private var resultScore = inf
    private var result: IntArray = IntArray(11) { 0 }

    fun solution(n: Int, info: IntArray): IntArray {
        lionScores = info
        solve(0, n, 0, mutableListOf())
        if (resultScore <= 0) {
            return intArrayOf(-1)
        }
        return result
    }

    private fun solve(idx: Int, remainArrow: Int, score: Int, picked: MutableList<Int>) {
        if (idx == 11) {
            if (resultScore == score) {
                var trigger = false
                for (i in picked.size - 1 downTo 0) {
                    if (picked[i] < result[i]) {
                        break
                    }
                    if (picked[i] > result[i]) {
                        trigger = true
                    }
                    if (trigger) {
                        break
                    }
                }
                if (trigger) {
                    resultScore = score
                    result = picked.toIntArray()
                }
            }
            if (resultScore < score) {
                resultScore = score
                result = picked.toIntArray()
            }
            return
        }
        for (i in 0..remainArrow) {
            var nextScore = score
            if (lionScores[idx] > 0 && lionScores[idx] >= i) {
                nextScore -= idx.score()
            } else if (lionScores[idx] < i) {
                nextScore += idx.score()
            }
            picked.add(i)
            solve(idx + 1, remainArrow - i, nextScore, picked)
            picked.removeLast()
        }
    }

    private fun Int.score() = abs(this - 10)
}
