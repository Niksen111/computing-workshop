package hw5

import org.la4j.Matrix
import org.la4j.Vector
import kotlin.math.abs
import kotlin.math.sqrt

class PartialEigennumberProblem private constructor() {
    companion object {
        fun powerMethod(A: Matrix, b: Vector, eps: Double): Triple<Double, Vector, Int> {
            var k = 1
            var xk = b.copy()
            while (true) {
                var xkNext = A.multiply(xk)
                val eigennumber = sqrt(xkNext.innerProduct(xkNext) / xk.innerProduct(xk))
                val aposteriorError = (xkNext.subtract(xk.multiply(eigennumber))).norm() / xk.norm()
                if (xkNext.norm() > 100) {
                    xkNext = xkNext.divide(xkNext.norm())
                }

                if (aposteriorError < eps) {
                    return Triple(eigennumber, xkNext, k)
                }

                xk = xkNext.copy()
                k++
            }
        }

        fun scalarProductMethod(A: Matrix, b: Vector, c: Vector, eps: Double): Triple<Double, Vector, Int> {
            var xk = b.copy()
            var yk = c.copy()
            var k = 0
            while (true) {
                var xkNext = A.multiply(xk)
                var ykNext = A.multiply(yk)
                val eigennumber = xkNext.innerProduct(ykNext) / xk.innerProduct(ykNext)
                val aposteriorError = (xkNext.subtract(xk.multiply(eigennumber))).norm() / xk.norm()
                if (xkNext.norm() > 100) {
                    xkNext = xkNext.divide(xkNext.norm())
                }

                if (ykNext.norm() > 100) {
                    ykNext = ykNext.divide(ykNext.norm())
                }

                if (aposteriorError < eps) {
                    return Triple(eigennumber, ykNext, k)
                }

                xk = xkNext.copy()
                yk = ykNext.copy()
                k++
            }
        }
    }
}