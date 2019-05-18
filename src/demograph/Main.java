/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demograph;

import java.io.IOException;

/**
 *
 * @author thanhthu
 */
public class Main {

    private static Graph loadGraph(String file) throws IOException {
        Graph g = new Graph();
        String filename = file.split("/")[2];
        String graphname = filename.split(".txt")[0];

        long start = System.nanoTime();
        g.createGraph(file);
        float runTime = (System.nanoTime() - start) / 1000000f;

        System.out.println("\nIt takes " + runTime + "ms to load " + filename + " into graph.");
        System.out.println(graphname + ":");
        g.displayGraphInfo();

        return g;
    }

    private static void findHighestDegree(Graph g) {
        long start = System.nanoTime();
        float max = 0;
        for (int i = 0; i < g.ds_dinh.countNodes() / 10000; i++) {
            g.deleteVertex(g.ds_dinh.root);
            float newdeg = g.getDegree();
            System.out.println(g.ds_dinh.countNodes() + " " + newdeg);
            if (max < newdeg) {
                max = newdeg;
            }
        }

        float runTime = (System.nanoTime() - start) / 1000000f;
        System.out.println(max);
        System.out.println("\nIt takes " + runTime + "ms to find highest degree of graph.");
    }

    public static void main(String[] args) throws IOException {

//		loadGraph("/roadNet/roadNet-CA.txt");
//		loadGraph("/roadNet/roadNet-TX.txt");
        Graph g = loadGraph("/roadNet/roadNet-PA.txt");
        findHighestDegree(g);

    }
}
