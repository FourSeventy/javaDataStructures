package datastructures;

import datastructures.BinarySearchTree.TreeNode;
import datastructures.BinarySearchTree.TreeNodeVisitor;
import datastructures.LinkedList.LinkedListNode;
import graphs.Graph;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import sorting.BinarySearch;
import sorting.Heapsort;
import sorting.Mergesort;
import sorting.Quicksort;
import java.util.*;


/**
 *
 * @author Mike
 */
public class DataStructures {

    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {       
        
        
        
        
            
        //linked list
        java.util.LinkedList linkedList = new java.util.LinkedList();
        linkedList.add("a");
        linkedList.poll();
        
        
        //hashmap
        java.util.HashMap hashMap = new java.util.HashMap();      
        hashMap.put("Key", "Value");
        hashMap.get("Key");
        hashMap.containsKey("a");
        
        
        //hashset
        java.util.HashSet hashSet = new java.util.HashSet();
          

        
        //string
        String test = "test";          
        test.charAt(1);
        test.toCharArray();
         
    }
    
    public static void compute_palindromes(String[] words) 
    {
        
        
        for(String palindromeCandidate: words)
        {
            
            //check for empty edge cases
            if( palindromeCandidate == null || palindromeCandidate.length() == 0)
            {
                System.out.println("-1");
            }
            
            //parse incoming string into arraylist of chars so its easier to work with
            ArrayList<Character> listOfCharacters = new ArrayList<>();
            for(int i = 0; i < palindromeCandidate.length(); i++ )
            {
                listOfCharacters.add(palindromeCandidate.charAt(i));
            }
            
            String palindromeStringLeft = "";
            String palindromeStringRight = "";
            
            //pull out pairs of letters
            for(int i = 0; i < listOfCharacters.size(); i++)
            {
                //pull first letter
                Character firstLetter = listOfCharacters.remove(i);
            
                //get matching letter index
                int matchingLetterIndex = listOfCharacters.indexOf(firstLetter);
                
                if(matchingLetterIndex != -1)
                {
                    Character matchingLetter = listOfCharacters.remove(matchingLetterIndex);
                    
                    palindromeStringLeft = firstLetter +palindromeStringLeft;
                    palindromeStringRight = palindromeStringRight + matchingLetter;
                    
                }
                else
                {
                    //add back removed letter
                    listOfCharacters.add(firstLetter);
                }
            }
            
            //all pairs should be removed at this point
            if(listOfCharacters.size() > 1)
            {
                System.out.println("-1");
            }
            else
            {
                if(listOfCharacters.size() == 1)
                {
                  String finalPalindrome =  palindromeStringLeft + listOfCharacters.get(0) + palindromeStringRight;
                  
                  System.out.println(finalPalindrome);
                }
            }
           
        }
    }
    
    public static boolean removePair(ArrayList<Character> l)
    {
        for(int i = 0; i < l.size(); i ++)
        {
            for(int j = i+1; j < l.size(); j++)
            {
                if(l.get(i).equals(l.get(j)))
                {
                    Character a = l.get(i);
                    Character b = l.get(j);
                    l.remove(a);
                    l.remove(b);
                    return true;
                }
            }

        }
        
        return false;
    }
    
   public static LinkedListNode getMiddleOfLinkedList(LinkedList list)
   {

        LinkedListNode slowPointer, fastPointer;

        slowPointer = list.getHead();
        fastPointer = list.getHead();

       //while fast pointer can move two nodes ahead
       while(fastPointer.getNext() != null && fastPointer.getNext().getNext() != null)
       {
             //move the slow pointer once
            slowPointer = slowPointer.getNext();

             //move the fast pointer twice
            fastPointer = fastPointer.getNext().getNext();

        }

       //slow pointer now points to our middle
       return slowPointer;
   
   }
    
    public static int fib(int n)
    {
        if(n == 0)
        {
            return 0;
        }
        
        if(n == 1)
        {
            return 1;
        }
        
        return fib(n-2) + fib(n-1);
          
    }
    
    public static void readFileWithBufferedReader()
    {
        Path p = Paths.get("X:\\words_en.txt");       
        try(BufferedReader reader = Files.newBufferedReader(p);)
        {
            
            for(String line = reader.readLine(); line != null; line = reader.readLine())
            {
                System.out.println(line);
            }
        }
        catch(Exception e)
        {
            
        }
    }
    
