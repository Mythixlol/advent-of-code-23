package day3

import Util

class Day3 {

    private var input: List<String> = Util.readFileLineByLine("./src/${javaClass.simpleName}/input")

    companion object {

        val part1TestData = """467..114..
       ...*......
       ..35..633.
       ......#...
       617*......
       .....+.58.
       ..592.....
       ......755.
       ...$.*....
       .664.598.."""

    }

    init {

        part1()
        part2()
    }


    data class Day3Number(
        var line: Int, var value: Int, var start: Int, var end: Int
    )


    private fun part1() {

        val expected = 4361
        val preparedTestdata = prepareInput(part1TestData.split("\n"))
        val testResult = calculateSumOfDataList(preparedTestdata)
        if (testResult != expected) {
            println("Test ${javaClass.simpleName} Part 1 failed with result: $testResult")
            return
        }

        val prepareData = prepareInput(input)

        val result = calculateSumOfDataList(prepareData)


    }

    // pad dots at the beginning for easy parsing
    private fun prepareInput(input: List<String>): List<String> {
        val resultMap = ArrayDeque(input.map { ".${it.trim()}." })
        val padLine = ".".repeat((resultMap.first().length))

        resultMap.addFirst(padLine)
        resultMap.addLast(padLine)

        return resultMap
    }

    fun calculateSumOfDataList(input: List<String>): Int {

        val numbers = input.mapIndexed { index, element -> parseValidNumbers(element, index) }



        return 0
    }


    private fun createDataList(input: String): List<Number> {
        return emptyList()
    }

    private

    fun parseValidNumbers(line: String, lineNumber: Int): ArrayList<Day3Number> {

        val numbers: ArrayList<Day3Number> = arrayListOf()

        var startIndex: Int? = null
        var endIndex: Int? = null
        var digits = ""
        var cycle = false

        for (index in line.indices) {
            if (line[index].isDigit()) {
                startIndex ?: run { startIndex = index }
                endIndex = index
                digits += line[index]
                cycle = true
                continue
            } else if (line[index] != '.') {
                val day3Numb = Day3Number(
                    line = lineNumber, value = -1, start = index, end = index
                )
                numbers.add(day3Numb)
            }
            if (cycle) {
                val day3Numb = Day3Number(
                    line = lineNumber, value = digits.toInt(), start = startIndex!!, end = endIndex!!
                )
                numbers.add(day3Numb)

                startIndex = null
                endIndex = null
                digits = ""
                cycle = false

            }
        }

        return numbers
    }


    private fun part2() {


    }

}