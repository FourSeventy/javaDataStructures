/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datastructures;


/**
 *
 * @author Mike
 * Implementation of a singley linked list.
 */
public class LinkedList<E> 
{
    
    //head of the list
    private LinkedListNode<E> head;
    private LinkedListNode<E> tail;

    
    public LinkedList()
    {
        
    }
    
    
    public void addFirst(E data)
    {
        //make node pointing to old head
        LinkedListNode newNode = new LinkedListNode(data, head);
        
        //point head to new node
        head = newNode;
        
        //set tail
        if(tail == null)
        {
            tail = newNode;
        }
    }
    
    public void addLast(E data)
    {
        //make new node pointing to null
        LinkedListNode newNode = new LinkedListNode(data, null);
        
        //point old tail to new node
        if(tail != null)
        {
            tail.setNext(newNode);
        }
        
        //set new tail to be new node
        tail = newNode;
             
        //set head if its null
        if(head == null)
        {
            head = newNode;
        }
    }
    
    
    public E getFirst()
    {
        if(head == null)
        {
            return null;
        }
        
        return head.getData();
    }
    
    public E getLast()
    {
        if(tail == null)
        {
            return null;
        }
        
        return tail.getData();
    }
    
    public void deleteFirst()
    {
        if(this.head == null)
            return;
        
        LinkedListNode<E> nextNode = this.head.getNext();
        this.head = nextNode;
    }
    
    
    public LinkedListNode<E> search(E data)
    {
        return recursiveSearch(head, data);
    }
    
    private LinkedListNode<E> recursiveSearch(LinkedListNode<E> head, E data)
    {
        if(head == null)
        {
            return null;
        }
        
        if(head.getData().equals(data))
        {
            return head;
        }
        
        return recursiveSearch(head.getNext(), data);
    }
    
    public String toString()
    {
        return recursiveToString( head);
    }
    
    private String recursiveToString(LinkedListNode<E> head)
    {
        if(head == null)
        {
            return "";
        }
        
        return head.data.toString() + " " + recursiveToString(head.getNext());
    }
    
    
    public LinkedListNode<E> getHead()
    {
        return this.head;
    }
    
    public void setHead(LinkedListNode<E> head)
    {
        this.head = head;
    }
    

    
    
    
    
    
    
    public static class LinkedListNode<E>
    {
        //data
        private E data;
        
        //link to next node
        private LinkedListNode next;
        
        
        public LinkedListNode(E data, LinkedListNode next)
        {
            this.data = data;
            this.next = next;
        }
        
        
        public E getData()
        {
            return this.data;
        }
        
        public LinkedListNode getNext()
        {
            return this.next;
        }
        
        public void setNext( LinkedListNode node)
        {
            this.next = node;
        }
        

    }
}





