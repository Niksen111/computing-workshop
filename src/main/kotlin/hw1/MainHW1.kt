package hw1

import dnl.utils.text.table.TextTable
import org.la4j.Matrix
import org.la4j.Vector
import org.la4j.linear.GaussianSolver
import kotlin.math.abs

fun performCalculations(A: Matrix, variation: Matrix, b: Vector) {
    println("Исходная матрица:")
    println(A)
    println()

    println("Правая часть уравнения:")
    println(b)
    println()

    println("Решение уравнения:")
    val solution = GaussianSolver(A).solve(b)
    println(solution)
    println()

    println("Решение варьированного уравнения:")
    val Av = A.add(variation)
    val solutionVariated = GaussianSolver(Av).solve(b)
    println(solutionVariated)
    println()

    println("Погрешность решения:")
    val solutionError = solution.subtract(solutionVariated).each { _, value-> abs(value) }
    println(solutionError)
    println()

    println("Качественные критерии:")
    val header = arrayOf("Варьирование", "Спектральный", "Объемный", "Угловой")
    val data = arrayOf(
        arrayOf(0.0, ConditionNums.spectralCr(A), ConditionNums.ortegaCr(A), ConditionNums.angularCr(A)),
        arrayOf(variation[0, 0], ConditionNums.spectralCr(Av), ConditionNums.ortegaCr(Av), ConditionNums.angularCr(Av))
    )
    val table = TextTable(header, data)
    table.printTable()
}

fun tests() {

}

fun main(args: Array<String>) {
    println("Вычислительный практикум")
    println("Выполнил: Ксенчик Никита")
    println("Задача 1:")
    println("ВЛИЯНИЕ ОШИБОК ОКРУГЛЕНИЯ НА РЕШЕНИЕ СЛАУ. ЧИСЛА ОБУСЛОВЛЕННОСТИ.")
    println()

    tests()
}