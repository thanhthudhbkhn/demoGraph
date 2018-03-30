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
    int n = 0; //verteId max cua graph
    
    public void BFS(int verteId) {
        // Mark all the vertices as not visited(By default
        // set as false)
        boolean visited[] = new boolean[5000];
 
        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<>();
 
        // Mark the current node as visited and enqueue it
        visited[verteId]=true;
        queue.add(verteId);
 
        while (!queue.isEmpty())
        {
            // Dequeue a vertex from queue and print it
            verteId = queue.poll();
            System.out.print(verteId+" ");

            // Get all adjacent vertices of the dequeued vertex 
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it            
            int itemCount = list[verteId].size();
            for (int i = 0; i < itemCount; i++) {
                int adjacentVerte = list[verteId].get(i);
                if (visited[adjacentVerte]==false) {
                    visited[adjacentVerte]=true;
                    queue.add(adjacentVerte);
                }
            }
        }
    }
    
    public void DFSFunction(int verteId, boolean visited[]) {
        // Mark the current node as visited and enqueue it
        visited[verteId]=true; 
        System.out.print(verteId+" ");

        // Get all adjacent vertices of the vertex 
        // If a adjacent has not been visited, visit it with DFS
        int itemCount = list[verteId].size();
        for (int i = 0; i < itemCount; i++) {
            int adjacentVerte = list[verteId].get(i);
            if (visited[adjacentVerte]==false) {
                DFSFunction(adjacentVerte,visited);
            }
        }
    }
    
    public void DFS(int verteId) {
        // Mark all the vertices as not visited(By default
        // set as false)
        boolean visited[] = new boolean[5000];
        DFSFunction(verteId, visited);        
    }
    
    
    public void displayGraph(){
        for (int i = 0; i <=n; i++) {
            System.out.println(i+" - "+list[i]);
        }
    }
    
    public List<Integer>[] createGraph() throws FileNotFoundException, IOException {
        //khoi tao mang chua 10 list
        //moi list la danh sach dinh ke cua dinh co verteId = listId
        
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
                //get 2 vertes from an edge
                String[] vertes = currentLine.split(" ");
                int v1 = Integer.parseInt(vertes[0]);
                int v2 = Integer.parseInt(vertes[1]);
                //get max verteId
                n = (n>v1)? n:v1;
                n = (n>v2)? n:v2;
                //add 2 vertes into lists
                list[v1].add(v2);
                list[v2].add(v1);
            }
            currentLine = in.readLine();
        }
        in.close();
        return list;
    }
    
}
