package hw7

import org.la4j.Matrix

// Пакулина 6 Вариант
fun test1() {
    println("Найти решение уравнения:")
    println(" -(x - 2) / (x + 2) * u'' + x * u' + (1 - sin(x)) * u = x^2")
    println("p = -(x - 2) / (x + 2)")
    println("q = x")
    println("r = 1 - sin(x)")
    println("f = x^2")
    println("Краевые условия:")
    println("a = -1; b = 1")
    println("u(a) = 0; u(b) = 0")

    val n = 10
    val eps = 1e-4
    val params = GridMethods.paramsPakulina
    val answer = GridMethods.solveFdm(params, n, eps)
    val matrix1 = Matrix.zero(answer.discretization.length(), 3)
    answer.discretization.forEachIndexed { i, d ->
        matrix1[i, 0] = d
        matrix1[i, 1] = answer.approx[i]
        matrix1[i, 2] = 2.82
    }

    val matrix2 = Matrix.zero(answer.ns.size, 2)
    answer.ns.forEachIndexed { i, d ->
        matrix2[i, 0] = d.toDouble()
        matrix2[i, 1] = answer.norms[i]
    }

    ResultDrawer.draw(matrix1, matrix2)
}

fun test2() {
    println("Найти решение уравнения:")
    println(" u'' = Pi^2 * sin(Pi * x)")
    println("p = 1")
    println("q = 0")
    println("r = 0")
    println("f = Pi^2 * sin(Pi * x)")
    println("Краевые условия:")
    println("a = 0; b = 1")
    println("u(a) = 0; u(b) = 0")
    println("Реальное решение: sin(Pi * x)")

    val n = 2
    val eps = 1e-4
    val params = GridMethods.paramsMy
    val answer = GridMethods.solveFdm(params, n, eps)
    val matrix1 = Matrix.zero(answer.discretization.length(), 3)
    answer.discretization.forEachIndexed { i, d ->
        matrix1[i, 0] = d
        matrix1[i, 1] = answer.approx[i]
        matrix1[i, 2] = params.u(d)
    }

    val matrix2 = Matrix.zero(answer.ns.size, 2)
    answer.ns.forEachIndexed { i, d ->
        matrix2[i, 0] = d.toDouble()
        matrix2[i, 1] = answer.norms[i]
    }

    ResultDrawer.draw(matrix1, matrix2)
}

fun tests() {
//    test1()
    test2()
}

fun main() {
    println("Вычислительный практикум")
    println("Выполнил: Ксенчик Никита")
    println("Задача 7:")
    println("КРАЕВАЯ ЗАДАЧА. СЕТОЧНЫЕ МЕТОДЫ")
    println()

    tests()
}

























// fun test1() {
//    val u = { x: Double -> 2 * x * x * sin(x) - x * x}
//    val p = { _: Double -> 1.0 }
//    val q = { x: Double -> -4.0 / x }
//    val r = { x: Double -> 6 / (x * x) }
//    val f = { x: Double -> -2 * x * x * sin(x) }
//    val a = 0.0
//    val b = PI / 6
//    val alpha = 0.0
//    val beta = 0.0
//    val alpha1 = 1.0
//    val beta1 = 1.0
//    val alpha2 = 0.0
//    val beta2 = 0.0
//    println("Найти решение уравнения:")
//    println("u'' - (4 / x) * u' + (6 / x^2) * u = -2x^2 * sin(x)")
//    println("q = -4 / x")
//    println("r = 6 / x^2")
//    println("f = -2x^2 * sin(x)")
//    println("Краевые условия:")
//    println("a = 0; b = PI / 6")
//    println("u(a) = 0; u(b) = 0")
//    println()
//    performComputation(DeParams(u, p, q, r, f, a, b ,alpha, alpha1, alpha2, beta, beta1, beta2))
//}