package org.hsmak.datastructures.imperative.stack;

import org.hsmak.datastructures.imperative.linkedlist.JSinglyLinkedListOO;

/**
 * Adapter Pattern
 * @param <E>
 */
public class JLinkedStack<E> implements IJStack<E> {

    private JSinglyLinkedListOO<E> data = new JSinglyLinkedListOO<>();

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void push(E e) {
        data.addFirst(e);

    }

    @Override
    public E top() {
        return data.first();
    }

    @Override
    public E pop() {
        return data.removeFirst();
    }

    public static void main(String... args){
        JLinkedStack<String> jls = new JLinkedStack<>();
        jls.push("A");
        jls.push("B");
        jls.push("C");
        System.out.println(jls.pop());

    }
}
