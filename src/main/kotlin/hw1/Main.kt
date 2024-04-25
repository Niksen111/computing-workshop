package hw1

import org.la4j.Matrix
import kotlin.math.abs

fun printDeviation(x: Matrix, y: Matrix) {
    val z = x.subtract(y)
    z.each { _, _, value -> abs(value) }
    println("Погрешность решения:")
    println(z)
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