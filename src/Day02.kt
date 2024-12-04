import kotlin.math.abs

fun main() {


    fun part1(input: List<String>): Int {

        return input.map {
            val reports = parseList(it)
            if (reports.isValid()) 1 else 0
        }.sum()

    }

    fun part2(input: List<String>): Int {
        return input.map {
            val reports = parseList(it)
            when {
                reports.isValid() -> 1
                reports.isValidWithoutElement(0) -> 1
                else -> 0
            }
        }.sum()
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

fun parseList(input: String) =
    input.split("""\s+""".toRegex()).map { it.toLong() }

fun List<Long>.isValid(): Boolean {
    if (this.size <= 1) return true
    val differences = this.drop(1).zip(this.dropLast(1)) { v1: Long, v2: Long -> v2-v1 }

    // ensure that all values are between 1 and 3
    if (!differences.all { abs(it) in 1..3 })
        return false

    // ensure that all have the same sign
    if (differences.map { it/abs(it) }.toSet().size != 1)
        return false
    return true
}

fun List<Long>.isValidWithoutElement(i: Int): Boolean {
    if (i == this.size) return false
    if ((this.subList(0, i) + this.subList(i+1, this.size)).isValid()) return true
    return this.isValidWithoutElement(i+1)

}

fun inputToIntLists(input:List<String>) =
    input.map { inputList ->
        inputList.split("""\s+""".toRegex()).let { Pair(it[0].toInt(), it[1].toInt())}
    }.unzip()
