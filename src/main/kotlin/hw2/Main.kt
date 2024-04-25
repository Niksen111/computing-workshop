package hw2

import hw1.MatrixGenerator

fun tests() {
    val kek = MatrixGenerator.hilbertMatrix(2)
    val L = LUDecomposition.sqrtMethod(kek)
    println(L)
    println(L.multiply(L.transpose()))
}

fun main() {
    println("Вычислительный практикум")
    println("Выполнил: Ксенчик Никита")
    println("Задача 2:")
    println("ТОЧНЫЕ МЕТОДЫ РЕШЕНИЯ СЛАУ, LU-РАЗЛОЖЕНИЯ")
    println()

    tests()
}