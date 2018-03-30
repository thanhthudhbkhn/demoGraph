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
        DemoGraph g = new DemoGraph();
        g.createGraph();
        System.out.print("Breadth First Traversal from: ");
        Scanner scn = new Scanner(System.in);
        int v = scn.nextInt();
        g.BFS(v);
        
        System.out.println("");
        System.out.print("Depth First Traversal from: ");
        v = scn.nextInt();
        g.DFS(v);
        System.out.println("");
    }
}
