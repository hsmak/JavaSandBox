package org.hsmak.datastructures.imperative.queue;

public interface IJQueue<E> {
    int size();
    boolean isEmpty();
    E first();
    void enqueue(E e);
    E dequeue();
}
