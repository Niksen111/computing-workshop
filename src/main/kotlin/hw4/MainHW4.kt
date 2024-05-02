package hw4

import hw1.ConditionNums
import hw1.MatrixGenerator
import hw2.LUDecomposition
import hw3.RotationMethod
import org.la4j.LinearAlgebra
import org.la4j.Matrix
import org.la4j.Vector
import org.la4j.matrix.SparseMatrix
import kotlin.random.Random
import kotlin.random.asJavaRandom

fun performComputation(A: Matrix, b: Vector, eps: Double) {
    println("Матрица A:")
    println(A)

    println("Критерии матрицы A:")
    ConditionNums.criteriaComputation(A)

    println("Правая часть уравнения (b):")
    println(b)
    println()

    println("Точное решение системы:")
    val solution = LinearAlgebra.SolverFactory.SMART.create(A).solve(b)
    println(solution)
    println()

    println("Точность итерационных методов: $eps")
    println()

    val normSys = IterationMethods.normalizeMatrices(A, b)

    println("Решение методом простой итерации:")
    val simpleIterationSolution = IterationMethods.simpleIterationMethod(normSys.first, normSys.second, eps)
    println(simpleIterationSolution.first)
    println()

    println("Количество итераций в методе: ${simpleIterationSolution.second}")
    println()

    println("Решение методом Зейделя:")
    val zeidelMethodSolution = IterationMethods.zeidelMethod(normSys.first, normSys.second, eps)
    println(zeidelMethodSolution.first)
    println()

    println("Количество итераций в методе: ${zeidelMethodSolution.second}")
    println()
}

fun bigTest() {
    val A = Matrix.diagonal(250, 100.0)
        .add(SparseMatrix.random(250, 250 , 0.282, Random.asJavaRandom()))
    val b = Vector.random(250, Random.asJavaRandom())
    val eps = 1e-10
    performComputation(A, b, eps)
}

fun lebedevaMatrixTest() {
    val A = MatrixGenerator.lebedevaAMatrix
    val b = MatrixGenerator.lebedevaBVector
    val eps = 1e-10
    performComputation(A, b, eps)
}

fun m1Test() {
    val A = LUDecomposition.A2
    val b = LUDecomposition.b2
    val eps = 1e-10
    performComputation(A, b, eps)
}

fun m2Test() {
    val A = LUDecomposition.A3
    val b = LUDecomposition.b3
    val eps = 1e-10
    performComputation(A, b, eps)
}

fun m3Test() {
    val A = LUDecomposition.A4
    val b = LUDecomposition.b4
    val eps = 1e-10
    performComputation(A, b, eps)
}

fun tests() {
    lebedevaMatrixTest()
    m1Test()
    m2Test()
    m3Test()
    bigTest()
}

fun main() {
    println("Вычислительный практикум")
    println("Выполнил: Ксенчик Никита")
    println("Задача 4:")
    println("ИТЕРАЦИОННЫЕ МЕТОДЫ РЕШЕНИЯ СЛАУ")
    println()

    tests()
}