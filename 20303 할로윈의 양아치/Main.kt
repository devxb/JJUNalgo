import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max
import kotlin.math.min

private var children = 0
private var friendCount = 0
private var cryCount = 0
private var friendsCandy = Array(30_001) { 0 }
private val friends = mutableListOf<MutableList<Int>>()
private val friendGroup = Array(30_001) { it }
private val candyOfGroup = Array(30_001) { 0L to 0 }
private val zippedCandyOfGroup = mutableListOf<Pair<Long, Int>>()

fun main() {
    read()
    repeat(children) { groupFriends(it + 1) }
    initCandyOfGroup()
    candyOfGroup.filter { it.first > 0 }
        .forEach { zippedCandyOfGroup.add(it) }

    println(stealCandy())
}

private fun read() {
    BufferedReader(InputStreamReader(System.`in`)).use { reader ->
        var line = reader.readLine().split(" ").map { it.toInt() }
        children = line[0]
        friendCount = line[1]
        cryCount = line[2]
        for (i in 0..children) {
            friends.add(mutableListOf())
        }

        line = reader.readLine().split(" ").map { it.toInt() }
        repeat(children) { friendsCandy[it + 1] = line[it] }

        repeat(friendCount) {
            line = reader.readLine().split(" ").map { it.toInt() }
            val left = line[0]
            val right = line[1]
            friends[left].add(right)
            friends[right].add(left)
        }
    }
}

private fun groupFriends(friendIdx: Int) {
    val friends = friends[friendIdx]
    for (friend in friends) {
        var minFriend = min(friendIdx, friend)
        var maxFriend = max(friendIdx, friend)
        minFriend = disjointSet(minFriend)
        maxFriend = disjointSet(maxFriend)
        friendGroup[maxFriend] = minFriend
    }
}

private fun initCandyOfGroup() {
    repeat(children) {
        val friend = it + 1
        val candy = friendsCandy[friend]
        val group = disjointSet(friendGroup[friend])
        candyOfGroup[group] = candyOfGroup[group].first + candy to candyOfGroup[group].second + 1
    }
}

private fun disjointSet(idx: Int): Int {
    if (friendGroup[idx] == idx) {
        return idx
    }
    friendGroup[idx] = disjointSet(friendGroup[idx])
    return friendGroup[idx]
}

fun stealCandy(): Long {
    val dp = Array(2) {
        Array(cryCount + 1) { 0L }
    }
    for (cryIdx in 1 until cryCount) {
        val needCryIdx = zippedCandyOfGroup[0].second
        if (cryIdx >= needCryIdx) {
            dp[0][cryIdx] = zippedCandyOfGroup[0].first
        }
    }

    for (groupIdx in 1 until zippedCandyOfGroup.size) {
        for (cryIdx in 1 until cryCount) {
            val needCryIdx = zippedCandyOfGroup[groupIdx].second
            if (cryIdx >= needCryIdx) {
                dp[groupIdx % 2][cryIdx] = zippedCandyOfGroup[groupIdx].first
                dp[groupIdx % 2][cryIdx] = max(
                    dp[(groupIdx + 1) % 2][cryIdx],
                    max(
                        dp[groupIdx % 2][cryIdx - 1],
                        dp[(groupIdx + 1) % 2][cryIdx - needCryIdx] + dp[groupIdx % 2][cryIdx]
                    )
                )
                continue
            }
            dp[groupIdx % 2][cryIdx] = max(
                dp[(groupIdx + 1) % 2][cryIdx],
                dp[groupIdx % 2][cryIdx - 1]
            )
        }
    }
    return max(dp[0][cryCount - 1], dp[1][cryCount - 1])
}
