package hw1

import org.la4j.LinearAlgebra.SolverFactory
import org.la4j.Matrix
import org.la4j.Vector
import java.text.DecimalFormat
import kotlin.math.abs
import kotlin.random.Random
import kotlin.random.asJavaRandom

fun performCalculations(A: Matrix, variation: Matrix, b: Vector) {
    val formatter = DecimalFormat("0.00000000000")
    println("Исходная матрица:")
    println(A.toCSV(formatter))

    println("Правая часть уравнения:")
    println(b.toCSV(formatter))
    println()

    println("Решение уравнения:")
    val solution = SolverFactory.SMART.create(A).solve(b)
    println(solution.toCSV(formatter))
    println()

    println("Вариация:")
    println(variation.toCSV(formatter))

    println("Варьированная матрица:")
    val Av = A.add(variation)
    println(Av.toCSV(formatter))

    println("Решение варьированного уравнения:")
    val solutionVariated = SolverFactory.SMART.create(Av).solve(b)
    println(solutionVariated.toCSV(formatter))
    println()

    println("Погрешность решения:")
    val solutionError = solution.subtract(solutionVariated)
    solution.each { _, value-> abs(value) }
    println(solutionError.toCSV(formatter))
    println()

    ConditionNums.criteriaComputation(A, Av, variation)
}

fun hilbert2x2MatrixTest() {
    val n = 2
    val A = MatrixGenerator.hilbertMatrix(n)
    val variation = Matrix.constant(n, n, 1e-2 - 1e-10)
    val b = Vector.random(n, Random.asJavaRandom())
    performCalculations(A, variation, b)
}

fun hilbert8x8MatrixTest() {
    val n = 8
    val A = MatrixGenerator.hilbertMatrix(n)
    val variation = Matrix.constant(n, n, 1e-2 - 1e-10)
    val b = Vector.random(n, Random.asJavaRandom())
    performCalculations(A, variation, b)
}

fun tridiagonal6x6MatrixTest() {
    val n = 6
    val A = MatrixGenerator.tridiagonalMatrix(n)
    val variation = Matrix.constant(n, n, 1e-2 - 1e-10)
    val b = Vector.random(n, Random.asJavaRandom())
    performCalculations(A, variation, b)
}

fun lebedevaMatrixTest() {
    val A = MatrixGenerator.lebedevaAMatrix
    val variation = Matrix.constant(3, 3, 1e-2 - 1e-10)
    val b = MatrixGenerator.lebedevaBVector
    performCalculations(A, variation, b)
}

fun hitraiaMatrixTest() {
    val A = MatrixGenerator.hitriyA
    val variation = Matrix.constant(2, 2, 1e-2 - 1e-10)
    val b = MatrixGenerator.hitriyB
    performCalculations(A, variation, b)
}

fun tests() {
    hilbert2x2MatrixTest()
    hilbert8x8MatrixTest()
    tridiagonal6x6MatrixTest()
    lebedevaMatrixTest()
    hitraiaMatrixTest()
}

fun main() {
    println("Вычислительный практикум")
    println("Выполнил: Ксенчик Никита")
    println("Задача 1:")
    println("ВЛИЯНИЕ ОШИБОК ОКРУГЛЕНИЯ НА РЕШЕНИЕ СЛАУ. ЧИСЛА ОБУСЛОВЛЕННОСТИ.")
    println()

    tests()
}