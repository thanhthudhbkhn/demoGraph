/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demograph;
import java.util.*;
/**
 *
 * @author ThuPT
 */

public class DemoGraph {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        List<Integer> list[] = new  LinkedList[500];
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of vertices: ");
        int n = scanner.nextInt();
        for(int i=0; i<=n; i++) {
            list[i] = new LinkedList<>();
        }        
        System.out.println("Enter number of edges: ");
        int m = scanner.nextInt();
        
        scanner.nextLine();
        for(int i=0;i<m;i++) {
            System.out.println("Enter the edge: ");
            String edge = scanner.nextLine();
            if (!"".equals(edge)) {
                String[] vertes = edge.split(" ");
            
                int v1 = Integer.parseInt(vertes[0]);
                int v2 = Integer.parseInt(vertes[1]);
                list[v1].add(v2);
                list[v2].add(v1);
            }
        }
        
        for (int i = 0; i <=n; i++) {
            System.out.println(i+" - "+list[i]);
        }
    }
    
}
