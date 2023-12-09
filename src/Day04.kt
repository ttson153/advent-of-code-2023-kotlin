fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        for (line in input) {
            val s1 = line.split(":")[1]
            val s2 = s1.split("|")
            val winningSet = s2[0].split(" ").filter { it.isNotBlank() }.map { it.toInt() }.toSet()
            val havingSet = s2[1].split(" ").filter { it.isNotBlank() }.map { it.toInt() }.toSet()
            var point = 0
            for (num in havingSet) {
                if (num in winningSet) {
                    if (point == 0) point = 1 else point *= 2
                }
            }
            sum += point
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        val cardCount = mutableMapOf<Int, Int>()
        for (i in input.indices) {
            cardCount[i + 1] = 1
        }
        for (line in input) {
            val s = line.split(":")
            val cardNumber = s[0].replace("Card", "").trim().toInt()
            val s2 = s[1].split("|")
            val winningSet = s2[0].split(" ").filter { it.isNotBlank() }.map { it.toInt() }.toSet()
            val havingSet = s2[1].split(" ").filter { it.isNotBlank() }.map { it.toInt() }.toSet()
            var match = 0
            for (num in havingSet) {
                if (num in winningSet) {
                    match += 1
                }
            }
            val originalCards = cardCount[cardNumber] ?: 0
            for (i in cardNumber + 1..cardNumber + match) {
                val cards = cardCount[i] ?: -1
                if (cards != -1) {
                    cardCount[i] = cards + originalCards
                }
            }
        }
        for (num in cardCount.values) {
            sum += num
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 30)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
