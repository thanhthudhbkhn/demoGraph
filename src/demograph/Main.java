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

	static void BFSrun(Graph g, Vertex node) {
		if (node != null) {
			if (g.BFS(node.vertexId) == false) {
				System.out.println("Breadth First Traversal with vertext " + node.vertexId + " failed");
			}
			BFSrun(g, node.left);
			BFSrun(g, node.right);
		}
	}

	static void DFSrun(Graph g, Vertex node) {
		if (node != null) {
//			if (g.DFS(node.vertexId) == false) {
//				System.out.println("Deepth First Traversal with vertext " + node.vertexId + " failed");
//			}
//			DFSrun(g, node.left);
//			DFSrun(g, node.right);
		}
	}

	public static void main(String[] args) throws IOException {
		Graph g = new Graph();
		g.createGraph();

		int choice;
		do {
			System.out.println("-----------------------------------------");
			System.out.println("Press 1 to display graph info.");
			System.out.println("Press 2 find path between 2 vertices.");
			System.out.println("Press 3 to start Breadth First Traversal.");
			System.out.println("Press 4 to start Deepth First Traversal.");
			System.out.println("-----------------------------------------");

			Scanner scn = new Scanner(System.in);
			choice = scn.nextInt();

			switch (choice) {
				case 1:
					g.displayGraphInfo();
					break;
				case 2:
					System.out.println("Enter 2 vertices: ");
					int v1 = scn.nextInt();
					int v2 = scn.nextInt();
					long start = System.nanoTime();
//					if (g.findPath(v1, v2) == false) {
//						System.out.println("There is not any paths from " + v1 + " to " + v2 + ".");
//					}
					float runTime = (System.nanoTime() - start) / 1000000f;
					System.out.println("\nIt takes " + runTime + "ms to find path.");
					break;
				case 3:
					start = System.nanoTime();
					BFSrun(g, g.ds_dinh.root);
					runTime = (System.nanoTime() - start) / 1000000f;
					System.out.println("It takes " + runTime + "ms to run BFS.");
					break;
				case 4:
					start = System.nanoTime();
					DFSrun(g, g.ds_dinh.root);
					runTime = (System.nanoTime() - start) / 1000000f;
					System.out.println("It takes " + runTime + "ms to run DFS.");
					break;
				default:
					break;
			}
		} while (choice == 1 || choice == 2 || choice == 3 || choice == 4);
	}
}
