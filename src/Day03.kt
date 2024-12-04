import kotlin.math.abs

fun main() {

    fun sumOInstructions(str: String): Long {
        val regex = """mul\((\d+),(\d+)\)""".toRegex()
        val validMatches = regex.findAll(str).toList()


        return validMatches.sumOf {

            val g1 = it.groups[1]!!.value.toLong()
            val g2 = it.groups[2]!!.value.toLong()
            g1 * g2
        }
    }


    fun part1(input: List<String>): Long {

        val allValid = input.sumOf { line ->
            sumOInstructions(line)
        }
        return allValid

    }

    fun part2(input: List<String>): Long {
        // The entire input needs to be treated as one line, despite the line breaks in the input. Therefore, we join the
        // input lines into a single string

        val oneInput = input.joinToString("")

        // Remove everything between a don't() and the next do(), since this code is deactivated anyway
        val removeRegex = """don't.*?(do\()""".toRegex()

        val transformed = listOf(oneInput).map {
            removeRegex.replace(it) { match ->
                match.groups[1]!!.value }
        }

        val allValid = transformed.sumOf { line ->
            sumOInstructions(line)
        }
        return allValid

    }

    // Read the input from the `src/Day03.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()

}


