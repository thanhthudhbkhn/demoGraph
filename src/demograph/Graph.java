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

	ArrayList<Vertex> ds_dinh = new ArrayList<>();
	int so_dinh = 0;
	int so_canh = 0;

	public void displayGraph() {
		for (int i = 0; i < ds_dinh.size(); i++) {
			Vertex v = ds_dinh.get(i);
			System.out.println(v.vertexId + "-" + v.deg + " " + v.adjacencyList);
		}
	}

	public void displayGraphInfo() {
		System.out.println(so_dinh + " vertices");
		System.out.println(so_canh + " edges");
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

	public Vertex getVertex(int vertexId) {
		for (int i = 0; i < ds_dinh.size(); i++) {
			if (ds_dinh.get(i).getVertexId() == vertexId) {
				return ds_dinh.get(i);
			}
		}
		return null;
	}

	public void addVertex(Vertex vertex) {
		ds_dinh.add(vertex);
		so_dinh++;
	}

	public void addEdge(String type, int v1, int v2) {
		if ("directed".equals(type)) {
			//if there are not vertex v1, create new vertex before add edge
			Vertex v = getVertex(v1);
			if (v == null) {
				v = new Vertex(v1);
				v.adjacencyList.add(v2);
				v.deg++;
				addVertex(v);
			} else {
				//if vertex v1 is existed, get v1 and add v2 to v1's adjacency list

				v.adjacencyList.add(v2);
				v.deg++;
				if (getVertex(v2) == null) {
					v = new Vertex(v2);
					addVertex(v);
				}
			}
		} else if ("undirected".equals(type)) {
			addEdge("directed", v1, v2);
			addEdge("directed", v2, v1);
		}
	}

	public ArrayList createGraph(String file) throws FileNotFoundException, IOException {
		//define path for open file with shorter address
		String path = "D:/gradute/demoGraph/src";
		try (BufferedReader in = new BufferedReader(new FileReader(path + file))) {
			String currentLine = in.readLine();
			currentLine = in.readLine();
			currentLine = in.readLine();
			currentLine = in.readLine();
			currentLine = in.readLine();//currentLine has format: "v1 v2"
			while (currentLine != null) {
				if (!"".equals(currentLine)) {
					int vertex1 = getVerticesFromString(currentLine)[0];
					int vertex2 = getVerticesFromString(currentLine)[1];
					//add 2 vertices into lists
					addEdge("undirected", vertex1, vertex2);
					so_canh++;
				}
				currentLine = in.readLine();
			}
		}
//		addEdge("undirected", 1, 2);so_canh++;
//		addEdge("undirected", 1, 3);so_canh++;
//		addEdge("undirected", 1, 4);so_canh++;
//		addEdge("undirected", 3, 2);so_canh++;
//		displayGraph();
		return ds_dinh;
	}

}
