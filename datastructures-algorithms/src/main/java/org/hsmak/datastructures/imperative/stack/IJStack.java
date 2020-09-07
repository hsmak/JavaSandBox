package org.hsmak.datastructures.imperative.stack;

public interface IJStack<E> {
    int size();
    boolean isEmpty();
    void push(E e);
    E top();
    E pop();
}
