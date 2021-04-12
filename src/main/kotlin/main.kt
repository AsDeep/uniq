import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    var input = mutableListOf<String>()
    val parser = ArgsParser()
    parser.parseArgs(args)

    if (File(parser.input).isFile) {
        input = File(parser.input).readLines().toMutableList()
    } else if (parser.input.isNotEmpty()) {
        System.err.println("Invalid file")
        exitProcess(-1)
    }

    if (input.isEmpty()) {
        input = generateSequence { readLine() }.toMutableList()
    }

    val result = uniq(parser.ignoreReg, parser.onlyUnique, parser.count, parser.ignoreCount, input)

    if (parser.out.isNotEmpty()) {
        val output = File(parser.out).bufferedWriter()
        for (line in result) {
            output.write(line)
            output.newLine()
        }
        output.close()
    } else {
        for (line in result) println(line)
    }


}


fun uniq(i: Boolean, u: Boolean, c: Boolean, s: Int, input: List<String>): List<String> {
    val result = mutableListOf<String>()
    var lastLine = input[0]
    var counter = 1

    for (string in input.subList(1, input.lastIndex + 1)) {
        if (string.drop(s).equals(lastLine.substring(s), ignoreCase = i)) {
            counter += 1
        } else {
            if (c) lastLine = "$counter $lastLine"
            if ((u && counter == 1) || !u) result.add(lastLine)
            counter = 1
            lastLine = string
        }

    }
    if (c) lastLine = "$counter $lastLine"
    if ((u && counter == 1) || !u) result.add(lastLine)
    return result
}