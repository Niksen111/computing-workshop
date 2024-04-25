package hw2

import org.la4j.Matrices
import org.la4j.Matrix
import kotlin.math.sqrt

class LUDecomposition private constructor() {
    companion object {
        fun sqrtMethod(matrix: Matrix): Matrix {
            require(Matrices.SYMMETRIC_MATRIX.test(matrix)) { "Матрица должна быть симметричной!" }
            val N = matrix.rows()
            val L: Matrix = Matrix.zero(N, N)
            for (i in 0..<N) {
                for (j in 0..i) {
                    if (i == j) {
                        var sum = matrix[i, i]
                        for (k in 0..<i) {
                            sum -= (L[i, k] * L[i, k])
                        }

                        L[i,i] = sqrt(sum)
                    } else {
                        var sum = matrix[i, j]
                        for (k in 0..<j) {
                            sum -= (L[i, k] * L[j,k])
                        }

                        L[i, j] = sum / L[j, j]
                    }
                }
            }

            return L
        }
    }
}