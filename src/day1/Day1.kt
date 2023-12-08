package day1

class Day1(val input: List<String>) {

    companion object {

        val testDay1 = listOf(
            "1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet"
        )

        val testDay2 = listOf(
            " two1nine",
            "eightwothree",
            "abcone2threexyz",
            "xtwone3four",
            "4nineeightseven2",
            "zoneight234",
            "7pqrstsixteen"
        )

        val numbersList = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

        fun run() {
            val input = Util.readFileAsLinesUsingUseLines("./src/day1/input")
            val day = Day1(input)

            day.part1()
            day.part2()
        }
    }

    fun part1() {

        val expected = 142
        val testResult = testDay1.sumOf { parseWordForNumbers(it) }
        if (testResult != expected) {
            println("Test Day1 failed with result: $testResult")
            return
        }

        val result = input.sumOf { parseWordForNumbers(it) }
        println("Day1 part1: $result")
    }

    private fun parseWordForNumbers(inputString: String): Int {

        var firstDigit: Char? = null
        var lastDigit: Char? = null

        for (char in inputString) {
            if (char.isDigit()) {
                firstDigit ?: run { firstDigit = char }
                lastDigit = char
            }
        }

        firstDigit ?: return 0

        return "$firstDigit$lastDigit".toInt()
    }


    fun part2() {

        val expected = 281
        val testResult = convertWords(testDay2).sumOf { parseWordForNumbers(it) }
        if (testResult != expected) {
            println("Test Day2 failed with result: $testResult")
            return
        }


        val result = convertWords(input).sumOf { parseWordForNumbers(it) }
        println("Day1 part2: $result")

    }

    private fun convertWords(input: List<String>): List<String> {
        return input.asSequence().map { replaceWordWithInt(it) }.toList()
    }

    private fun replaceWordWithInt(word: String): String {

        val indexList: ArrayList<Pair<Int, Int>> = ArrayList()

        numbersList.asSequence().forEach { number ->
            val list = generateSequence(word.indexOf(number)) { word.indexOf(number, it + 1) }
                .takeWhile { it >= 0 }
                .toList()

            list.forEach { index -> indexList.add(Pair(index, numbersList.indexOf(number) + 1)) }
        }

        var newWord = word

        indexList.sortedByDescending { it.first }.forEach { indexPair ->
            val index = indexPair.first
            val replace = indexPair.second
            newWord = newWord.substring(0, index) + replace + newWord.substring(index + 1)
        }

        return newWord
    }
}