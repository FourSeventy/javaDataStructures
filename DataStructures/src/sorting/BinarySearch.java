/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sorting;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *
 * @author Mike
 */
public class BinarySearch {
    
 
    public static int search(int[] list, int key)
    {
        return BinarySearch.search(list, key, 0, list.length -1);
    }
    
    
    private static int search(int[] list, int key, int low, int high)
    {
        //key not found terminating case
        if( high < low)
        {
            return -1;
        }
        
        //get midpoint 
        int midpoint = (high+low)/2;
              
        //key found terminating case
        if(list[midpoint] == key)
        {
            return midpoint;
        }
        
        //if key is less than midpoint recurse left
        if(key < list[midpoint])
        {
            return search(list, key, low, midpoint-1);
        }
        else //else recurse right
        {
            return search(list, key, midpoint +1, high);
        }
    }
    
    
}
