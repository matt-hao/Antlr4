package model;

public class Number extends Expression {
    private int num;

    public Number(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return Integer.toString(num);
    }
}
