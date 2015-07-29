
package sorting;

/**
 *
 * @author Mike
 */
public class Quicksort {
    
    private Quicksort()
    {
        
    }
    
    
    public static void sort(int[] list)
    {
        Quicksort.sort(list, 0, list.length-1);
    }
    
    private static void sort(int[] list, int low, int high)
    {
        //terminating case
        if(high <= low)
        {
            return;
        }
        
        //get pivot point and sort partitions
        int pivot = Quicksort.partition(list,low,high);
        
        //recurse into each partition
        Quicksort.sort(list, low, pivot-1);
        Quicksort.sort(list,pivot+1,high);
    }
    
    
    private static int partition(int[] list, int low, int high)
    {
        //choose pivot
        int pivotIndex = low;        
        int pivotValue = list[pivotIndex];
        
        //swap pivot to top of list
        Quicksort.swap(list, pivotIndex, high);
        int storeIndex = low;
        
        // Compare remaining array elements against pivotValue
        for(int i = low; i < high; i++)
        {
            if(list[i] < pivotValue)
            {
                Quicksort.swap(list, i, storeIndex);
                storeIndex++;
            }
        }
        
        // Move pivot to its final place
        Quicksort.swap( list,storeIndex, high);  
        return storeIndex;       
      
    }
    
    
    /**
     * Swaps indicies a and b, in array list.
     * @param list
     * @param a
     * @param b 
     */
    private static void swap(int[] list, int a, int b)
    {
        int temp = list[a];
        list[a] = list[b];
        list[b] = temp;
    }
    
    
}
