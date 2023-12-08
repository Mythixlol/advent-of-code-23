import java.io.File

class Util {
    companion object {
        fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
    }
}