package hw1

import org.la4j.LinearAlgebra.InverterFactory
import org.la4j.Matrix
import org.la4j.inversion.GaussJordanInverter
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sqrt

// Condition Numbers
class ConditionNums private constructor() {

    // Criteria
    companion object {
        fun spectralCr(matrix: Matrix): Double {
            val inverted = InverterFactory.NO_PIVOT_GAUSS.create(matrix).inverse()
            return abs(matrix.determinant() * inverted.determinant())
        }

        fun ortegaCr(matrix: Matrix): Double {
            var product = 1.0
            val N = matrix.rows()
            for (i in 0..<N) {
                var sum = 0.0
                for (j in 0..<N) {
                    sum += (matrix[i, j] * matrix[i, j])
                }

                product *= sqrt(sum)
            }

            return product / abs(matrix.determinant())
        }

        fun angularCr(matrix: Matrix): Double {
            val inverted = InverterFactory.NO_PIVOT_GAUSS.create(matrix).inverse()
            var condA = 0.0
            val N = matrix.rows()
            for (i in 0..<N) {
                condA = max(condA, abs(matrix.getRow(i).euclideanNorm() * inverted.getColumn(i).euclideanNorm()))
            }

            return condA
        }
    }
}