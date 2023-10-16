import kotlin.math.max

class Solution {
    private val check: Array<IntArray> = Array(17) { IntArray(1 shl 22) { 0 } }
    private val edgeWithRev: MutableList<MutableList<Int>> =
        MutableList(17) { mutableListOf() }
    private lateinit var info: IntArray
    private var answer = 0

    fun solution(info: IntArray, edges: Array<IntArray>): Int {
        this.info = info;
        edges.forEach { idx ->
            edgeWithRev[idx[0]].add(idx[1])
            edgeWithRev[idx[1]].add(idx[0])
        }
        val alreadyGet = (1 shl 18) or (1 shl 0)
        searchSheep(0, 1, 0, alreadyGet)
        return answer
    }

    private fun searchSheep(idx: Int, sheep: Int, wolf: Int, alreadyGet: Int) {
        if (check[idx][alreadyGet] >= sheep) {
            return
        }
        check[idx][alreadyGet] = sheep
        answer = max(answer, sheep)
        for (nextEdge in edgeWithRev[idx]) {
            var nextWolfCount = wolf
            var nextSheepCount = sheep
            if ((alreadyGet and (1 shl nextEdge)) == 0) {
                when (info[nextEdge]) {
                    0 -> nextSheepCount++
                    1 -> nextWolfCount++
                }
            }
            if (nextSheepCount <= nextWolfCount) {
                continue
            }
            searchSheep(nextEdge, nextSheepCount, nextWolfCount, alreadyGet or (1 shl nextEdge))
        }
    }
}
