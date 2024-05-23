package hw7

import org.la4j.Vector

data class Answer(
    val discretization: Vector,
    val approx: Vector,
    val ns: List<Int>,
    val norms: List<Double>
)
