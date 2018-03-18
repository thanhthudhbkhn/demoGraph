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

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        //khoi tao mang chua 10 list, moi list 500 ptu
        //moi list la danh sach dinh ke cua dinh co verteId = listId
        List<Integer> list[] = new  LinkedList[500];
        for(int i=0; i<=10; i++) {
            list[i] = new LinkedList<>();
        }        
        
        //khai bao path de thao tac voi file
        String path = "/home/thanhthu/NetBeansProjects/demoGraph/src";
        BufferedReader in = new BufferedReader(new FileReader(path+"/demograph/data.txt"));
//        BufferedReader in = new BufferedReader(new FileReader(path+"/facebook/facebook_combined.txt"));
        
        int m = 0; //so edge cua graph
        String currentLine = in.readLine(); //currentLine doc tu file, co dang "v1 v2"
        while(currentLine != null){
            m++;            
            if (!"".equals(currentLine)) {
                //get 2 vertes from an edge
                String[] vertes = currentLine.split(" ");
                int v1 = Integer.parseInt(vertes[0]);
                int v2 = Integer.parseInt(vertes[1]);
                //add 2 vertes into lists
                list[v1].add(v2);
                list[v2].add(v1);
            }
            currentLine = in.readLine();
        }
        
        //display the lists to check
        for (int i = 0; i <=10; i++) {
            System.out.println(i+" - "+list[i]);
        }
    }
    
}
