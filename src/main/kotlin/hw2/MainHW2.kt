package hw2

import hw1.ConditionNums.Companion.criteriaComputation
import org.la4j.Matrix
import org.la4j.Vector
import org.la4j.linear.GaussianSolver

fun performComputation(A: Matrix, b: Vector) {
    println("Матрица A:")
    println(A)

    criteriaComputation(A)

    println("Матрица L:")
    val L = LUDecomposition.sqrtMethod(A)
    println(L)

    println("L*L^T :")
    val LLT = L.multiply(L.transpose())
    println(LLT)

    if (A.equals(LLT)) {
        println("Матрицы совпадают!")
        println()
    }
    else {
        println("Матрицы не совпадают :(")
        println()
        return
    }

    println("Вектор b:")
    println(b)
    println()

    println("Решение оригинальной системы:")
    val XA = GaussianSolver(A).solve(b)
    println(XA)
    println()

    println("Решение системы L*y=b:")
    val YL = GaussianSolver(L).solve(b)
    println(YL)
    println()

    println("Решение системы L^T*x=b:")
    val XL = GaussianSolver(L.transpose()).solve(YL)
    println(XL)
    println()

    if (XA.equals(XL)) {
        println("Решения совпадают!")
    }
    else {
        println("Решения не совпадают :(")
    }
    println()
    println()
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
    m1Test()
    m2Test()
    m3Test()
    m4Test()
}

fun main() {
    println("Вычислительный практикум")
    println("Выполнил: Ксенчик Никита")
    println("Задача 2:")
    println("ТОЧНЫЕ МЕТОДЫ РЕШЕНИЯ СЛАУ, LU-РАЗЛОЖЕНИЯ")
    println("Метод квадратного корня/метод Холецкого")
    println()

    tests()
}