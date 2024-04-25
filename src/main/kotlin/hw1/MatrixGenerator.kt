package hw1

import org.la4j.Matrix
import kotlin.math.abs
import kotlin.random.Random
import kotlin.random.asJavaRandom

class MatrixGenerator private constructor() {
    companion object {
        fun hilbertMatrix(n: Int): Matrix {
            val matrix: Matrix = Matrix.zero(n, n)
            for (i in 0..<n) {
                for (j in 0..<n) {
                    matrix[i, j] = 1.0 / (i + j + 1)
                }
            }

            return matrix
        }

        fun randomTridiagonalMatrix(n: Int): Matrix {
            val matrix: Matrix = Matrix.random(n, n, Random.asJavaRandom())
            for (i in 0..<n) {
                for (j in 0..<n) {
                    if (abs(i - j) > 1) {
                        matrix[i, j] = 0.0
                    }
                }
            }

            return matrix
        }
    }
}