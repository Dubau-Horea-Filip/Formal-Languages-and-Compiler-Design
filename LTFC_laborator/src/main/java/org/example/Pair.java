package org.example;

public class Pair <K, T> {
    private K firstValue;
    private T secondValue;

    @Override
    public String toString() {
        return  "\n" +
                "token=" + firstValue +
                ", SymboltablePos=" + secondValue
               ;
    }

    public Pair(K firstValue, T secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public K getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(K firstValue) {
        this.firstValue = firstValue;
    }

    public T getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(T secondValue) {
        this.secondValue = secondValue;
    }
}
