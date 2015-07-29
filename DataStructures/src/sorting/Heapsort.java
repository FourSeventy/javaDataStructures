
package sorting;

import java.util.PriorityQueue;

/**
 *
 * @author Mike
 */
public class Heapsort {
    
    
    
    public static void sort(int[] list)
    {
        
        //make a heap out of the list
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for(int i: list)
        {
            heap.add(i);
        }
        
        //until the heap is empty remove the smallest item 
        for( int i = 0; i < list.length; i++)
        {
            list[i] = heap.poll();
        }
    }
}
