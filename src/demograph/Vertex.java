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
    List<Integer> adjacencyList = new LinkedList();

    public Vertex(int vertexId) {
        this.vertexId = vertexId;
    }

    public int getVertexId() {
        return vertexId;
    }

    public List<Integer> getAdjacencyList() {
        return adjacencyList;
    }
    
}
