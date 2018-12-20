/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demograph;

import java.util.*;
import java.io.*;

/**
 *
 * @author ThuPT
 */
public class Graph {

	Hashtable<Integer, Vertex> ds_dinh = new Hashtable<Integer, Vertex>();
	int so_canh = 0;

//	return true if BFS is successful
	public boolean BFS(int vertexId) {
		if (ds_dinh.containsKey(vertexId) == false) {
			return false;
		}
		// Mark all the vertices as not visited(By default
		// set as false)
		ArrayList<Boolean> visited = new ArrayList<>(Collections.nCopies(2000000, false));
		// Create a queue for BFS
		LinkedList<Integer> queue = new LinkedList<>();

		// Mark the current node as visited and enqueue it
		visited.set(vertexId, true);
		queue.add(vertexId);

		while (!queue.isEmpty()) {
			// Dequeue a vertex from queue and print it
			vertexId = queue.poll();
//			System.out.print(vertexId + " ");

			// Get all adjacent vertices of the dequeued vertex 
			Vertex vertex = ds_dinh.get(vertexId);
			int adjacentSize = vertex.adjacencyList.size();
			for (int i = 0; i < adjacentSize; i++) {
				int adjacentVertex = vertex.adjacencyList.get(i);
				// If a adjacent has not been visited, then mark it
				// visited and enqueue it    
				if (visited.get(adjacentVertex) == false) {
					visited.set(adjacentVertex, true);
					queue.add(adjacentVertex);
				}
			}
		}
		return true;
	}

	public boolean DFS(int vertexId) {
		if (ds_dinh.containsKey(vertexId) == false) {
			return false;
		}
		// Mark all the vertices as not visited(By default
		// set as false)
		ArrayList<Boolean> visited = new ArrayList<>(Collections.nCopies(2000000, false));
		// Create a stack for DFS
		Stack<Integer> stack = new Stack<>();

		// Mark the current node as visited and push it to stack
		stack.push(vertexId);
		int adjacentSize;
		while (!stack.isEmpty()) {
			// Pop a vertex from stack and print it

			vertexId = stack.peek();
			stack.pop();

			if (visited.get(vertexId) == false) {
//				System.out.print(vertexId + " ");
				visited.set(vertexId, true);
			}
			// Get all adjacent vertices of the dequeued vertex 
			Vertex vertex = ds_dinh.get(vertexId);
			adjacentSize = vertex.adjacencyList.size();
			for (int i = 0; i < adjacentSize; i++) {
				int adjacentVertex = vertex.adjacencyList.get(i);
				// If a adjacent has not been visited, then mark it
				// visited and push it to stack
				if (visited.get(adjacentVertex) == false) {
					stack.push(adjacentVertex);
				}
			}
		}
		return true;
	}

	public void displayGraph() {
		Enumeration data = ds_dinh.keys();
		while (data.hasMoreElements()) {
			Vertex v = ds_dinh.get(data.nextElement());
			System.out.println(v.vertexId + " " + v.adjacencyList);

		}
	}

	public void displayGraphInfo() {
		System.out.println("There are:");
		System.out.println(ds_dinh.size() + " vertices");
		System.out.println("and " + so_canh + " edges");
		System.out.println("in this graph.");
	}

	public int[] getVerticesFromString(String str) {
		//get 2 vertexs from an line "v1 v2"
		int[] result = new int[2];
		//String[] vertices = {v1,v2}
		String[] vertices = str.split("\t");
		//Convert from string into integer
		result[0] = Integer.parseInt(vertices[0]);
		result[1] = Integer.parseInt(vertices[1]);
		return result;
	}

	public void addVertex(Vertex vertex) {
		ds_dinh.put(vertex.vertexId, vertex);
	}

	public void addEdge(String type, int v1, int v2) {
		if ("directed".equals(type)) {
			//if there are not vertex v1, create new vertex before add edge
			if (ds_dinh.containsKey(v1) == false) {
				Vertex v = new Vertex(v1);
				v.adjacencyList.add(v2);
				addVertex(v);
			} else {
				//if vertex v1 is existed, get v1 and add v2 to v1's adjacency list
				Vertex v = ds_dinh.get(v1);
				v.adjacencyList.add(v2);
			}
			if (ds_dinh.containsKey(v2) == false) {
				Vertex v = new Vertex(v2);
				addVertex(v);
			}
		} else if ("undirected".equals(type)) {
			addEdge("directed", v1, v2);
			addEdge("directed", v2, v1);
		}
	}

