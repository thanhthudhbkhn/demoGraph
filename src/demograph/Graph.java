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

	public void displayGraph() {
		Enumeration data = ds_dinh.keys();
		while (data.hasMoreElements()) {
			Vertex v = ds_dinh.get(data.nextElement());
			System.out.println(v.vertexId + "-" + v.deg + " " + v.adjacencyList);
//System.out.println(v.vertexId + " " + v.adjacencyList);

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
				v.deg++;
				addVertex(v);
			} else {
				//if vertex v1 is existed, get v1 and add v2 to v1's adjacency list
				Vertex v = ds_dinh.get(v1);
				v.adjacencyList.add(v2);
				v.deg++;
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

	public Hashtable createGraph(String file) throws FileNotFoundException, IOException {

		//define path for open file with shorter address
		String path = "D:/gradute/demoGraph/src";
//		try (BufferedReader in = new BufferedReader(new FileReader(path + "/facebook/facebook_combined.txt"))) {
		try (BufferedReader in = new BufferedReader(new FileReader(path + file))) {
			String currentLine = in.readLine();
			currentLine = in.readLine();
			currentLine = in.readLine();
			currentLine = in.readLine();
			currentLine = in.readLine();//currentLine has format: "v1\tv2"
			while (currentLine != null) {
				if (!"".equals(currentLine)) {
					int vertex1 = getVerticesFromString(currentLine)[0];
					int vertex2 = getVerticesFromString(currentLine)[1];
					//add 2 vertices into lists
					addEdge("directed", vertex1, vertex2);
					so_canh++;
				}
				currentLine = in.readLine();
			}
		}
		
//		displayGraph();
		return ds_dinh;
	}
}