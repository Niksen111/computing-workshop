package hw6

import dnl.utils.text.table.TextTable
import hw1.MatrixGenerator
import hw5.getDiagonal
import org.la4j.LinearAlgebra
import org.la4j.Matrix

fun performComputations(A: Matrix) {
    println("Матрица A:")
    println(A)

    val vd = LinearAlgebra.EIGEN.create(A).decompose()

    println("Собственные числа матрицы:")
    val eigennumbers = vd[1].getDiagonal()
    println(eigennumbers.sorted().toString())
    println()

    println("Круги Гершгорина:")
    val circles = FullEigennumberProblem.getGershgorinCircles(A)
    println(circles)

    println("Поиск собственных чисел с использованием метода максимального элемента:")
    var values = mutableListOf<String>()
    var steps = mutableListOf("Кол-во шагов")
    var eps = 1.0
    for (i in 1..6) {
        eps /= 10
        val result = FullEigennumberProblem.getEigennumbers(A, eps, FullEigennumberProblem.MaxElementMethod())
        values.add(result.first.sorted().toString() + "\n")
        steps.add(result.second.toString())
    }

    val header = listOf("Точность", "1e-1", "1e-2", "1e-3", "1e-4", "1e-5", "1e-6")
    var table = TextTable(header.toTypedArray(), arrayOf(steps.toTypedArray()))
    table.printTable()
    println()

    println("Найденные собственные числа:")
    println(values)
    println()

    println("Поиск собственных чисел с использованием метода оптимального элемента:")
    values = mutableListOf()
    steps = mutableListOf("Кол-во шагов")
    eps = 1.0
    for (i in 1..6) {
        eps /= 10
        val result = FullEigennumberProblem.getEigennumbers(A, eps, OptimalElementMethod())
        values.add(result.first.sorted().toString() + "\n")
        steps.add(result.second.toString())
    }

    table = TextTable(header.toTypedArray(), arrayOf(steps.toTypedArray()))
    table.printTable()
    println()

    println("Найденные собственные числа:")
    println(values)
    println()
}

fun hilbertMatrixTest() {
    val A = MatrixGenerator.hilbertMatrix(5)
    performComputations(A)
}

fun tests() {
    hilbertMatrixTest()
}

fun main() {
    println("Вычислительный практикум")
    println("Выполнил: Ксенчик Никита")
    println("Задача 6:")
    println("ПОЛНАЯ ПРОБЛЕМА СОБСТВЕННЫХ ЧИСЕЛ")
    println()

    tests()
}