    public static void readFileWithScanner()
    {
        Path p = Paths.get("X:\\words_en.txt"); 
        
        try(Scanner scanner = new Scanner(p);)
        {
            while(scanner.hasNext())
            {
                String line = scanner.nextLine();
                System.out.println(line);
            }
        }
        catch(Exception e)
        {
            
        }
      
    }
    
    public static void printWordsWithPrefix()
    {
        //read in dictionary to trie
        Trie trie = new Trie();
        
        Path p = Paths.get("X:\\words_en.txt");       
        try(Scanner scanner = new Scanner(p);)
        {
            while(scanner.hasNext())
            {
                String line = scanner.nextLine();
                trie.add(line);
                             
            }
        }
        catch(Exception e)
        {
            
        }
        
        //listen for System.in input and print out if  it is a key
        Scanner scanner = new Scanner(System.in);
        System.out.println("Awaiting Input: ");
        while(scanner.hasNext())
        {           
            String input = scanner.next();
            
            System.out.println(trie.keysWithPrefix(input));
        }

    }
    
    public static void readInput()
    {
        System.out.println("Type something: ");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        System.out.println(input);
    }
    
    public static String reverseString(String input)
    {
        char[] charArray = input.toCharArray();
        
        //create second char array to hold reversed string
        char[] reversedArray = new char[charArray.length];
                
        int lastIndex = charArray.length -1;
        
        //iterate over char array and insert into reversed array
        for( int i = 0; i <= lastIndex; i++)
        {
            reversedArray[lastIndex - i] = charArray[i];
        }
        
        //create string from reversed char array
        String reversedString = String.valueOf(reversedArray);
        
        return reversedString;
    }
    
    
    public static String reverseString2(String input)
    {
        String reversedString = "";       
        
        for( int i = input.length() -1; i >= 0; i--)
        {
            reversedString += input.charAt(i);
        }
        
        return reversedString;
    }
    
    public static char[] stringReverseInPlace(char[] string) 
    {
        for(int i = 0, j = string.length - 1; i < string.length / 2; i++, j--)
        {
          char c = string[i];
          string[i] = string[j];
          string[j] = c;
        }
        return string;
    }
    
    
    public static int stringToInt(String input)
    {
        int outputInt = 0;
        
       //loop through characters of string in reverse
        for(int i = input.length() -1; i >= 0; i--)
        {
             //cast each character to an int, multiply it by powers of 10 for its location, and add to holder int
             char currentChar = input.charAt(i);
             int tempInt = (int)(Character.getNumericValue(currentChar) * (Math.pow(10, input.length()-1 - i) ));
             
             //if < 0 check
             
             outputInt += tempInt;
        }
        
        return outputInt;
           
    }
    
    
    public static boolean testParens(String input)
    {
        //check for null
        if( input == null)
         { 
           throw new InvalidParameterException("Input can’t be null");
         }

        //initialize a stack
        java.util.LinkedList<Character> stack= new java.util.LinkedList<>();

        //iterate through string
        for( int i = 0; i < input.length(); i++)
        {
            //get the character
            char paren = input.charAt(i);

            //check if char is valid
            if(paren != '(' && paren != ')')
              {
                  throw new InvalidParameterException("string contains a bad character");
              }

             //if its a left paren push onto stack, if right paren pop
             if(paren == '(')
              {
                 stack.push(paren);
               }
              else
               {
                   //if we try to pop an empty stack the parens are messed up
                   if(stack.peek() == null)
                      {
                        return false;
                       }
                   else
                     {
                      stack.pop();
                     }
                }


        }

         //at the end if the stack isn’t empty its messed up
        if(stack.size()!= 0)
         { 
           return false;
          }
         else
         {
           return true;
          }
   

    }
    
    
    public static void reverseLinkedList(LinkedList list)
    {

        //check for null parameter
        if(list == null)
        {
          throw new InvalidParameterException("list cant be null");
        }


        //get root node of list
       LinkedListNode previous = null;
       LinkedListNode currentNode= list.getHead();

        //iterate through list
      while( currentNode != null)
       {
           //find the next node
           LinkedListNode next = currentNode.getNext();

           //make current node point to previous node
           currentNode.setNext(previous);

           // if next item is null point head to current node
           if(next == null)
           {
             list.setHead(currentNode);
           }

          previous = currentNode;
          currentNode= next;
       }


    }
    
    public static boolean isPalindone(String input)
    {
        for(int start = 0, end = input.length() -1; start<= end; start++, end--)
        {
            char startChar = input.charAt(start);
            char endChar = input.charAt(end);
            
            if(startChar != endChar)
            {
                return false;
            }
        }
        
        return true;
    }


    
}
