fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        for (line in input) {
            val first = line.find { it.isDigit() }?.digitToInt() ?: 0
            val second = line.findLast { it.isDigit() }?.digitToInt() ?: 0
            sum += first * 10 + second
        }
        return sum
    }

    val map = mutableMapOf<String, String>().apply {
        put("one", "1")
        put("two", "2")
        put("three", "3")
        put("four", "4")
        put("five", "5")
        put("six", "6")
        put("seven", "7")
        put("eight", "8")
        put("nine", "9")
    }

    val set = mutableSetOf<String>().apply {
        add("one")
        add("two")
        add("three")
        add("four")
        add("five")
        add("six")
        add("seven")
        add("eight")
        add("nine")
    }

    fun isStringOrDigit(i: Int, str: String): String? {
        if (str[i].isDigit()) return str[i].toString()
        set.forEach {
            if (i + it.length <= str.length) {
                val subStr = str.substring(i, i + it.length)
                if (subStr == it) return map[subStr]
            }
        }
        return null
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        for (line in input) {
            val first = line.indices.firstNotNullOf { isStringOrDigit(it, line) }.toInt()
            val second = line.indices.reversed().firstNotNullOf { isStringOrDigit(it, line) }.toInt()
            sum += first * 10 + second
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part2(testInput) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
