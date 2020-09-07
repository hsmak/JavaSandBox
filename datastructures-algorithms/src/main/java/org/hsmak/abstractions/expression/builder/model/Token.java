package org.hsmak.abstractions.expression.builder.model;

public class Token<R> {

    private R value;

    public Token() {
    }

    public Token(R value) {
        this.value = value;
    }

    public R getValue() {
        return value;
    }

    public void setValue(R value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token<?> token = (Token<?>) o;

        return value.equals(token.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "value=" + value +
                '}';
    }
}