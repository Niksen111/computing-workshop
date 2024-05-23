package hw7

import org.la4j.LinearAlgebra.SolverFactory
import org.la4j.Matrix
import org.la4j.Vector
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.sin

class GridMethods {
    companion object {
        fun genAB(params: DeParams, discret: Vector): Pair<Matrix, Vector> {
            val n = discret.length()
            val a = Matrix.zero(n, n)
            val b = Vector.zero(n)
            val h = abs(params.b - params.a) / (n - 1)

            a[0, 0] = h * params.alpha1 + 1.5 * params.alpha2
            a[0, 1] = -2 * params.alpha2
            a[0, 2] = 0.5 * params.alpha2
            b[0] = h * params.alpha
            for ( i in 1..n-2) {
                a[i, i - 1] = -params.p(discret[i]) - params.q(discret[i])
                a[i, i + 1] = -params.p(discret[i]) - params.q(discret[i])
                a[i, i] = -(a[i, i - 1] + a[i, i + 1] - h * h * params.r(discret[i]))
                b[i] = h * h * params.f(discret[i])
            }

            a[n - 1, n - 3] = 0.5 * params.beta2
            a[n - 1, n - 2] = - 2 * params.beta2
            a[n - 1, n - 1] = h * params.beta1 + 1.5 * params.beta2
            b[n - 1] = h * params.beta

            return Pair(a, b)
        }

        fun richardson(approx: Vector, prevApprox: Vector): Vector {
            require(approx.length() - 1 == 2 * (prevApprox.length() - 1)) { "Invalid sizes!" }

            var diff = Vector.zero(approx.length())

            for ( i in 0..<prevApprox.length()) {
                diff[2 * i] = prevApprox[i]
                if ( i != (prevApprox.length() - 1)) {
                    diff[2 * i + 1] = (prevApprox[i + 1] + prevApprox[i]) / 2
                }
            }

            diff = approx.subtract(diff).divide(3.0)

            return diff
        }

        fun solveFdm(params: DeParams, startSize: Int, eps: Double
        ): Answer {
            require(abs(params.alpha1) + abs(params.alpha2) != 0.0)
            require(abs(params.beta1) + abs(params.beta2) != 0.0)
            require(params.alpha1 * params.alpha2 >= 0)
            require(params.beta1 * params.beta2 >= 0)

            var prevApprox: Vector? = null
            var approx: Vector? = null
            var norm = 0.0
            var discret: Vector? = null
            var ns = mutableListOf<Int>()
            var norms = mutableListOf<Double>()
            var n = startSize
            val segSpliterator: (Int) -> Vector = { locN ->
                Vector.fromArray(
                    (0..locN).map { x -> params.a + x * (params.b - params.a) / locN }.toDoubleArray())
            }

            while (prevApprox == null || norm >= eps) {
                if (approx != null) {
                    prevApprox = approx.copy()
                }
                else {
                    discret = segSpliterator(n)
                    val ab = genAB(params, discret)
                    prevApprox = SolverFactory.SMART.create(ab.first).solve(ab.second)
                }

                n *= 2
                discret = segSpliterator(n)
                val ab = genAB(params, discret)
                approx = SolverFactory.SMART.create(ab.first).solve(ab.second)
                val diff = richardson(approx, prevApprox)
                approx = approx.add(diff)
                norm = diff.norm()
                ns.add(n)
                norms.add(norm)
            }

            return Answer(discret!!, approx!!, ns, norms)
        }
        
        val paramsPakulina = 
            DeParams(
                u = { _: Double -> -2.82},
                p = { x: Double -> -(x - 2) / (x + 2) },
                q = { x: Double -> x },
                r = { x: Double -> 1 - sin(x) },
                f = { x: Double -> x * x },
                a = -1.0,
                b = 1.0,
                alpha = 0.0,
                beta = 0.0,
                alpha1 = 1.0,
                beta1 = 1.0,
                alpha2 = 0.0,
                beta2 = 0.0)
        
        val paramsMy = 
            DeParams(
                u = { x: Double -> sin(PI * x) },
                p = { x: Double -> 1.0 },
                q = { x: Double -> 0.0 },
                r = { x: Double -> 0.0 },
                f = { x: Double -> PI * PI * sin(PI * x) },
                a = 0.0,
                b = 1.0,
                alpha = 0.0,
                beta = 0.0,
                alpha1 = 1.0,
                beta1 = 1.0,
                alpha2 = 0.0,
                beta2 = 0.0
            )
    }
}