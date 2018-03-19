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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        List<Integer> list[] = new  LinkedList[500];
        
        String path = "/home/thanhthu/NetBeansProjects/demoGraph/src";
        BufferedReader in = new BufferedReader(new FileReader(path+"/demograph/data.txt"));
//        BufferedReader in = new BufferedReader(new FileReader(path+"/facebook/facebook_combined.txt"));
        
        for(int i=0; i<=10; i++) {
            list[i] = new LinkedList<>();
        }

        int m = 0;
        String currentLine = in.readLine();
        while(currentLine != null){
            m++;
            
            if (!"".equals(currentLine)) {
                String[] vertes = currentLine.split(" ");            
                int v1 = Integer.parseInt(vertes[0]);
                int v2 = Integer.parseInt(vertes[1]);
                list[v1].add(v2);
                list[v2].add(v1);
            }
            currentLine = in.readLine();
        }
        
        for (int i = 0; i <=10; i++) {
            System.out.println(i+" - "+list[i]);
        }
    }
    
}
