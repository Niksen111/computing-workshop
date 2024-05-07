package hw6;

import kotlin.Pair;
import org.jetbrains.annotations.NotNull;
import org.la4j.Matrix;
import org.la4j.Vector;
import org.la4j.matrix.functor.MatrixAccumulator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.lang.Math.*;

public class FullEigennumberProblem {
    private FullEigennumberProblem() {}

    @NotNull
    public static Vector getDiagonal(@NotNull Matrix A) {
        var vector = Vector.zero(A.rows());
        for (int i = 0; i < A.rows(); i++) {
            vector.set(i, A.get(i, i));
        }

        return vector;
    }

    @NotNull
    public static Matrix getRotationMatrix(@NotNull Matrix A, int i, int j) {
        double fi;
        if (abs(A.get(i, i) - A.get(j, j)) < 1e-14) {
            fi = Math.PI / 4;
        } else {
            fi = Math.atan(-2 * A.get(i, j) / (A.get(i, i) - A.get(j, j)));
        }

        var cosFi = cos(fi);
        var sinFi = sin(fi);
        var result = Matrix.identity(A.rows());
        result.set(i, i, cosFi);
        result.set(j, j, cosFi);
        result.set(i, j, -sinFi);
        result.set(j, i, sinFi);

        return result;
    }

    @NotNull
    public static Pair<Vector, Integer> getEigennumbers(@NotNull Matrix matrix, double eps, ZeroingMethod method) {
        var matrixAccumulator = new MatrixAccumulator() {
            double sum = 0.0;
            @Override
            public void update(int i, int j, double value) {
                if (i != j) {
                    sum += value * value;
                }
            }

            @Override
            public double accumulate() {
                return sum;
            }
        };
        var A = matrix.copy();
        var k = 0;

        while (A.fold(matrixAccumulator) > eps) {
            var coords = method.getZeroingIndexes(A);
            var T = getRotationMatrix(A, coords.getFirst(), coords.getSecond());
            A = T.transpose().multiply(A).multiply(T);
            matrixAccumulator.sum = 0;
            ++k;
        }

        return new Pair<>(getDiagonal(A), k);
    }

    @NotNull
    public static List<Segment> getGershgorinCircles(@NotNull Matrix A) {
        List<Segment> intervals = new ArrayList<>();
        final var n = A.rows();
        for (int i = 0; i < n; i++) {
            final var row = A.getRow(i);
            row.each((ind, value) -> abs(value));
            final var radius = row.sum() - abs(A.get(i, i));
            intervals.add(new Segment(A.get(i, i) - radius, A.get(i, i) + radius));
        }

        intervals.sort(Comparator.comparingDouble(Segment::getLeft));
        List<Segment> merged = new ArrayList<>(List.of(intervals.get(0)));
        for (Segment interval : intervals) {
            var last = merged.getLast();
            if (interval.getLeft() <= last.getRight()) {
                last.setRight(max(last.getRight(), interval.getRight()));
            } else {
                merged.add(interval);
            }
        }

        return merged;
    }

    interface ZeroingMethod {
        Pair<Integer, Integer> getZeroingIndexes(Matrix A);
    }

    static final class MaxElementMethod implements ZeroingMethod {
        @Override
        public Pair<Integer, Integer> getZeroingIndexes(@NotNull Matrix A) {
            Pair<Integer, Integer> maxCoords = new Pair<>(0, 1);
            for (int i = 0; i < A.rows(); i++) {
                for (int j = 0; j < A.rows(); j++) {
                    if (i != j && abs(A.get(i, j)) > abs(A.get(maxCoords.getFirst(), maxCoords.getSecond()))) {
                        maxCoords = new Pair<>(i, j);
                    }
                }
            }

            return maxCoords;
        }
    }
}