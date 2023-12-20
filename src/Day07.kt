fun main() {
    data class CardHand(
        val cards: String,
        val bid: Int
    )

    /**
     * 1 -> High card
     * 2 -> One pair
     * 3 -> Two pair
     * 4 -> Three of a kind
     * 5 -> Full house
     * 6 -> Four of a kind
     * 7 -> Five of a kind
     */
    fun checkHand(hand: String): Int {
        val map = mutableMapOf<Char, Int>()
        for (card in hand) {
            map[card] = (map[card] ?: 0) + 1
        }
        if (map.size == 1) {
            return 7
        } else if (map.size == 2) {
            for (entry in map) {
                if (entry.value >= 4) {
                    return 6
                }
            }
            return 5
        } else {
            // map size = 3, 4, 5
            var pairCount = 0
            for (entry in map) {
                if (entry.value >= 3) {
                    return 4
                } else if (entry.value == 2) {
                    pairCount += 1
                }
            }
            return when (pairCount) {
                2 -> 3
                1 -> 2
                else -> 1
            }
        }
    }

    fun checkHand2(hand: String): Int {
        val filtered = hand.filter { it != 'J' }
        if (filtered.isEmpty()) return 7
        val mostChar = filtered.groupingBy { it }.eachCount().toList().maxBy { (_, count) -> count }.first
        val replaced = hand.replace('J', mostChar)
        return checkHand(replaced)
    }

    fun compareCard(c1: String, c2: String, mapCardValue: Map<Char, Int>): Int {
        for (i in c1.indices) {
            if (c1[i] != c2[i]) {
                return mapCardValue[c1[i]]?.compareTo(mapCardValue[c2[i]] ?: 0) ?: 0
            }
        }
        return 0
    }

    val mapCardValue1 = mapOf(
        '2' to 2,
        '3' to 3,
        '4' to 4,
        '5' to 5,
        '6' to 6,
        '7' to 7,
        '8' to 8,
        '9' to 9,
        'T' to 10,
        'J' to 11,
        'Q' to 12,
        'K' to 13,
        'A' to 14,
    )
    fun part1(input: List<String>): Int {
        val deck = mutableListOf<CardHand>()
        for (line in input) {
            val s = line.split(" ")
            deck.add(CardHand(s[0], s[1].toInt()))
        }
        val sorted = deck.sortedWith(compareBy<CardHand> { checkHand(it.cards) }.thenComparator { a, b -> compareCard(a.cards, b.cards, mapCardValue1) })
        var sum = 0
        for (i in sorted.indices) {
            sum += sorted[i].bid * (i + 1)
        }
        return sum
    }

    val mapCardValue2 = mapOf(
        'J' to 1,
        '2' to 2,
        '3' to 3,
        '4' to 4,
        '5' to 5,
        '6' to 6,
        '7' to 7,
        '8' to 8,
        '9' to 9,
        'T' to 10,
        'Q' to 11,
        'K' to 12,
        'A' to 13,
    )
    fun part2(input: List<String>): Int {
        val deck = mutableListOf<CardHand>()
        for (line in input) {
            val s = line.split(" ")
            deck.add(CardHand(s[0], s[1].toInt()))
        }
        val sorted = deck.sortedWith(compareBy<CardHand> { checkHand2(it.cards) }.thenComparator { a, b -> compareCard(a.cards, b.cards, mapCardValue2) })
        var sum = 0
        for (i in sorted.indices) {
            sum += sorted[i].bid * (i + 1)
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 6440)
    check(part2(testInput) == 5905)

    val input = readInput("Day07")
    part1(input).println()
    part2(input).println()
}
