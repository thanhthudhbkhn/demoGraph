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
        for(int i=0; i<5; i++) {
            list[i] = new LinkedList<>();
        }
        list[0].add(10);
        list[1].add(10); list[1].add(20);
        list[2].add(10);
        list[3].add(10);
        list[4].add(10);
        for (int i = 0; i < 5; i++) {
            System.out.println(i+" - "+list[i]);
        }
    }
    
}