	//return the index of the vertex in the list
	public int indexOf(int vertexId) {
		for (int i = 0; i < ds_dinh.size(); i++) {
			if (vertexId == ds_dinh.get(i).getVertexId()) {
				return i;
			}
		}
		return -1;
	}

	public Hashtable createGraph() throws FileNotFoundException, IOException {
		//define path for open file with shorter address
		String path = "D:/gradute/demoGraph/src";
//		try (BufferedReader in = new BufferedReader(new FileReader(path + "/facebook/facebook_combined.txt"))) {
		try (BufferedReader in = new BufferedReader(new FileReader(path + "/roadNet/roadNet-PA.txt"))) {
			String currentLine = in.readLine();
			currentLine = in.readLine();
			currentLine = in.readLine();
			currentLine = in.readLine();
			currentLine = in.readLine();//currentLine has format: "v1\tv2"
			while (currentLine != null) {
				if (!"".equals(currentLine) && so_canh < 300000) {
					int vertex1 = getVerticesFromString(currentLine)[0];
					int vertex2 = getVerticesFromString(currentLine)[1];
					//add 2 vertices into lists
					addEdge("directed", vertex1, vertex2);
					so_canh++;
				}
				currentLine = in.readLine();
			}
		} //currentLine doc tu file, co dang "v1 v2"
//		addEdge("undirected", 1, 2);
//		so_canh++;
//		addEdge("undirected", 1, 3);
//		so_canh++;
//		addEdge("undirected", 1, 4);
//		so_canh++;
//		addEdge("undirected", 1, 5);
//		so_canh++;
//		addEdge("undirected", 4, 2);
//		so_canh++;
//		addEdge("undirected", 5, 2);
//		so_canh++;
//		displayGraph();

		return ds_dinh;
	}

	public void displayPath(int path[], int vertex1, int vertex2) {
//        int destination = vertex2;
		System.out.print(vertex2);
		do {
			int step = path[vertex2];
			vertex2 = step;
			System.out.print(" <- " + vertex2);
		} while (vertex2 != vertex1);
	}

	public boolean findPath(int vertex1, int vertex2) {
		//if vertex1 or vertex2 are not existed in graph, return false
		if (ds_dinh.containsKey(vertex1) == false || ds_dinh.containsKey(vertex2) == false) {
			System.out.println("The vertex does not exist.");
			return false;
		}
		// else if 2 vertices are the same, return true
		if (vertex1 == vertex2) {
			return true;
		}
		//else
		int start = vertex1;
		// Mark all the vertices as not visited(By default
		// set as false)
		ArrayList<Boolean> visited = new ArrayList<>(Collections.nCopies(10000, false));
		int[] path = new int[10000]; //do lon cua mang phai > chi so lon nhat cua dinh
		// Create a queue for BFS
		LinkedList<Integer> queue = new LinkedList<>();

		// Mark the current node as visited and enqueue it
		visited.set(vertex1, true);
		queue.add(vertex1);

		while (!queue.isEmpty()) {
			// Dequeue a vertex from queue and print it
			vertex1 = queue.poll();

			// Get all adjacent vertices of the dequeued vertex 
			// If a adjacent has not been visited, then mark it
			// visited and enqueue it            
			Vertex vertex = ds_dinh.get(vertex1);
			for (int i = 0; i < vertex.adjacencyList.size(); i++) {
				int adjacentVertex = vertex.adjacencyList.get(i);
				if (visited.get(adjacentVertex) == false) {
					visited.set(adjacentVertex, true);
					//If this vertex is the destination return true
					if (adjacentVertex == vertex2) {
						path[adjacentVertex] = vertex1;
						displayPath(path, start, vertex2);
						return true;
					}
					//Else continue BFS
					queue.add(adjacentVertex);
					path[adjacentVertex] = vertex1;
				}
			}
		}
		return false;
	}
}
