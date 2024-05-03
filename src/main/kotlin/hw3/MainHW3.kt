package hw3

import hw1.ConditionNums
import hw1.MatrixGenerator
import hw2.LUDecomposition
import org.la4j.LinearAlgebra
import org.la4j.LinearAlgebra.InverterFactory
import org.la4j.Matrix
import org.la4j.Vector

fun performComputation(A: Matrix, b: Vector) {
    println("Матрица A:")
    println(A)

    println("Критерии матрицы A:")
    ConditionNums.criteriaComputation(A)

    println("Правая часть уравнения (b):")
    println(b)
    println()

    println("Решение системы:")
    val solution = LinearAlgebra.SolverFactory.SMART.create(A).solve(b)
    println(solution)
    println()

    val xqr = RotationMethod.rotation(A, b)
    println("Матрица R:")
    println(xqr.R)

    println("Критерии матрицы R:")
    ConditionNums.criteriaComputation(xqr.R)

    println("Матрица Q:")
    println(xqr.Q)

    println("Q * Q^T :")
    println(xqr.Q.multiply(InverterFactory.SMART.create(xqr.Q).inverse()))

    println("Критерии матрицы Q:")
    ConditionNums.criteriaComputation(xqr.Q)

    println(xqr.Q.multiply(xqr.R))

    println("Решение системы с помощью QR разложения:")
    println(xqr.x)
    println()


    if (solution.equals(xqr.x)) {
        println("Решения совпадают!")
    }
    else {
        println("Решения не совпадают :(")
    }
    println("##############################################################################")
    println()
}

fun lebedevaMatrixTest() {
    val A = MatrixGenerator.lebedevaAMatrix
    val b = MatrixGenerator.lebedevaBVector
    performComputation(A, b)
}

fun m1Test() {
    val A = LUDecomposition.A1
    val b = LUDecomposition.b1
    performComputation(A, b)
}

fun m2Test() {
    val A = LUDecomposition.A2
    val b = LUDecomposition.b2
    performComputation(A, b)
}

fun m3Test() {
    val A = LUDecomposition.A3
    val b = LUDecomposition.b3
    performComputation(A, b)
}

fun m4Test() {
    val A = LUDecomposition.A4
    val b = LUDecomposition.b4
    performComputation(A, b)
}

fun tests() {
    lebedevaMatrixTest()
    m1Test()
    m2Test()
    m3Test()
    m4Test()
}

fun main() {
    println("Вычислительный практикум")
    println("Выполнил: Ксенчик Никита")
    println("Задача 3:")
    println("ТОЧНЫЕ МЕТОДЫ РЕШЕНИЯ СЛАУ. QR-РАЗЛОЖЕНИЯ. МЕТОД ОТРАЖЕНИЯ")
    println()

    tests()
}