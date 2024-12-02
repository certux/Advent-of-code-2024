import kotlin.math.abs

fun main() {


    fun part1(input: List<String>): Int {

    }

    fun part2(input: List<String>): Int {

    }

    // Test if implementation meets criteria from the description, like:
    //check(part1(listOf("1  2", "3 3")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    //val testInput = readInput("Day01_test")
    //check(part1(testInput) == 1)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

fun inputToIntLists(input:List<String>) =
    input.map { inputList ->
        inputList.split("""\s+""".toRegex()).let { Pair(it[0].toInt(), it[1].toInt())}
    }.unzip()
