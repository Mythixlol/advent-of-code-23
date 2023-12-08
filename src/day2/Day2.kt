package day2

class Day2 {

    private var input: List<String> = Util.readFileLineByLine("./src/${javaClass.simpleName}/input")

    companion object {

        val testPart1 = listOf(
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green ",
        )
    }

    private val colors = listOf("green", "red", "blue")

    init {
        part1()
        part2()
    }

    private fun part1() {

        val expected = 8
        val testResult = testPart1.sumOf { getIdOfValidGame(it) }
        if (testResult != expected) {
            println("Test ${javaClass.simpleName} Part 1 failed with result: $testResult")
            return
        }

        val result = input.sumOf { getIdOfValidGame(it) }
        println("${javaClass.simpleName} Part 1: $result")
    }


    data class Game(
        var id: Int, var red: Int, var green: Int, var blue: Int, var isValidGame: Boolean
    )

    data object MaxColorValues {
        const val GREEN = 13
        const val RED = 12
        const val BLUE = 14
    }


    private fun getIdOfValidGame(game: String): Int {

        val resultList = parseGames(game)

        if (resultList.maxOf { it.green } <= MaxColorValues.GREEN && resultList.maxOf { it.red } <= MaxColorValues.RED && resultList.maxOf { it.blue } <= MaxColorValues.BLUE) return resultList.first().id
        return 0

    }

    private fun parseGames(game: String): List<Game> {

        val gameId = game.substring(game.indexOf(" ") + 1, game.indexOf(":")).toInt()

        val games = game.substring(game.indexOf(":") + 1).split(";")

        val resultList = games.map { singleGame ->
            getSingleGameFromString(singleGame, gameId)
        }

        return resultList
    }


    private fun getSingleGameFromString(input: String, gameId: Int): Game {

        var green = 0
        var red = 0
        var blue = 0

        input.split(",").forEach { color ->

            val trimmed = color.trim()
            val index = colors.indexOfFirst { trimmed.contains(it) }
            val count = trimmed.substring(0, trimmed.indexOf(" ")).toInt()

            when (index) {
                0 -> green += count
                1 -> red += count
                2 -> blue += count
            }
        }

        return Game(red = red, green = green, blue = blue, id = gameId, isValidGame = false)
    }

    private fun part2() {

        val expected = 2286
        val testResult = testPart1.sumOf { calculatePowerOfMinMax(parseGames(it)) }
        if (testResult != expected) {
            println("Test ${javaClass.simpleName} Part 2 failed with result: $testResult")
            return
        }

        val result = input.sumOf { calculatePowerOfMinMax(parseGames(it)) }
        println("${javaClass.simpleName} Part 2: $result")
    }

    private fun calculatePowerOfMinMax(games: List<Game>): Int {
        return games.maxOf { it.green } * games.maxOf { it.red } * games.maxOf { it.blue }
    }

}

