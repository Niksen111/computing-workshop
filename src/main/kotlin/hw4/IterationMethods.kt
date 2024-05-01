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
                g[i] = b[i] / A[i, i]
                for (j in 0..<n) {
                    if (i != j) {
                        H[i, j] = - A[i, j] / A[i, i]
                    }
                }
            }

            return Pair(H, g)
        }

        fun simpleIterationMethod(H: Matrix, g: Vector, e: Double): Pair<Vector, Int> {
            val n = H.rows()
            val eval = H.norm() / (1 - H.norm())
            var x = Vector.zero(n)
            var k = 1
            var xk = H.multiply(x).add(g)
            var tmp = xk.subtract(x)
            var check = eval * tmp.norm()
            while (abs(check) > e) {
                x = xk.copy()
                xk = H.multiply(x).add(g)
                tmp = xk.subtract(x)
                check = eval * tmp.norm()
                k++
            }

            return Pair(xk, k)
        }

        fun zeidelMethod(H: Matrix, g: Vector, e: Double): Pair<Vector, Int> {
            val n = H.rows()
            val hl = Matrix.zero(n, n)
            val hr = Matrix.zero(n, n)
            val E = Matrix.diagonal(n, 1.0)
            for (i in 0..<n) {
                for (j in 0..<n) {
                    if (i > j) {
                        hr[i, j] = H[i, j]
                    }
                    else {
                        hl[i, j] = H[i, j]
                    }
                }
            }

            val eval = H.norm() / (1 - H.norm())

            val tmpInv = GaussJordanInverter(E.subtract(hl)).inverse()
            val constAdd = tmpInv.multiply(g)
            var tmpMat: Matrix
            var x: Vector
            var xk = constAdd.copy()
            var tmp = xk.copy()
            var check = eval * tmp.norm()
            var k = 0
            while (abs(check) > e) {
                x = xk.copy()
                tmpMat = tmpInv.multiply(hr)
                xk = tmpMat.multiply(x).add(constAdd)
                tmp = xk.subtract(x)
                check = eval * tmp.norm()
                k++
            }

            return Pair(xk, k)
        }
    }
}