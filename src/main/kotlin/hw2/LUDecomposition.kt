package hw2

import org.la4j.Matrices
import org.la4j.Matrix
import org.la4j.Vector
import kotlin.math.sqrt

class LUDecomposition private constructor() {
    companion object {
        fun sqrtMethod(A: Matrix): Matrix {
            require(Matrices.SYMMETRIC_MATRIX.test(A)) { "Матрица должна быть симметричной!" }
            val N = A.rows()
            val L: Matrix = Matrix.zero(N, N)
            for (i in 0..<N) {
                for (j in 0..i) {
                    var sum = 0.0
                    if (j != 0) {
                        for (k in 0..<j) {
                            sum += L[i, k] * L[j, k]
                        }
                    }

                    if (i == j) {
                        sum = sqrt(A[i, j] - sum)
                    }
                    else {
                        sum = (A[i, j] - sum) / L[j, j]
                    }

                    L[i, j] = sum
                }
            }

            return L
        }

        fun sqrtMethod2(matrix: Matrix): Matrix {
            require(Matrices.SYMMETRIC_MATRIX.test(matrix)) { "Матрица должна быть симметричной!" }
            val N = matrix.rows()
            val L: Matrix = Matrix.zero(N, N)
            for (i in 0..<N) {
                for (j in 0..i) {
                    var sum = matrix[i, j]
                    for (k in 0..<j) {
                        sum -= (L[i, k] * L[j,k])
                    }

                    if (i == j) {
                        L[i, j] = sqrt(sum)
                    }
                    else {
                        L[i, j] = sum / L[j, j]
                    }
                }
            }

            return L
        }

        val A1 = Matrix.from2DArray(arrayOf(
            doubleArrayOf(4.0, 3.0, 2.0),
            doubleArrayOf(3.0, 4.0, 1.0),
            doubleArrayOf(2.0, 1.0, 4.0)
        ))
        val b1 = Vector.fromArray(doubleArrayOf(1.0, 2.0, 3.0))

        val A2 = Matrix.diagonal(7, 7.0)
        val b2 = Vector.fromArray(doubleArrayOf(-3.0, -1.0, 1.0, 3.0, 33.0, 777.0, 3.14))

        val A3 = Matrix.from2DArray(arrayOf(
            doubleArrayOf(11.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0),
            doubleArrayOf(2.0, 22.0, 3.0, 4.0, 5.0, 6.0, 7.0),
            doubleArrayOf(3.0, 3.0, 33.0, 4.0, 5.0, 6.0, 7.0),
            doubleArrayOf(4.0, 4.0, 4.0, 44.0, 5.0, 6.0, 7.0),
            doubleArrayOf(5.0, 5.0, 5.0, 5.0, 55.0, 6.0, 7.0),
            doubleArrayOf(6.0, 6.0, 6.0, 6.0, 6.0, 66.0, 7.0),
            doubleArrayOf(7.0, 7.0, 7.0, 7.0, 7.0, 7.0, 77.0),
            )
        )
        val b3 = Vector.fromArray(doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0))

        val A4 = Matrix.from1DArray(1, 1, doubleArrayOf(777.0))
        val b4 = Vector.fromArray(doubleArrayOf(666.0))
    }
}