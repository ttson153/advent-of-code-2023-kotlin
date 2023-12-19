//
// Created by tts on 19/12/2023.
//
fun main() {
    fun countWin(t: Long, d: Long): Long {
        var win = 0L
        for (j in 1..t / 2) {
            if (j * (t - j) > d) {
                win += 1
            }
        }
        if (t.mod(2) == 0 && (t / 2) * (t / 2) > d) {
            win = win * 2 - 1
        } else {
            win *= 2
        }

        return win
    }

    fun part1(input: List<String>): Long {
        val times = input[0].replace("Time:", "").split(" ").filter { it.isNotBlank() }.map { it.trim().toLong() }
        val distances = input[1].replace("Distance:", "").split(" ").filter { it.isNotBlank() }.map { it.trim().toLong() }

        var res = 1L
        var hasWin = false
        for (i in times.indices) {
            val t = times[i]
            val d = distances[i]

            val win = countWin(t, d)
            if (win > 0) {
                if (!hasWin) {
                    hasWin = true
                }
                res *= win
            }
        }
        return if (hasWin) res else 0
    }

    fun part2(input: List<String>): Long {
        val times = input[0].replace("Time:", "").replace(" ", "").trim().toLong()
        val distances = input[1].replace("Distance:", "").replace(" ", "").trim().toLong()
        return countWin(times, distances)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 288L)
    check(part2(testInput) == 71503L)

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}
