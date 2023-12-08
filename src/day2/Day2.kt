package day2

class Day2() {

    private var input: List<String> = Util.readFileAsLinesUsingUseLines("./src/${javaClass.simpleName}/input")

    companion object {

        val testPart1 = listOf(
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green ",
        )
    }


    init {
        part1()
        part2()
    }

    private fun part1() {

        val expected = 8
        val testResult = testPart1.sumOf { isValidGameAndGetId(it) }
        if (testResult != expected) {
            println("Test Day1 failed with result: $testResult")
            return
        }

        val result = input.sumOf { isValidGameAndGetId(it) }
        println("Day1 part1: $result")
    }


    private fun isValidGameAndGetId(game: String): Int {

        val gameId = game.substring(game.indexOf(" ") + 1, game.indexOf(":")).toInt()

        val games = game.substring(game.indexOf(":") + 1).split(";")

        return gameId
    }


    private fun part2() {


    }

}