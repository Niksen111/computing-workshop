package hw7

import org.la4j.Matrix
import java.io.File

class ResultDrawer {
    companion object {

        fun draw(matrix1: Matrix, matrix2: Matrix) {

            var file = File("src/main/resources/data1.csv")
            file.bufferedWriter().use { writer ->
                for (i in 1..<matrix1.rows()) {
                    writer.write("${matrix1[i, 0]}, ${matrix1[i, 1]}, ${matrix1[i, 2]}")
                    writer.newLine()
                }
            }

            file = File("src/main/resources/data2.csv")
            file.bufferedWriter().use { writer ->
                for (i in 1..<matrix2.rows()) {
                    writer.write("${matrix2[i, 0]}, ${matrix2[i, 1]}")
                    writer.newLine()
                }
            }

            ProcessBuilder("python", "src/main/resources/drawer.py").start()

        }
    }
}