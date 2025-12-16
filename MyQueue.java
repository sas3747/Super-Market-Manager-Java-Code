package edu.cw1.supermarket; 

import java.util.ArrayList; 
import java.util.List; 

/**
 * A minimal array-based circular queue with fixed capacity.
 * We implement from scratch to satisfy the "custom DS" requirement (I2).
 * 
 * @param <T>
 */
public class MyQueue<T> 
{ 
    private final Object[] arr; 
    private int head; // index of the oldest element
    private int tail; // index to insert the next element
    private int size; // current number of elements
    private final int cap; // fixed capacity

    public MyQueue(int capacity) 
    { // constructor takes fixed capacity
        this.cap = capacity; // set capacity
        this.arr = new Object[capacity]; // allocate array
        this.head = 0; // start head at 0
        this.tail = 0; // start tail at 0
        this.size = 0; // initially empty
    } 

    public int size()
    { 
        return size; 
    } // number of items
    public boolean isEmpty()
    { 
        return size == 0; 
    } // emptiness check
    public boolean isFull() 
    { 
        return size == cap;
    } // full check

    /**
     * Adds an element to the queue. If full, drops the oldest (to keep "last 4" semantics).
     * @param value
     */
    public void offer(T value)
    { // enqueue with overwrite-oldest if full
        if (isFull())
        { // if queue full
            head = (head + 1) % cap; // advance head to drop oldest
            size--; // decrement size because we're overwriting
        }
        arr[tail] = value; // place new element at tail
        tail = (tail + 1) % cap; // move tail circularly
        size++; // increment size
    } 

    /**
     * Removes and returns the oldest element, or null if empty.
     * @return 
     */
    @SuppressWarnings("unchecked") 
    public T poll() 
    { // dequeue
        if (isEmpty()) return null; // nothing to remove
        T value = (T) arr[head]; // read oldest
        arr[head] = null; // help GC
        head = (head + 1) % cap; // advance head
        size--; // decrease size
        return value; // return removed value
    } 

    /**
     * Returns the oldest element without removing, or null if empty.
     * @return 
     */
    @SuppressWarnings("unchecked")
    public T peek()
    { // look oldest
        if (isEmpty()) return null; // no item
        return (T) arr[head]; // return oldest
    } 

    /**
     * Returns a snapshot list in FIFO order (oldest -> newest).
     * @return 
     */
    @SuppressWarnings("unchecked")
    public List<T> toList()
    { // snapshot for sorting/display
        List<T> list = new ArrayList<>(size); // allocate list
        for (int i = 0; i < size; i++) { // iterate size elements
            int idx = (head + i) % cap; // compute circular index
            list.add((T) arr[idx]); // add element
        }
        return list;
    } 
} 
