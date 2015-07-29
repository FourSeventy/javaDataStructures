/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datastructures;

/**
 *
 * @author Mike
 */
public class BinarySearchTree<K extends Comparable<K>, V> {
    
    private TreeNode<K,V> root = null;
    
    public BinarySearchTree()
    {
        
    }
    
    
    public void addNode(K key, V value)
    {
        //build new node
        TreeNode<K,V> node = new TreeNode<>(key, value);
        
        //check if root is null
        if(root == null)
        {
            root = node;
            return;
        }
        
        //recurse through tree looking for place to put the node
        this.addNodeRecurse(root, node);
    }
    
    private void addNodeRecurse(TreeNode<K,V> root, TreeNode<K,V> node)
    {
        //if the next node from the root is null, insert the node
        if(node.getKey().compareTo(root.getKey()) < 0 && root.getLeft() == null)
        {
            root.setLeft(node); 
            return;
        }
        else if(node.getKey().compareTo(root.getKey()) >= 0 && root.getRight() == null)
        {
            root.setRight(node);
            return;
        }
        
        //if node key is less than root key, recurse on left child
        if(node.getKey().compareTo(root.getKey()) < 0)
        {
            addNodeRecurse(root.getLeft(),node);
        }
        else //else recurse on right child
        {
            addNodeRecurse(root.getRight(),node);
        }
        
    }
    
    
    public void deleteNode(K key)
    {
       this.root = this.delete(this.root, key);
             
    }
    
    //TODO: this needs to handle the case where a node has two children
    private TreeNode<K,V> delete(TreeNode<K,V> node, K key)
    {
        //if node is null return null
        if(node == null)
        {
            return null;
        }
        
        //if the key is less than the node recurse left
        if(key.compareTo(node.getKey()) < 0)
        {
            node.setLeft(delete(node.getLeft(),key));
        }
        else if(key.compareTo(node.getKey()) > 0)
        {
            node.setRight(delete(node.getRight(),key));
        }
        else //if our node matches the key
        {
            //if our node only has one child, delete the node and return the child
            if(node.getRight() == null)
            {
                return node.getLeft();
            }
            if(node.getLeft() == null)
            {
                return node.getRight();
            }
        }
        
        return node;
        
    }
    
    public V get(K key)
    {
        return this.getNodeRecurse(this.root, key).getValue();
    }
    
    /**
     * O(log n) time
     * @param root
     * @param key
     * @return 
     */
    private TreeNode<K,V> getNodeRecurse(TreeNode<K,V> root, K key)
    {
        
        //if the root is null, return null
        if(root == null)
        {
            return null;
        }
        
        //if the root equals the key, return the value
        if(root.getKey().equals(key))
        {
            return root;
        }
        
        //if the key is less than the root value, recurse left
        if(key.compareTo(root.getKey()) < 0)
        {
            return getNodeRecurse(root.getLeft(),key);
        }
        else
        {
            //else recurse right
            return getNodeRecurse(root.getRight(),key);
        }
               
    }
    
    public void inorderVisit(TreeNodeVisitor<K,V> visitor)
    {
        inorderVisitRecurse(this.root,visitor);
    }
    
    private void inorderVisitRecurse(TreeNode<K,V> root, TreeNodeVisitor<K,V> visitor)
    {
        if(root == null)
        {
            return;
        }
        
        inorderVisitRecurse(root.getLeft(),visitor);
        visitor.visitNode(root);
        inorderVisitRecurse(root.getRight(),visitor);
        
    }
    
    public void preorderVisit(TreeNodeVisitor<K,V> visitor)
    {
        this.preorderVisitRecurse(this.root,visitor);
    }
    
    private void preorderVisitRecurse(TreeNode<K,V> root, TreeNodeVisitor<K,V> visitor)
    {
        if(root == null)
        {
            return;
        }
        
        visitor.visitNode(root);
        preorderVisitRecurse(root.getLeft(),visitor);       
        preorderVisitRecurse(root.getRight(),visitor);
    }
    
    public void postorderVisit(TreeNodeVisitor<K,V> visitor)
    {
        this.postorderVisitRecurse(this.root,visitor);
    }
    
    private void postorderVisitRecurse(TreeNode<K,V> root, TreeNodeVisitor<K,V> visitor)
    {
        if(root == null)
        {
            return;
        }
        
        
        postorderVisitRecurse(root.getLeft(),visitor);       
        postorderVisitRecurse(root.getRight(),visitor);
        visitor.visitNode(root);
    }
    
    
    public String toString()
    {
        return this.toStringRecurse(root);
    }
    
    private String toStringRecurse(TreeNode<K,V> root)
    {
        //if root is null return empty string
        if(root == null)
        {
            return "";
        }
        
        return toStringRecurse(root.getLeft()) + " (" + root.getKey().toString()+ ","+ root.getValue().toString()+") " + toStringRecurse(root.getRight());
        
        
    }
    
    
  
    
    
    
    
    
    
    
    
    public static class TreeNode<K2 extends Comparable<K2>, V2>
    {
        private K2 key;
        private V2 value;
        
        private TreeNode<K2,V2> left;
        private TreeNode<K2,V2> right;
        
        public TreeNode(K2 key, V2 value)
        {
            this.key = key;
            this.value = value;
        }
        
        
        
        public TreeNode<K2,V2> getLeft()
        {
            return this.left;
        }
        
        public TreeNode<K2,V2> getRight()
        {
            return this.right;
        }
        
        public void setLeft(TreeNode<K2,V2> node)
        {
            this.left = node;
        }
        
        public void setRight(TreeNode<K2,V2> node)
        {
            this.right = node;
        }
        
        public K2 getKey()
        {
            return this.key;
        }
        
        public V2 getValue()
        {
            return this.value;
        }
        
        
    }
    
    
    
    public static interface TreeNodeVisitor<K3 extends Comparable<K3>, V3>
    {
        public void visitNode(TreeNode<K3,V3> node);
    }
    
}
