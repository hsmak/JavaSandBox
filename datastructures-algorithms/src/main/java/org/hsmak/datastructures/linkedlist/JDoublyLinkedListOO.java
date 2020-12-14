package org.hsmak.datastructures.linkedlist;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class JDoublyLinkedListOO<E> {

    private Node head;
    private Node tail;

    private int size;

    class Node {
        E e;
        Node next;
        Node prev;

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
            head = newNode;//next = null & prev = null
            tail = head;
        } else {
            newNode.next = head;
            head.prev = newNode;
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
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public E removeFirst() {
        if (head == null)
            return null;

        Node n = head;
        size--;

        if (head.next == null) {//case: one element
            head = null;
            tail = null;
            return n.e;
        }

        head = head.next;
        head.prev = null;


        return n.e;
    }

    /**
     * More efficient than SinglyLinkedList
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

        E e = tail.e;
        tail = tail.prev;
        tail.next = null;

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

    /**
     * ToDo
     *
     * @param e
     * @return
     */
    public E remove(E e) {
        if (head == null)
            return null;

        for (Node n = head; n != null; n = n.next) {
            if (n.e.equals(e)) {
                if (n == head) {
                    head = head.next;
                    head.prev = null;

                    size--;

                    return n.e;
                } else if (n == tail) {
                    tail = tail.prev;
                    tail.next = null;

                    size--;

                    return n.e;
                } else { // case; in the middle
                    n.next.prev = n.prev;
                    n.prev.next = n.next;
                    n.next = null;
                    n.prev = null;

                    size--;

                    return n.e;
                }

            }
        }
        return null;
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
        JDoublyLinkedListOO<String> jdll = new JDoublyLinkedListOO<>();
        jdll.addLast("A");
        jdll.addLast("B");
        jdll.addLast("Z");
        jdll.addLast("E");
        System.out.println(jdll.remove("B"));
        System.out.println();

    }
}


