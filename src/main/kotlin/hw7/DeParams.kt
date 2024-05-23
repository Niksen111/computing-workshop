package hw7

data class DeParams(
    var u: (Double) -> Double,
    var p: (Double) -> Double,
    var q: (Double) -> Double,
    var r: (Double) -> Double,
    var f: (Double) -> Double,
    var a: Double,
    var b: Double,
    var alpha: Double,
    var alpha1: Double,
    var alpha2: Double,
    var beta: Double,
    var beta1: Double,
    var beta2: Double
)
