import java.io.File

class Util {
    companion object {
        fun readFileLineByLine(fileName: String): List<String> = File(fileName).useLines { it.toList() }
    }
}