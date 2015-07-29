/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graphs;

import datastructures.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Mike
 */
public class Graph<T> {
 
    
   private Map<T, GraphNode<T>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    /**
     * Adds a vertex to the graph.
     * @param vertex        vertex to add
     */
    public boolean addVertex(T vertex) {
        if (adjacencyList.containsKey(vertex)) {
            return false;
        }
        adjacencyList.put(vertex, new GraphNode<>(vertex));
        return true;
    }

    /**
     * Adds a directed edge between two vertices in the graph.
     * @param vertex1       vertex where the (directed) edge begins
     * @param vertex2       vertex where the (directed) edge ends
     */
    public boolean addEdge(T vertex1, T vertex2) {
        return addEdge(vertex1, vertex2, 0);
    }

    /**
     * Adds a weighted directed edge between two vertices in the graph.
     * @param vertex1       vertex where the (directed) edge begins
     * @param vertex2       vertex where the (directed) edge ends
     * @param weight        weight of the edge
     */
    public boolean addEdge(T vertex1, T vertex2, int weight) {
        if (!containsVertex(vertex1) || !containsVertex(vertex2)) {
            throw new RuntimeException("Vertex does not exist");
        }

        // add the edge
        GraphNode<T> node1 = getNode(vertex1);
        GraphNode<T> node2 = getNode(vertex2);
        return node1.addEdge(node2, weight);
    }

    /**
     * Remove a vertex from the graph.
     * @param vertex        vertex to be removed
     * @return      true if the vertex was removed, false if no such vertex was found.
     */
    public boolean removeVertex(T vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            return false;
        }

        // get node to be removed
        final GraphNode<T> toRemove = getNode(vertex);

        // remove all incoming edges to node
        adjacencyList.values().forEach(node -> node.removeEdge(toRemove));

        // remove the node
        adjacencyList.remove(vertex);
        return true;
    }

    /**
     * Method to remove a directed edge between two vertices in the graph.
     * @param vertex1       vertex where the (directed) edge begins
     * @param vertex2       vertex where the (directed) edge ends
     * @return  true if the edge was removed, false if no such edge was found.
     */
    public boolean removeEdge(T vertex1, T vertex2) {
        if (!containsVertex(vertex1) || !containsVertex(vertex2)) {
            return false;
        }
        return getNode(vertex1).removeEdge(getNode(vertex2));
    }

    /**
     * Method to get the number of vertices in the graph.
     * @return      count of vertices
     */
    public int vertexCount() {
        return adjacencyList.keySet().size();
    }

    /**
     * Method to get the number of edges in the graph.
     * @return      count of edges
     */
    public int edgeCount() {
        return adjacencyList.values()
                .stream()
                .mapToInt(GraphNode::getEdgeCount)
                .sum();
    }

    /**
     * Method to check if a vertex exists in the graph.
     * @param vertex    vertex which is to be checked
     * @return  returns true if the graph contains the vertex, false otherwise
     */
    public boolean containsVertex(T vertex) {
        return adjacencyList.containsKey(vertex);
    }

    /**
     * Method to check if an edge exists in the graph.
     * @param vertex1       vertex where the (directed) edge begins
     * @param vertex2       vertex where the (directed) edge ends
     * @return   returns true if the graph contains the edge, false otherwise
     */
    public boolean containsEdge(T vertex1, T vertex2) {
        if (!containsVertex(vertex1) || !containsVertex(vertex2)) {
            return false;
        }
        return getNode(vertex1).hasEdge(getNode(vertex2));
    }
    
    private GraphNode<T> getNode(T value) {
        return adjacencyList.get(value);
    }

    private void resetGraph() {
        adjacencyList.keySet().forEach(key -> {
            GraphNode<T> node = getNode(key);
            node.setParent(null);
            node.setVisited(false);
        });
    }
    
    
    public void breadthFirstSearch(T startVertex)
    {
        //make our queue
        java.util.LinkedList<GraphNode<T>> queue = new java.util.LinkedList<>();
        
        //enqueue our first node
        GraphNode<T> startNode = this.getNode(startVertex);
        queue.add(startNode);
        startNode.setVisited(true);
        
        while(!queue.isEmpty())
        {
            GraphNode<T> workingNode = queue.poll();
            
            for(GraphEdge<T> edge: workingNode.edges())
            {
                GraphNode<T> next = edge.toNode();
                if(!next.isVisited())
                {
                    queue.add(next);
                    next.setVisited(true);
                    //System.out.println(next.vertex);
                }
            }
            
        }
    
    }
    
    public int componentCount()
    {
        int componentCount = 0;
        
        for(GraphNode<T> node: this.adjacencyList.values())
        {
            if(!node.isVisited())
            {               
                this.breadthFirstSearch(node.vertex());
                componentCount++;
            }
        }
        
        this.resetGraph();
        return componentCount;
    }
    
       
    
    public static class GraphNode<T>
    {
        
        private T vertex;

        private List<GraphEdge<T>> edges;

        private GraphNode<T> parent;

        private boolean isVisited;

        public GraphNode(T vertex) {
            this.vertex = vertex;
            this.edges = new ArrayList<>();
        }

        public T vertex() {
            return vertex;
        }

        public boolean addEdge(GraphNode<T> node, int weight) {
            if (hasEdge(node)) {
                return false;
            }
            GraphEdge<T> newEdge = new GraphEdge<>(this, node, weight);
            return edges.add(newEdge);
        }

        public boolean removeEdge(GraphNode<T> node) {
            Optional<GraphEdge<T>> optional = findEdge(node);
            if (optional.isPresent()) {
                return edges.remove(optional.get());
            }
            return false;
        }

        public boolean hasEdge(GraphNode<T> node) {
            return findEdge(node).isPresent();
        }

        private Optional<GraphEdge<T>> findEdge(GraphNode<T> node) {
            return edges.stream()
                    .filter(edge -> edge.isBetween(this, node))
                    .findFirst();
        }

        public List<GraphEdge<T>> edges() {
            return edges;
        }

        public int getEdgeCount() {
            return edges.size();
        }

        public GraphNode<T> parent() {
            return parent;
        }

        public boolean isVisited() {
            return isVisited;
        }

        public void setVisited(boolean isVisited) {
            this.isVisited = isVisited;
        }

        public void setParent(GraphNode<T> parent) {
        this.parent = parent;
    }
    }
    
    
    public static class GraphEdge<T> {

    private GraphNode<T> node1;

    private GraphNode<T> node2;

    private int weight;

    public GraphEdge(GraphNode<T> node1, GraphNode<T> node2, int weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }

    public GraphNode<T> fromNode() {
        return node1;
    }

    public GraphNode<T> toNode() {
        return node2;
    }

    public boolean isBetween(GraphNode<T> node1, GraphNode<T> node2) {
        return (this.node1 == node1 && this.node2 == node2);
    }
}
}

