/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demograph;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Computer
 */
public class Vertex {

	int vertexId, height;
    List<Integer> adjacencyList = new LinkedList();
	Vertex left;
	Vertex right;

	Vertex(int d) {
		vertexId = d;
		height = 1;
	}
}
