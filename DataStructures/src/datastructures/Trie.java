package datastructures;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mike
 */
public class Trie {
    
    //root
    public TrieNode root;
    
    //node class
    public static class TrieNode
    {
        //one pointer for each letter of the alphabet
        public TrieNode[] children = new TrieNode[26]; 
        
        //does this node terminate a word
        public boolean isWord;
    }
    
    
    public Trie()
    {
        root = new TrieNode();
    }
    
    
    //add key
    public void add(String key)
    {      
        //dont keep keys with strange characters
        if(!key.matches("[a-zA-Z]*"))
        {
            return;
        }
        
        this.addRecurse(this.root,key);
    }
    
    private void addRecurse(TrieNode node, String key)
    {
        //get first letter of key
        char c = key.charAt(0);
        
        //get index of letter
        int index = Trie.getIndexOfChar(c);
        
        //if node doesnt have a child entry at this index add one
        if(node.children[index] == null)
        {
            TrieNode newNode = new TrieNode();                      
            node.children[index] = newNode;
            
            //if string is length(1) set the node isWord boolean to true, this is also our termination condition
            if(key.length() == 1)
            {
                newNode.isWord = true;               
                return;               
            }
                      
        }
        
        // recurse on child with key = key.substring(1)
        this.addRecurse(node.children[index], key.substring(1));
    }
    
    
    public TrieNode getNode(String key)
    {
        return this.getNodeRecurse(this.root, key);
    }
    
    private TrieNode getNodeRecurse(TrieNode node, String key)
    {
        //if our node is null return false
        if(node == null)
        {
            return null;
        }       
        
        //if they key is length 1 and if our node has that key as a child, return the node 
        if(key.length() == 1)
        {
                    
            char c = key.charAt(0);
            int index = Trie.getIndexOfChar(c);

            return node.children[index];
        }
        
        //recurse child node on key.substring(1)
        char c = key.charAt(0);
        int index = Trie.getIndexOfChar(c);
        return this.getNodeRecurse(node.children[index], key.substring(1));       
        
    }
    
    public boolean hasKey(String key)
    {
        TrieNode node = this.getNode(key);
        
        if(node != null && node.isWord)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean hasPrefix(String key)
    {
        TrieNode node = this.getNode(key);
        
        if(node != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public ArrayList<String> keysWithPrefix(String key)
    {
        
        //get prefix node
        TrieNode prefixNode = this.getNode(key);
        
        ArrayList<String> list = new ArrayList<>();
        
        this.keysWithPrefixRecurse(prefixNode, new StringBuilder(key), list);
        
        return list;
     
    }
    
    private void keysWithPrefixRecurse(TrieNode node, StringBuilder prefix, ArrayList<String> list)
    {
        //terminating condition
        if (node == null)
        {
            return;
        }
        
        if (node.isWord) 
        {
            list.add(prefix.toString());
        }
        
        for (int i = 0; i < 26; i++) 
        {
            char c = (char)('a' + i);
            prefix.append(c);
            keysWithPrefixRecurse(node.children[i], prefix, list);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }
    

    
    private static int getIndexOfChar(char c)
    {
        return (int)c - (int)'a';
    }
    
}
