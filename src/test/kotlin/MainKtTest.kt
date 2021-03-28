import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

internal class MainKtTest {

    @Test
    fun main() {
        main(arrayOf("uniq", "-c", "-s", "2", "-o", "testSources/output/1.txt", "testSources/input/1.txt"))
        assertEquals(
            File("testSources/outputExpected/1.txt").readLines(),
            File("testSources/output/1.txt").readLines()
        )

        main(arrayOf("uniq", "-o", "testSources/output/2.txt", "testSources/input/2.txt"))
        assertEquals(
            File("testSources/outputExpected/2.txt").readLines(),
            File("testSources/output/2.txt").readLines()
        )

        main(arrayOf("uniq", "-c", "-s", "2", "-o", "testSources/output/1.txt", "testSources/input/1.txt"))
        assertEquals(
            File("testSources/outputExpected/1.txt").readLines(),
            File("testSources/output/1.txt").readLines()
        )

        main(arrayOf("uniq", "-u", "-o", "testSources/output/3.txt", "testSources/input/3.txt"))
        assertEquals(
            File("testSources/outputExpected/3.txt").readLines(),
            File("testSources/output/3.txt").readLines()
        )

        main(arrayOf("uniq", "-o", "testSources/output/3.txt", "testSources/input/3.txt"))
        assertNotEquals(
            File("testSources/outputExpected/3.txt").readLines(),
            File("testSources/output/3.txt").readLines()
        )

    }
}