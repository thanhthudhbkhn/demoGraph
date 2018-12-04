/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demograph;

import java.util.*;

/**
 *
 * @author thanhthu
 */
public class Vertex {
    int vertexId;
	int priority;
    List<Integer> adjacencyList = new LinkedList();
	Vertex left, right;

    public Vertex(int vertexId) {
        this.vertexId = vertexId;
		this.left = this;
		this.right = this;
		this.priority = Integer.MAX_VALUE;
    }
	
	public Vertex(int vertexId, Vertex left, Vertex right) {
        this.vertexId = vertexId;
		this.left = left;
		this.right = right;
		this.priority = new Random().nextInt();
    }

    public int getVertexId() {
        return vertexId;
    }

    public List<Integer> getAdjacencyList() {
        return adjacencyList;
    }
    
}
