package hw5

import dnl.utils.text.table.TextTable
import hw1.ConditionNums
import hw1.MatrixGenerator
import org.la4j.LinearAlgebra
import org.la4j.Matrix
import org.la4j.Vector
import org.la4j.matrix.SparseMatrix
import java.text.DecimalFormat
import kotlin.random.Random
import kotlin.random.asJavaRandom

fun Matrix.getDiagonal(): Vector {
    val vector = Vector.zero(this.rows())
    for (i in 0..<this.rows()) {
        vector[i] = this[i, i]
    }

    return vector
}

fun performComputation(A: Matrix, x0: Vector, y0: Vector) {
    println("Матрица A:")
    println(A)

    println("Критерии матрицы A:")
    ConditionNums.criteriaComputation(A)

    println("Вектор x0:")
    println(x0)
    println()

    println("Вектор y0:")
    println(y0)
    println()

    val vd = LinearAlgebra.EIGEN.create(A).decompose()

    println("Собственные числа системы:")
    val eigennumbers = vd[1].getDiagonal()
    println(eigennumbers.toCSV(DecimalFormat("0.000000000000000")))
    println()

    println("Собственные векторы системы:")
    println(vd[0])
    println()

    val resultsPM = mutableListOf("Степенной")
    val valuesPM = mutableListOf("Степенной")
    val resultsSPM = mutableListOf("Скалярных произведений")
    val valuesSPM = mutableListOf("Скалярных произведений")

    var eps = 0.1
    for (i in 1..6) {
        val resultPM = PartialEigennumberProblem.powerMethod(A, x0, eps)
        resultsPM.add(resultPM.third.toString())
        valuesPM.add(resultPM.first.toString())
        val resultSPM = PartialEigennumberProblem.scalarProductMethod(A, x0, y0, eps)
        resultsSPM.add(resultSPM.third.toString())
        valuesSPM.add(resultSPM.first.toString())
        eps /= 10
    }

    val headers = arrayOf("Метод", "1e-1", "1e-2", "1e-3", "1e-4", "1e-5", "1e-6")
    val data1 = arrayOf(resultsPM.toTypedArray(), resultsSPM.toTypedArray())
    val table1 = TextTable(headers, data1)
    println("Количество шагов метода:")
    table1.printTable()
    println()

    val data2 = arrayOf(valuesPM.toTypedArray(), valuesSPM.toTypedArray())
    val table2 = TextTable(headers, data2)
    println("Найденные значения:")
    table2.printTable()
    println()
}

fun lebedevaMatrixTest() {
    val A = MatrixGenerator.lebedevaAMatrix
    val x0 = MatrixGenerator.lebedevaBVector
    val y0 = Vector.random(3, Random.asJavaRandom()).multiply(10.0)
    performComputation(A, x0, y0)
}

fun bigMatrixTest() {
    val A = Matrix.diagonal(150, 100.0)
        .add(SparseMatrix.random(150, 150 , 0.282, Random.asJavaRandom()))
    val x0 = Vector.random(150, Random.asJavaRandom())
    val y0 = Vector.random(150, Random.asJavaRandom())
    performComputation(A, x0, y0)
}

fun badMatrixTest() {
    val A = Matrix.from1DArray(3, 3, doubleArrayOf(0.5, 2.0, 1.0, -0.5, 2.5, 0.5, 0.0, 0.0, 0.5))
    val x0 = Vector.random(3, Random.asJavaRandom())
    val y0 = Vector.random(3, Random.asJavaRandom())
    performComputation(A, x0, y0)
}

fun tests() {
    lebedevaMatrixTest()
    badMatrixTest()
//    bigMatrixTest()
}

fun main() {
    println("Вычислительный практикум")
    println("Выполнил: Ксенчик Никита")
    println("Задача 5:")
    println("ЧАСТИЧНАЯ ПРОБЛЕМА СОБСТВЕННЫХ ЧИСЕЛ")
    println()

    tests()
}