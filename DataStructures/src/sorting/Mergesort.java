
package sorting;

/**
 *
 * @author Mike
 */
public class Mergesort {
    
    private Mergesort()
    {
        
    }
    
    /**
     * Sort a list from low to high. Time complexity is Average: O(n log n) Worst: O(n log n) Space: O(n)
     * @param list 
     */
    public static void sort(int[] list)
    {
        //build auxilliary array
        int[] aux = new int[list.length];
        
        //call recursive sort
        Mergesort.sort(list, aux, 0, list.length-1);
        
    }
    
    private static void sort(int[] list, int[] aux, int low, int high)
    {
        //terminating condition
        if(high <= low)
        {
            return;
        }
        
        //find middle
        int mid = (low + high) /2;
        
        //call sort on left half
        Mergesort.sort(list,aux,low, mid);
        
        // call sort on right half
        Mergesort.sort(list,aux,mid+1, high);
        
        //merge halves
        Mergesort.merge(list, aux, low, mid, high);
    }
    
    
    /**
     * Merge two lists together. The two lists are list[low ... mid] and list[mid+1 ... high]. Aux[] is used as
     * auxilliary storage during the merge
     * @param list
     * @param aux
     * @param low
     * @param mid
     * @param high 
     */
    private static void merge(int[] list, int[] aux, int low, int mid, int high)
    {
        
        //copy whole list to aux
        for(int i = low; i <= high; i++)
        {
            aux[i] = list[i];        
        }
        
        // merge back to list
        int topIndexList1 = low;
        int topIndexList2 = mid+1;
        for (int ii = low; ii <= high; ii++) 
        {
            if(topIndexList1 > mid) //some type of edge case
            {
                list[ii] = aux[topIndexList2];
                topIndexList2++;
            } 
            else if(topIndexList2 > high) //some type of edge case
            {
                list[ii] = aux[topIndexList1];  
                topIndexList1++;
            }
            
            //find smaller of the two items aux[topList2] and aux[topList1] and add that item back to list[]
            else if(aux[topIndexList2] < aux[topIndexList1]) 
            {
                list[ii] = aux[topIndexList2];  
                topIndexList2++;
            }
            else
            {
                list[ii] = aux[topIndexList1];
                topIndexList1++;
            }
        }
    }
}
