package org.hsmak.datastructures.stack;

public class JArrayStack<E> implements IJStack<E>{

    public static final int capacity = 1000;
    private E[] data;
    private int t = -1;

    public JArrayStack(){
        this(capacity);
    }

    public JArrayStack(int capacity) {
        data = (E[])new Object[capacity];//ToDo - notice the downcast
    }

    @Override
    public int size() {
        return t + 1;
    }

    @Override
    public boolean isEmpty() {
        return t == -1;
    }

    @Override
    public void push(E e) {
        if(size() == data.length)
            throw new IllegalStateException("Stack is full");

        data[++t] = e;
    }

    @Override
    public E top() {
        if(isEmpty())
            return null;

        return data[t];
    }

    @Override
    public E pop() {
        if(isEmpty())
            return null;

        E elem = data[t];
        data[t--] = null;// deference to help garbage collection, then decrement t

        return elem;
    }

    public static void main(String... args){
        JArrayStack<String> jas = new JArrayStack<>();
        jas.push("A");
        jas.push("B");
        jas.push("C");
        System.out.println(jas.size());
        System.out.println(jas.top());
        System.out.println(jas.pop());
        System.out.println(jas.size());
    }

}
