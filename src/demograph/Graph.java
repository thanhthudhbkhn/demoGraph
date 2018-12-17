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

	AVLTree ds_dinh = new AVLTree();
	int so_dinh = 0;
	int so_canh = 0;

	//return true if BFS is successful
	public boolean BFS(int vertexId) {
		if (ds_dinh.getVertex(ds_dinh.root, vertexId) == null) {
			return false;
		}
		// Create a list contain the visited vertices
		AVLTree visited = new AVLTree();
		// Create a queue for BFS
		LinkedList<Integer> queue = new LinkedList<>();

		// Mark the current node as visited and enqueue it
		visited.root = visited.insert(visited.root, vertexId);
		queue.add(vertexId);

		while (!queue.isEmpty()) {
			// Dequeue a vertex from queue and print it
			vertexId = queue.poll();
//			System.out.print(vertexId + " ");

			// Get all adjacent vertices of the dequeued vertex 
			Vertex vertex = ds_dinh.getVertex(ds_dinh.root, vertexId);
			tham(vertex.adjacencyList.root, visited, queue);
		}
//		System.out.println("");
		return true;
	}

	public void DFSFunction(int vertexId, AVLTree visited) {
		// Mark the current node as visited and enqueue it
		visited.root = visited.insert(visited.root, vertexId);
//		System.out.print(vertexId + " ");

		// Get all adjacent vertices of the vertex
		Vertex vertex = ds_dinh.getVertex(ds_dinh.root, vertexId);
		if (vertex.adjacencyList.root != null) {
			Vertex adjacent_root = vertex.adjacencyList.root;
			if (visited.getVertex(visited.root, adjacent_root.vertexId) == null) {
				DFSFunction(adjacent_root.vertexId, visited);
			}
			if (adjacent_root.right != null && visited.getVertex(visited.root,adjacent_root.right.vertexId) == null) {
				DFSFunction(adjacent_root.right.vertexId, visited);
			}
			if (adjacent_root.left != null && visited.getVertex(visited.root,adjacent_root.left.vertexId) == null) {
				DFSFunction(adjacent_root.left.vertexId, visited);
			}
		}
	}

	public boolean DFS(int vertexId) {
		if (ds_dinh.getVertex(ds_dinh.root, vertexId) == null) {
			return false;
		}
		// Create a list contain the visited vertices
		AVLTree visited = new AVLTree();
		DFSFunction(vertexId, visited);
		return true;
	}

	public void displayGraph(Vertex root) {
		if (root != null) {
			System.out.println(root.vertexId + " ");
			displayGraph(root.adjacencyList.root);
			displayGraph(root.left);
			displayGraph(root.right);
		}
	}

	public void tham(Vertex vertex, AVLTree visited, LinkedList<Integer> queue) {
		if (vertex != null) {
			if (visited.getVertex(visited.root, vertex.vertexId) == null) {
				visited.root = visited.insert(visited.root, vertex.vertexId);

//				System.out.println("\nds da tham: ");
//				displayGraph(visited.root);
//				System.out.println("--");
				queue.add(vertex.vertexId);
//				System.out.println("queue: " + queue);
			}
			if (vertex.left != null) {
				tham(vertex.left, visited, queue);
			}
			if (vertex.right != null) {
				tham(vertex.right, visited, queue);
			}
		}
	}

	public void displayGraphInfo() {
		System.out.println("There are:");
		System.out.println(so_dinh + " vertices");
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

	public void addEdge(String type, int v1, int v2) {
		if ("directed".equals(type)) {
			Vertex dst = new Vertex(v2);
			//if there are not vertex v1, create new vertex before add edge
			if (ds_dinh.getVertex(ds_dinh.root, v1) == null) {
				Vertex v = new Vertex(v1);
				v.adjacencyList.root = v.adjacencyList.insert(v.adjacencyList.root, dst);
				ds_dinh.root = ds_dinh.insert(ds_dinh.root, v);
				so_dinh++;
			} else {
				//if vertex v1 is existed, get v1 and add v2 to v1's adjacency list
				Vertex v = ds_dinh.getVertex(ds_dinh.root, v1);
				v.adjacencyList.root = v.adjacencyList.insert(v.adjacencyList.root, dst);
				if (ds_dinh.getVertex(ds_dinh.root, v2) == null) {
					v = new Vertex(v2);
					ds_dinh.root = ds_dinh.insert(ds_dinh.root, v);
					so_dinh++;
				}
			}

		} else if ("undirected".equals(type)) {
			addEdge("directed", v1, v2);
			addEdge("directed", v2, v1);
		}
	}

	public AVLTree createGraph() throws FileNotFoundException, IOException {
		//define path for open file with shorter address
		String path = "D:/gradute/demoGraph/src";
//		try (BufferedReader in = new BufferedReader(new FileReader(path + "/facebook/facebook_combined.txt"))) {
		try (BufferedReader in = new BufferedReader(new FileReader(path + "/roadNet/roadNet-PA.txt"))) {
			String currentLine = in.readLine();
			currentLine = in.readLine();
			currentLine = in.readLine();
			currentLine = in.readLine();
			currentLine = in.readLine();//currentLine has format: "v1 v2"
			while (currentLine != null && so_canh < 10000) {
				if (!"".equals(currentLine)) {
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
//		addEdge("undirected", 2, 4);
//		so_canh++;
//		addEdge("undirected", 2, 5);
//		so_canh++;

//		displayGraph(ds_dinh.root);
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

//	public boolean findPath(int vertex1, int vertex2) {
//		//if vertex1 or vertex2 are not existed in graph, return false
//		if (ds_dinh.getVertex(ds_dinh.root, vertex1) == null || ds_dinh.getVertex(ds_dinh.root, vertex2) == null) {
//			System.out.println("The vertex does not exist.");
//			return false;
//		}
//		// else if 2 vertices are the same, return true
//		if (vertex1 == vertex2) {
//			return true;
//		}
//		//else
//		int start = vertex1;
//		// Create a list contain the visited vertices
//		ArrayList<Integer> visited = new ArrayList<>();
//		int[] path = new int[10000]; //do lon cua mang phai > chi so lon nhat cua dinh
//		// Create a queue for BFS
//		LinkedList<Integer> queue = new LinkedList<>();
//
//		// Mark the current node as visited and enqueue it
//		visited.add(vertex1);
//		queue.add(vertex1);
//
//		while (!queue.isEmpty()) {
//			// Dequeue a vertex from queue and print it
//			vertex1 = queue.poll();
//
//			// Get all adjacent vertices of the dequeued vertex 
//			// If a adjacent has not been visited, then mark it
//			// visited and enqueue it            
//			Vertex vertex = ds_dinh.getVertex(ds_dinh.root, vertex1);
//			for (int i = 0; i < vertex.adjacencyList.size(); i++) {
//				int adjacentVertex = vertex.adjacencyList.get(i);
//				if (visited.contains(adjacentVertex) == false) {
//					visited.add(adjacentVertex);
//					//If this vertex is the destination return true
//					if (adjacentVertex == vertex2) {
//						path[adjacentVertex] = vertex1;
//						displayPath(path, start, vertex2);
//						return true;
//					}
//					//Else continue BFS
//					queue.add(adjacentVertex);
//					path[adjacentVertex] = vertex1;
//				}
//			}
//		}
//		return false;
//	}
}
