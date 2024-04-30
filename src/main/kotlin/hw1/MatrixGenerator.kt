package hw1

import org.la4j.Matrix
import org.la4j.Vector
import kotlin.math.abs

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

        fun tridiagonalMatrix(n: Int): Matrix {
            val matrix: Matrix = Matrix.zero(n, n)
            for (i in 0..<n) {
                for (j in 0..<n) {
                    if (abs(i - j) <= 1) {
                        matrix[i, j] = i + j + 1.0
                    }
                }
            }

            return matrix
        }

        val lebedevaAMatrix = Matrix.from2DArray(arrayOf(
            doubleArrayOf(3.278164, 1.046583, -1.378574),
            doubleArrayOf(1.046583, 2.975937, 0.934251),
            doubleArrayOf(-1.378574, 0.934251, 4.836173)
        ))

        val lebedevaBVector = Vector.fromArray(
            doubleArrayOf(-0.527466, 2.526877, 5.165441)
        )

        val hitriyA = Matrix.from1DArray(2, 2, doubleArrayOf(1.0, 0.99, 0.99, 0.98))
        val hitriyB = Vector.fromArray(doubleArrayOf(2.0, 2.0))
    }
}