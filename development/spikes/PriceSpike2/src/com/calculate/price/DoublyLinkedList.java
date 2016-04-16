package com.calculate.price;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements Iterable<E> {
    private int N;        // number of elements on list
    private Node head;     // sentinel before first item
    private Node tail;    // sentinel after last item

    public DoublyLinkedList() {
    }

    // linked list node helper data type
    private class Node {
        private E item;
        private Node next;
        private Node prev;
    }

    public boolean isEmpty()    { return N == 0; }
    public int size()           { return N;      }

    // add the item to the list
    public void add(E item) {
        Node x = new Node();
        x.item = item;
        if(head == null){
            head=x;
            tail=x;
            N++;
        }else {
            tail.next = x;
            x.prev = tail;
            x.next = null;
            tail = x;
            N++;
        }
    }

    public ListIterator<E> iterator()  { return new DoublyLinkedListIterator(); }

    // assumes no calls to DoublyLinkedList.add() during iteration
    private class DoublyLinkedListIterator implements ListIterator<E> {
        private Node current      = head;  // the node that is returned by next()
        private Node lastAccessed = null;      // the last node to be returned by prev() or next()
        // reset to null upon intervening remove() or add()
        private int index = 0;

        public boolean hasNext()      { return index < N; }
        public boolean hasPrevious()  { return index > 1; }
        public int previousIndex()    { return index - 1; }
        public int nextIndex()        { return index;     }

        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            lastAccessed = current;
            if (index == 0) {
                index++;
                return current.item;
            }
            current = current.next;
            E item = current.item;
            index++;
            return item;
        }

        public E previous() {
            if (!hasPrevious()) throw new NoSuchElementException();
            current = current.prev;
            index--;
            lastAccessed = current;
            return current.item;
        }

        // replace the item of the element that was last accessed by next() or previous()
        // condition: no calls to remove() or add() after last call to next() or previous()
        public void set(E item) {
            if (lastAccessed == null) throw new IllegalStateException();
            lastAccessed.item = item;
        }

        // remove the element that was last accessed by next() or previous()
        // condition: no calls to remove() or add() after last call to next() or previous()
        public void remove() {
            if (lastAccessed == null) throw new IllegalStateException();
            Node x = lastAccessed.prev;
            Node y = lastAccessed.next;
            x.next = y;
            y.prev = x;
            N--;
            if (current == lastAccessed)
                current = y;
            else
                index--;
            lastAccessed = null;
        }

        // add element to list
        public void add(E item) {
            Node x = current.prev;
            Node y = new Node();
            Node z = current;
            y.item = item;
            x.next = y;
            y.next = z;
            z.prev = y;
            y.prev = x;
            N++;
            index++;
            lastAccessed = null;
        }

    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (E item : this)
            s.append(item + " ");
        return s.toString();
    }
}

