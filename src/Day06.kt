import kotlin.math.abs

typealias Maze = List<List<Boolean>>

fun main() {
    val day = "06"

    var dir: Direction? = null
    var x: Int = -1
    var y: Int = -1

    var sizeX: Int = -1
    var sizeY: Int = -1
    val visited = mutableMapOf<Pair<Int, Int>, Int>()

    fun checkForGuard(row: String): Boolean {

        Direction.entries.forEach { direction ->
            val idx = row.indexOf(direction.dir)
            if (idx >= 0) {
                x = idx
                dir = direction
                return true
            }
        }
        return false
    }

    fun readMaze(input: List<String>): Maze {
        val maze = input.mapIndexed { idx, row ->
            if (checkForGuard(row)) {
                y = idx
            }
            row.map { it == '#' }
        }
        sizeX = maze.first().size
        sizeY = maze.size
        return maze
    }

    fun findNewDirection(maze: Maze, curX: Int, curY: Int, dir: Direction): Direction {

        val newX = curX + dir.moveX
        val newY = curY + dir.moveY

        return if (newX < 0 || newX >= sizeX || newY < 0 || newY >= sizeY)
            dir
        else if (!maze[newY][newX])
            dir
        else findNewDirection(maze, curX, curY, dir.rotate())
    }

    tailrec fun moveOne(maze: Maze, curX: Int, curY: Int, direction: Direction, steps: Int): Int {
        print("Currently at ${curX+1}, ${curY+1} and moving ${direction.dir}")
        if (curX < 0 || curX >= sizeX) return steps
        if (curY < 0 || curY >= sizeY) return steps

        val newDir = findNewDirection(maze, curX, curY, direction)
        val newX = curX + newDir.moveX
        val newY = curY + newDir.moveY

        val currPos = Pair(curX, curY)
        val alreadyVisited = visited.getOrDefault(currPos, 0)

        val count = if (alreadyVisited == 0) {
            visited[currPos] = newDir.bitmask
            1 // not visited yet
        }
        else if (alreadyVisited and newDir.bitmask > 0) {
            // already visited in same direction
            visited[currPos] = alreadyVisited or newDir.bitmask
            return steps
        }
        else { // already visited, but in different direction
            0
        }
        println(", counting: $count")

        return moveOne(maze, newX, newY, newDir, steps+count)
    }

    fun part1(input: List<String>): Int {

        val maze = readMaze(input)

        println("Starting position: ${x+1}, ${y+1}, $dir")

        return moveOne(maze, x, y, dir!!, 0)
    }

    fun part2(input: List<String>): Long {

        return 0L
    }


    val input = readInput("Day$day")
    part1(input).println()
    part2(input).println()

}

enum class Direction(val dir: String, val moveX: Int, val moveY: Int, val bitmask: Int) {
    UP("^", 0, -1, 1),
    DOWN("v", 0, 1, 2),
    LEFT("<", -1, 0, 4),
    RIGHT(">", 1, 0, 8);

    fun rotate() = when (this) {
        UP -> RIGHT
        RIGHT -> DOWN
        DOWN -> LEFT
        LEFT -> UP
    }
}


