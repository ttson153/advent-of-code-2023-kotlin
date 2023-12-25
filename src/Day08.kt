fun main() {
    data class Node(val left: String, val right: String)

    fun buildMap(input: List<String>): Map<String, Node> {
        val map = mutableMapOf<String, Node>()
        for (i in 2 until input.size) {
            val line = input[i]
            if (line.isNotBlank()) {
                val label = line.substring(0..2)
                val left = line.substring(7..9)
                val right = line.substring(12..14)
                map[label] = Node(left, right)
            }
        }
        return map
    }

    fun part1(input: List<String>): Int {
        val instruction = input[0]
        val map = buildMap(input)

        var step = 0
        var currentNode = "AAA"
        var i = 0
        while (currentNode != "ZZZ") {
            val inst = instruction[i]
            i = (i + 1).mod(instruction.length)
            currentNode = if (inst == 'L') {
                map[currentNode]?.left ?: currentNode
            } else {
                map[currentNode]?.right ?: currentNode
            }
            step += 1
        }
        return step
    }

    fun gcd(x: Long, y: Long): Long {
        return if (y == 0L) x else gcd(y, x % y)
    }

    fun lcm(x: Long, y: Long): Long {
        return x * y / gcd(x, y)
    }

    fun part2(input: List<String>): Long {
        val instruction = input[0]
        val map = buildMap(input)
        val nodeSet = mutableSetOf<String>()
        for (node in map.keys) {
            if (node.endsWith("A")) {
                nodeSet.add(node)
            }
        }
        val stepToZList = mutableListOf<Int>()
        for (node in nodeSet) {
            var step = 0
            var currentNode = node
            var i = 0
            while (!currentNode.endsWith("Z")) {
                val inst = instruction[i]
                i = (i + 1).mod(instruction.length)
                currentNode = if (inst == 'L') {
                    map[currentNode]?.left ?: currentNode
                } else {
                    map[currentNode]?.right ?: currentNode
                }
                step += 1
            }
            stepToZList.add(step)
        }

        return stepToZList.map { x -> x.toLong() }.reduce { x, y -> lcm(x, y) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
//    check(part1(testInput) == 6)
    check(part2(testInput) == 6L)

    val input = readInput("Day08")
    part1(input).println()
    part2(input).println()
}
