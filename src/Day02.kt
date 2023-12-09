import kotlin.math.max

fun main() {
    class Cube {
        var red: Int = 0
        var green: Int = 0
        var blue: Int = 0

        override fun toString(): String {
            return "r=$red g=$green b=$blue"
        }
    }

    fun countNumAndColor(cube: String): Cube {
        val rgb = cube.split(",")
        val res = Cube()
        for (item in rgb) {
            val isRed = item.contains("red")
            val isGreen = item.contains("green")
            val isBlue = item.contains("blue")
            var t = item
            t = t.replace("red", "")
            t = t.replace("green", "")
            t = t.replace("blue", "")
            val count = t.trim().toInt()
            if (isRed) {
                res.red = count
            } else if (isGreen) {
                res.green = count
            } else if (isBlue) {
                res.blue = count
            }
        }
        return res
    }

    fun isValid(cube: Cube): Boolean {
        return cube.red <= 12 && cube.green <= 13 && cube.blue <= 14
    }

    fun part1(input: List<String>): Int {
        var sum = 0
        for (line in input) {
            val t = line.split(":")
            val game = t[0].substring(4).trim().toInt()
            val cubeList = t[1].split(";")
            var validGame = true
            for (cubeSet in cubeList) {
                val cube = countNumAndColor(cubeSet)
                validGame = validGame && isValid(cube)
            }
            if (validGame) {
                sum += game
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        for (line in input) {
            val t = line.split(":")
            val cubeList = t[1].split(";")
            var maxRed = 0
            var maxGreen = 0
            var maxBlue = 0
            for (cubeSet in cubeList) {
                val cube = countNumAndColor(cubeSet)
                maxRed = max(maxRed, cube.red)
                maxGreen = max(maxGreen, cube.green)
                maxBlue = max(maxBlue, cube.blue)
            }
            sum += maxRed * maxGreen * maxBlue
        }
        //sum.println()
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
