package hw1

import org.la4j.Matrices
import org.la4j.Matrix
import org.la4j.inversion.GaussJordanInverter
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sqrt

class ConditionNumbers {
    companion object {
        fun spectralCriterion(matrix: Matrix): Double {
            val inverted = GaussJordanInverter(matrix).inverse()
            return abs(matrix.determinant() * inverted.determinant())
        }

        fun ortegaCriterion(matrix: Matrix): Double {
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

        fun angularCriterion(matrix: Matrix): Double {
            val inverted = GaussJordanInverter(matrix).inverse()
            var condA = 0.0
            val N = matrix.rows()
            for (i in 0..<N) {
                condA = max(condA, abs(matrix.getRow(i).euclideanNorm() * inverted.getColumn(i).euclideanNorm()))
            }

            return condA
        }
    }
}