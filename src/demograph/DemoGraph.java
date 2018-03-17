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
        LinkedList list = new  LinkedList();
        
        // add some elements
        list.add("Hello");
        list.add(2);
        list.add("Chocolate");
        list.add("10");

        // print the list
        System.out.println("LinkedList:" + list);

        // add a new element at the end of the list
        list.add(2,"Element");

        // print the updated list
        System.out.println("LinkedList:" + list);
    }
    
}
