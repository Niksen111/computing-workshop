package hw6;

public class Segment {
    private double left;

    private double right;

    public Segment(double left, double right) {
        this.left = left;
        this.right = right;
    }

    public double getLeft() {
        return left;
    }

    public void setLeft(double left) {
        this.left = left;
    }

    public double getRight() {
        return right;
    }

    public void setRight(double right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "[" + left + "; " + right + "]";
    }
}