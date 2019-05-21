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
        int get = 0;
        int originalSize = g.ds_dinh.countNodes();
        for (int i = 0; i < originalSize; i++) {
            float newDen = g.getDensity();
            if (i>5700 && i<6050) System.out.println(g.ds_dinh.countNodes() + " " + newDen);
            if (max < newDen) {
                max = newDen;
                get = g.ds_dinh.countNodes();
            }
            g.deleteVertex(g.ds_dinh.root);
        }

        float runTime = (System.nanoTime() - start) / 1000000f;
        System.out.println("Highest degree: " + max + " " + get);
        System.out.println("It takes " + runTime + "ms to find highest density of graph.");
    }

    public static void main(String[] args) throws IOException {

//		loadGraph("/roadNet/roadNet-CA.txt");
//		loadGraph("/roadNet/roadNet-TX.txt");
        Graph g = loadGraph("/roadNet/roadNet-PA.txt");
//        findHighestDegree(g);

    }
}
