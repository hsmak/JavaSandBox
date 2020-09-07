package org.hsmak.datastructures.imperative.linkedlist;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class JSinglyLinkedListOO<E> {

    private Node head;
    private Node tail;

    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        if (isEmpty())
            return null;

        return head.e;
    }

    class Node {
        E e;
        Node next;

        Node(E e) {// always specify what NextNode will be for this Node
            this.e = e;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("e", e)
//                    .append("next", next)
                    .toString();
        }
    }

    public void addFirst(E e) {
        Node newNode = new Node(e);
        if (head == null) {
            head = newNode;
            tail = head;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public void addLast(E e) {
        Node newNode = new Node(e);
        if (head == null) {
            head = newNode;
            tail = head;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public E removeFirst() {
        if (head == null)
            return null;

        Node n = head;
        head = head.next;

        if (head == null)//If all there was is one element
            tail = null;

        size--;

        return n.e;
    }

    /**
     * inefficient because we have to traverse the whole list in order to get hold of the element before the last
     *
     * @return
     */
    public E removeLast() {
        if (head == null)
            return null;

        if (head.next == null) { //If all there is one element
            E e = head.e;
            head = null;
            tail = null;
            size--;
            return e;

        }

        Node tempN = head;
        while (tempN.next.next != null) {
            tempN = tempN.next;
        }

        E e = tail.e;
        tail = tempN;

        size--;

        return e;

    }

    public E get(E e) {

        for (Node n = head; n != null; n = n.next) {
            if (n.e.equals(e))
                return n.e;
        }
        return null;//In FP this should be Option[Nothing]
    }

    public E remove(E e) {
        if (head == null)
            return null;

        for (Node n = head; n != null; n = n.next) {
            if (n.e.equals(e) && n == head) {//if matches head
                return removeFirst();//it will decrement size
            } else {
                Node peekedN = peek(n);
                if (peekedN == null)//not match
                    return null;
                else if (peekedN.e.equals(e)) {
                    if (n.next == tail) {// if matches tail
                        E tempE = tail.e;
                        tail = n;
                        size--;
                        return tempE;
                    } else {//if in the middle
                        size--;
                        n.next = n.next.next;
                        return peekedN.e;
                    }
                }

            }
        }
        return null;
    }

    private Node peek(Node n) {
        if (n == null)
            return null;

        return n.next;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("head", head.e)
                .append("tail", tail.e)
                .append("size", size)
                .toString();
    }

    public static void main(String[] args) {
        JSinglyLinkedListOO<String> jll = new JSinglyLinkedListOO<>();
        jll.addFirst("A");
        System.out.println(jll.removeLast());
        jll.addFirst("B");
        jll.addFirst("Z");
        jll.addFirst("E");

        System.out.println(jll.get("Z"));

        JSinglyLinkedListOO<String> jll2 = new JSinglyLinkedListOO<>();
        jll2.addLast("A");
        jll2.addLast("B");
        jll2.addLast("Z");
        jll2.addLast("E");

        System.out.println(jll2);
        System.out.println(jll2.remove("B"));
        System.out.println(jll2);

    }
}


