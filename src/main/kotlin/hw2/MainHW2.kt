package hw2

import dnl.utils.text.table.TextTable
import hw1.ConditionNums
import org.la4j.Matrix
import org.la4j.Vector
import org.la4j.linear.GaussianSolver

fun criteriaComputation(A: Matrix) {
    println("Качественные критерии:")
    val header = arrayOf("Варьирование", "Спектральный", "Объемный", "Угловой")
    val data = arrayOf(
        arrayOf(0.0, ConditionNums.spectralCr(A), ConditionNums.ortegaCr(A), ConditionNums.angularCr(A)),
    )
    val table = TextTable(header, data)
    table.printTable()
    println()
}

fun performKekLol(A: Matrix, b: Vector) {
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

    println("Решение системы с использование метода Холецкого:")
    val XL = GaussianSolver(L).solve(b)
    println(XL)
    println()

    if (XA.equals(XL)) {
        println("Матрицы совпадают!")
    }
    else {
        println("Матрицы не совпадают :(")
    }
    println()
    println()
}

fun m1Test() {
    val A = LUDecomposition.A1
    val b = LUDecomposition.b1
    performKekLol(A, b)
}

fun m2Test() {
    val A = LUDecomposition.A2
    val b = LUDecomposition.b2
    performKekLol(A, b)
}

fun m3Test() {
    val A = LUDecomposition.A3
    val b = LUDecomposition.b3
    performKekLol(A, b)
}

fun m4Test() {
    val A = LUDecomposition.A4
    val b = LUDecomposition.b4
    performKekLol(A, b)
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