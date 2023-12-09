import kotlin.math.max
import kotlin.math.min

fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        for (i in input.indices) {
            val line = input[i]
            var j = 0
            while (j < line.length) {
                if (line[j].isDigit()) {
                    val start = j
                    var k = j + 1
                    while (k < line.length && line[k].isDigit()) k++
                    val end = k - 1
                    val partNumber = line.substring(start, end + 1).toInt()
                    // check is engine part
                    var isPart = false
                    if (i > 0) {
                        for (t in max(0, start - 1)..min(end + 1, line.length - 1)) {
                            if (!input[i - 1][t].isDigit() && input[i - 1][t] != '.') {
                                isPart = true
                                break
                            }
                        }
                    }
                    if (!isPart && i < input.size - 1) {
                        for (t in max(0, start - 1)..min(end + 1, line.length - 1)) {
                            if (!input[i + 1][t].isDigit() && input[i + 1][t] != '.') {
                                isPart = true
                                break
                            }
                        }
                    }
                    if (!isPart && start > 0) {
                        isPart = input[i][start - 1] != '.'
                    }
                    if (!isPart && end < line.length - 1) {
                        isPart = input[i][end + 1] != '.'
                    }
                    if (isPart) {
                        sum += partNumber
                    }

                    j = end + 1
                } else {
                    j++
                }
            }
        }
        return sum
    }

    fun findPartNumber(x: Int, y: Int, input: List<String>, found: Array<Array<Boolean>>): Int? {
        if (x < 0 || x >= input.size || y < 0 || y >= input[0].length || !input[x][y].isDigit() || found[x][y])
            return null
        val line = input[x]
        var j = y
        while (j >= 0 && line[j].isDigit()) j--
        val start = j + 1
        j = y + 1
        while (j < line.length && line[j].isDigit()) j++
        val end = j - 1

        // mark found indices so we dont duplicate
        for (k in start..end) {
            found[x][k] = true
        }

        return line.substring(start, end + 1).toInt()
    }

    fun part2(input: List<String>): Int {
        val dx = listOf(-1, -1, -1, 0, 1, 1, 1, 0)
        val dy = listOf(-1, 0, 1, 1, 1, 0, -1, -1)
        var sum = 0
        val found = Array(input.size) { Array(input[0].length) { false } }
        for (i in input.indices) {
            for (j in input[i].indices) {
                if (input[i][j] == '*') {
                    val partList = mutableListOf<Int>()
                    for (k in dx.indices) {
                        val partNumber = findPartNumber(i + dx[k], j + dy[k], input, found)
                        if (partNumber != null) {
                            partList.add(partNumber)
                        }
                    }
                    var pow = 1
                    if (partList.size == 2) {
                        for (part in partList) {
                            pow *= part
                        }
                        sum += pow
                    }
                }
            }
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 4361)
    check(part2(testInput) == 467835)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
