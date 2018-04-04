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

public class DemoGraph {
    static List<Integer> list[] = new  LinkedList[5000];
    int m = 0; //so edge cua graph
    int n = 0; //vertexId max cua graph
    
    public void BFS(int vertexId) {
        // Mark all the vertices as not visited(By default
        // set as false)
        boolean visited[] = new boolean[5000];
 
        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<>();
 
        // Mark the current node as visited and enqueue it
        visited[vertexId]=true;
        queue.add(vertexId);
 
        while (!queue.isEmpty())
        {
            // Dequeue a vertex from queue and print it
            vertexId = queue.poll();
            System.out.print(vertexId+" ");

            // Get all adjacent vertices of the dequeued vertex 
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it            
            int itemCount = list[vertexId].size();
            for (int i = 0; i < itemCount; i++) {
                int adjacentVertex = list[vertexId].get(i);
                if (visited[adjacentVertex]==false) {
                    visited[adjacentVertex]=true;
                    queue.add(adjacentVertex);
                }
            }
        }
    }
    
    public void DFSFunction(int vertexId, boolean visited[]) {
        // Mark the current node as visited and enqueue it
        visited[vertexId]=true; 
        System.out.print(vertexId+" ");

        // Get all adjacent vertices of the vertex 
        // If a adjacent has not been visited, visit it with DFS
        int itemCount = list[vertexId].size();
        for (int i = 0; i < itemCount; i++) {
            int adjacentVertex = list[vertexId].get(i);
            if (visited[adjacentVertex]==false) {
                DFSFunction(adjacentVertex,visited);
            }
        }
    }
    
    public void DFS(int vertexId) {
        // Mark all the vertices as not visited(By default
        // set as false)
        boolean visited[] = new boolean[5000];
        DFSFunction(vertexId, visited);        
    }
    
    
    public void displayGraph(){
        for (int i = 0; i <=n; i++) {
            System.out.println(i+" - "+list[i]);
        }
    }
    
    public List<Integer>[] createGraph() throws FileNotFoundException, IOException {
        //khoi tao mang chua 10 list
        //moi list la danh sach dinh ke cua dinh co vertexId = listId
        
        for(int i=0; i<5000; i++) {
            list[i] = new LinkedList<>();
        }        
        //khai bao path de thao tac voi file
        String path = "/home/thanhthu/NetBeansProjects/demoGraph/src";
        BufferedReader in = new BufferedReader(new FileReader(path+"/demograph/data.txt"));
//        BufferedReader in = new BufferedReader(new FileReader(path+"/facebook/facebook_combined.txt"));
        
        String currentLine = in.readLine(); //currentLine doc tu file, co dang "v1 v2"
        while(currentLine != null){
            m++;            
            if (!"".equals(currentLine)) {
                //get 2 vertexs from an edge
                String[] vertices = currentLine.split(" ");
                int v1 = Integer.parseInt(vertices[0]);
                int v2 = Integer.parseInt(vertices[1]);
                //get max vertexId
                n = (n>v1)? n:v1;
                n = (n>v2)? n:v2;
                //add 2 vertices into lists
                list[v1].add(v2);
                list[v2].add(v1);
            }
            currentLine = in.readLine();
        }
        in.close();
        return list;
    }
    
    public boolean findPath (int vertex1, int vertex2) {
        //if 2 vertices are the same, return true
        if (vertex1 == vertex2 && vertex1<=n) return true;
        // Mark all the vertices as not visited(By default
        // set as false)
        boolean visited[] = new boolean[5000];
        
        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<>();
 
        // Mark the current node as visited and enqueue it
        visited[vertex1]=true;
        queue.add(vertex1);
 
        while (!queue.isEmpty())
        {
            // Dequeue a vertex from queue and print it
            vertex1 = queue.poll();
            //System.out.print(vertex1+" ");

            // Get all adjacent vertices of the dequeued vertex 
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it            
            int itemCount = list[vertex1].size();
            for (int i = 0; i < itemCount; i++) {
                int adjacentVertex = list[vertex1].get(i);
                if (visited[adjacentVertex]==false) {
                    visited[adjacentVertex]=true;
                    //If this vertex is the destination return true
                    if (adjacentVertex == vertex2) return true;
                    //Else continue BFS
                    queue.add(adjacentVertex);
                }
            }
        }
        return false;
    }
}
