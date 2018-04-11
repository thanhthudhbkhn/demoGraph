/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demograph;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author thanhthu
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Graph g = new Graph();
        g.createGraph();
        g.displayGraph();
        System.out.println("Enter 2 vertices: ");
        Scanner scn = new Scanner(System.in);
        int v1 = scn.nextInt();
        int v2 = scn.nextInt();
        if (g.findPath(v1, v2) == false) {
            System.out.println("There is not any paths from " + v1 + " to " + v2 + ".");
        }
        System.out.println("");

        System.out.print("Breadth First Traversal from: ");
        int v = scn.nextInt();
        if (g.BFS(v) == false) {
            System.out.println("Breadth First Traversal failed.");
        }
        System.out.println("");

        System.out.print("Depth First Traversal from: ");
        v = scn.nextInt();
        if (g.DFS(v) == false) {
            System.out.println("Breadth First Traversal failed.");
        }
        System.out.println("");
    }
}
