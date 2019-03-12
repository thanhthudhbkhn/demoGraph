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

	private static void loadGraph(String file) throws IOException {
		Graph g = new Graph();
		String filename = file.split("/")[2];
		String graphname = filename.split(".txt")[0];

		long start = System.nanoTime();
		g.createGraph(file);
		float runTime = (System.nanoTime() - start) / 1000000f;

		System.out.println("\nIt takes " + runTime + "ms to load " + filename + " into graph.");
		System.out.println(graphname + ":");
		g.displayGraphInfo();
	}

	public static void main(String[] args) throws IOException {

//		loadGraph("/roadNet/roadNet-CA.txt");	
//		loadGraph("/roadNet/roadNet-TX.txt");	
		loadGraph("/roadNet/roadNet-PA.txt");	
	}
}
