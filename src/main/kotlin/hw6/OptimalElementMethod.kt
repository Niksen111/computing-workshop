package hw6

import org.la4j.Matrix
import org.la4j.Vector
import kotlin.math.abs

class OptimalElementMethod : FullEigennumberProblem.ZeroingMethod {
    override fun getZeroingIndexes(A: Matrix): Pair<Int, Int> {
        val ASqr = A.transform { _, _, value -> value * value }
        val diag = FullEigennumberProblem.getDiagonal(ASqr)
        var sumRows = Vector.zero(ASqr.rows())
        for (i in 0..<ASqr.rows()) {
            for (elem in ASqr.getRow(i)) {
                sumRows[i] += elem
            }
        }

        sumRows = sumRows.subtract(diag)
        var ind = 0
        for (i in 1..<sumRows.length()) {
            if (sumRows[i] > sumRows[ind]) {
                ind = i
            }
        }
        val maxI = ind
        val iRow = A.getRow(maxI).transform { _, value -> abs(value) }
        iRow[maxI] = -1.0
        ind = 0
        for (i in 1..<iRow.length()) {
            if (iRow[i] > iRow[ind]) {
                ind = i
            }
        }
        val maxJ = ind

        return Pair(maxI, maxJ)
    }
}