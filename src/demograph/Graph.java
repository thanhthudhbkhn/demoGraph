/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demograph;

import java.io.*;
import java.util.LinkedList;

/**
 *
 * @author ThuPT
 */
public class Graph {

    TreapTree ds_dinh = new TreapTree();
    int so_canh = 0;
//	int so_dinh = 0;

    public void displayGraph() {
        ds_dinh.preorder();
    }

    public void displayGraphInfo() {
        System.out.println(ds_dinh.countNodes() + " vertices");
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
        Vertex v = ds_dinh.get(vertexId);
        return v;
    }

    public void addVertex(Vertex vertex) {
        ds_dinh.insert(vertex);
    }

    public void deleteVertex(Vertex vertex) {
        so_canh = so_canh - vertex.deg;
        int n = vertex.adjacencyList.size();
        for (int i = 0; i < n; i++) {
            Vertex v = getVertex(vertex.adjacencyList.get(i));
            if (v != null) {
                v.deg--;
                ds_dinh.update(v);
//                int m = v.adjacencyList.size();
//            for (int j = 0; j < m; i++) {
//                if (v.adjacencyList.get(j) == vertex.vertexId) {
//                    v.adjacencyList.remove(j);
//                }
//            }
//            v.adjacencyList.remove(v.adjacencyList.indexOf(vertex.vertexId));//WARNINGNGNNGNGNGNGSNGS RAT LAUUUUU
            }
        }
        ds_dinh.delete(vertex);
    }

    public float getDegree() {
        float degree = (float) so_canh / ds_dinh.countNodes();
        return degree;
    }

    public void addEdge(String type, int v1, int v2) {
        Vertex v;
        if ("directed".equals(type)) {
            //if there are not vertex v1, create new vertex before add edge
            if (ds_dinh.get(v1) == null) {
                v = new Vertex(v1);
                addVertex(v);
                ds_dinh.get(v1).adjacencyList.add(v2);
                ds_dinh.get(v1).deg++;

            } else {
                //if vertex v1 is existed, get v1 and add v2 to v1's adjacency list
                v = getVertex(v1);
                v.adjacencyList.add(v2);
                v.deg++;
                ds_dinh.update(v);
            }
            if (ds_dinh.get(v2) == null) {
                v = new Vertex(v2);
                addVertex(v);
            }
        } else if ("undirected".equals(type)) {
            addEdge("directed", v1, v2);
            addEdge("directed", v2, v1);
        }
    }

    public TreapTree createGraph(String file) throws FileNotFoundException, IOException {
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
        } //currentLine doc tu file, co dang "v1 v2"
//        addEdge("undirected", 1, 3);
//        so_canh++;
//        addEdge("undirected", 1, 2);
//        so_canh++;
//        addEdge("undirected", 1, 5);
//        so_canh++;
//        addEdge("undirected", 1, 4);
//        so_canh++;
//        addEdge("undirected", 2, 4);
//        so_canh++;
//        addEdge("undirected", 2, 5);
//        so_canh++;
//        addEdge("undirected", 7, 6);
//        so_canh++;
//        addEdge("undirected", 7, 8);
//        so_canh++;
//        addEdge("undirected", 7, 1);
//        so_canh++;
//        addEdge("undirected", 7, 4);
//        so_canh++;
//        displayGraph();

//so_dinh = ds_dinh.countNodes();
        return ds_dinh;
    }

}
