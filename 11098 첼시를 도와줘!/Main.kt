import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val testCase = reader.readLine().toInt()
    for (i in 1..testCase) {
        println(getExpensivePlayer(reader))
    }
}

fun getExpensivePlayer(reader: BufferedReader): String {
    val playerCount = reader.readLine().toInt()
    val players = mutableListOf<Player>()
    for (i in 1..playerCount) {
        val line = reader.readLine().split(" ")
        players.add(Player(line[0].toInt(), line[1]))
    }
    players.sort()
    return players.last().name
}

internal data class Player(val cost: Int, val name: String) : Comparable<Player> {

    override fun compareTo(other: Player): Int {
        return this.cost.compareTo(other.cost)
    }

}
