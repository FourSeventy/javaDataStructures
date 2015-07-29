/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datastructures;

import datastructures.LinkedList.LinkedListNode;

/**
 *
 * @author Mike
 */
public class Stack<E> {
    
    private LinkedList<E> list = new LinkedList<>();
    
    public Stack()
    {
        
    }
    
    /**
     * Pushes an item to the top of the stack. Returns reference to self for chaining.
     * @param item
     * @return 
     */
    public Stack push(E item)
    {
        this.list.addFirst(item);
        return this;
    }
    
    
    /**
     * Peeks at the first item on the stack;
     * @return 
     */
    public E peek()
    {
        return this.list.getFirst();
    }
    
    /**
     * Pops the top item off the stack.
     * @return the top item off the stack.
     */
    public E pop()
    {
        E data = this.list.getFirst();
        this.list.deleteFirst();
        
        return data;
        
    }
    
    /**
     * Returns a string representation of the stack.
     * @return 
     */
    public String toString()
    {
        return this.list.toString();
    }
    
}
