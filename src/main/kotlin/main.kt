import java.io.File
import java.lang.IllegalArgumentException

fun main(args: Array<String>) {
    var input = mutableListOf<String>()
    val parser = ArgsParser()

    parser.parseArgs(args)
    if (File(parser.input).isFile) {
        input = File(parser.input).readLines().toMutableList()
        if (input.isEmpty()) throw IllegalArgumentException()
    } else {
        var k = readLine() ?: throw IllegalArgumentException()
        while (k.isNotEmpty()) {
            input.add(k)
            k = readLine() ?: break
        }
    }
    val result = when {
        parser.ignoreReg -> registerNotSensitive(parser.onlyUnique, parser.count, parser.ignoreCount, input)
        else -> registerSensitive(parser.onlyUnique, parser.count, parser.ignoreCount, input)
    }
    if (parser.out.isNotEmpty() && File(parser.out).isFile) {
        val output = File(parser.out).bufferedWriter()
        for (line in result) {
            output.write(line)
            output.newLine()
        }
        output.close()
    } else {
        if (parser.out.isNotEmpty() && !File(parser.out).isFile) {
            println("Error with output file. Printing output to console:")
        }
        for (line in result) println(line)
    }


}

fun registerSensitive(u: Boolean, c: Boolean, s: Int, input: List<String>): List<String> {
    val result = mutableListOf<String>()
    var lastLine = input[0]
    var counter = 1

    for (i in input.subList(1, input.lastIndex + 1)) {
        if ((s != 0 && i.substring(s) == lastLine.substring(s)) || (s == 0 && i == lastLine)) {
            counter += 1
        } else {
            if (c) lastLine = "$counter $lastLine"
            if ((u && lastLine !in result) || !u) result.add(lastLine)
            counter = 1
            lastLine = i
        }

    }
    if (c) lastLine = "$counter $lastLine"
    if ((u && lastLine !in result) || !u) result.add(lastLine)
    return result

}

fun registerNotSensitive(u: Boolean, c: Boolean, s: Int, input: List<String>): List<String> {
    val result = mutableListOf<String>()
    var lastLine = input[0]
    var counter = 1
    for (i in input.subList(1, input.lastIndex + 1)) {
        if ((s != 0 && i.substring(s).equals(lastLine.substring(s), ignoreCase = true)) ||
            (s == 0 && i.equals(lastLine, ignoreCase = true))
        ) {
            counter += 1
        } else {
            if (c) lastLine = "$counter $lastLine"

            if ((u && lastLine !in result) || !u) result.add(lastLine)
            counter = 1
            lastLine = i
        }

    }
    if (c) lastLine = "$counter $lastLine"
    if ((u && lastLine !in result) || !u) {
        result.add(lastLine)
    }
    println(result)
    return result
}