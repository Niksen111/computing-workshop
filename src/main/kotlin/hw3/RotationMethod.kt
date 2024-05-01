package hw3

import org.la4j.LinearAlgebra.SolverFactory
import org.la4j.Matrix
import org.la4j.Vector
import kotlin.math.sqrt

class RotationMethod private constructor() {
    companion object {
        fun createTij(A: Matrix, i: Int, j: Int): Matrix {
            val n = A.rows()
            val norm = sqrt(A[i, i] * A[i, i] + A[j, i] * A[j, i])
            val cosFi = A[i, i]/norm
            val sinFi = - A[j, i] / norm
            val T = Matrix.diagonal(n, 1.0)
            T[i, i] = cosFi
            T[j, j] = cosFi
            T[i, j] = -sinFi
            T[j, i] = sinFi
            return T
        }

        fun rotation(A: Matrix, b: Vector): XQRMatrices {
            val n = A.rows()
            var Q = Matrix.diagonal(n, 1.0)
            var R = A.copy()
            var B = b.copy()
            for (i in 0..<n-1) {
                for (j in i+1..<n) {
                    val Tij = createTij(R, i, j)
                    R = Tij.multiply(R)
                    Q = Q.multiply(Tij.transpose())
                    B = Tij.multiply(B)
                }
            }

            val x = SolverFactory.SMART.create(R).solve(B)
            return XQRMatrices(x, Q, R)
        }
    }
}