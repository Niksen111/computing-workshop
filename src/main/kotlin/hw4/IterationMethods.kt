package hw4

import org.la4j.Matrix
import org.la4j.Vector
import org.la4j.inversion.GaussJordanInverter
import kotlin.math.abs

class IterationMethods private constructor() {
    companion object {
        fun normalizeMatrices(A: Matrix, b: Vector): Pair<Matrix, Vector> {
            val n = A.rows()
            val H = Matrix.zero(n, n)
            val g = Vector.zero(n)
            for (i in 0..<n) {
                for (j in 0..<n) {
                    if (i != j) {
                        H[i, j] = - (A[i, j] / A[i, i])
                    }
                }

                g[i] = b[i] / A[i, i]
            }

            return Pair(H, g)
        }

        fun simpleIterationMethod(H: Matrix, g: Vector, eps: Double): Pair<Vector, Int> {
            val n = H.rows()
            val evaluation = H.norm() / (1 - H.norm())
            var xk = Vector.zero(n)
            var k = 1
            while (true) {
                val xkNext = H.multiply(xk).add(g)
                if (abs(evaluation * (xkNext.subtract(xk)).norm()) < eps) {
                    break
                }

                xk = xkNext.copy()
                k++
            }

            return Pair(xk, k)
        }

        fun zeidelMethod(H: Matrix, g: Vector, eps: Double): Pair<Vector, Int> {
            val n = H.rows()
            val hl = Matrix.zero(n, n)
            val hr = Matrix.zero(n, n)
            val E = Matrix.diagonal(n, 1.0)
            for (i in 0..<n) {
                for (j in 0..<n) {
                    if (i > j) {
                        hl[i, j] = H[i, j]
                    }
                    else {
                        hr[i, j] = H[i, j]
                    }
                }
            }

            val evaluation = H.norm() / (1 - H.norm())
            val eMinusHlInverted = GaussJordanInverter(E.subtract(hl)).inverse()
            var k = 1
            var xk = Vector.zero(n)
            var xkNext: Vector
            while (true) {
                xkNext = eMinusHlInverted.multiply(hr).multiply(xk).add(eMinusHlInverted.multiply(g))
                if (abs(evaluation * (xkNext.subtract(xk)).norm()) < eps) {
                    break
                }

                xk = xkNext.copy()
                k++
            }

            return Pair(xkNext, k)
        }
    }
}