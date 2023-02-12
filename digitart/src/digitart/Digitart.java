/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitart;
import java.sql.*;
import java.time.*;
import java.util.ArrayList;
/**
 *
 * @author mohamed
 */
public class Digitart {
 /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        ArrayList<Room> data ;
        ArrayList<Artwork> data2 ;
        
        
        
        /**add and display room 
         * 
            
        */
          Room room1 = new Room("Carthage",260,"available","qdqzdqzd");
             Room_Services.add(room1);
        
    
             data = Room_Services.Display() ;
             System.out.println(data);
        /* delete and display room 
        
            Room room2 = data.get(0);
            Room_Services.delete(room2.getId_room());
        
            data = Room_Services.Display() ;
            System.out.println(data);
        */
        
       
        /* add  and display artwork
        
          Artwork artwork1 = new Artwork("La Jaconde",1,"Leonard de Vinci",LocalDate.of(1517, Month.MARCH, 10),"qdqzdqzd","url",2);
             Artwork_Services.add(artwork1);
        
        
             data2 = Artwork_Services.Display() ;
             System.out.println(data2);
             
        */
        
      
        
        /* delete and display artwork 
        
            Artwork artwork2 = data2.get(0);
            Artwork_Services.delete(artwork2.getId_art());
        
            data2 = Artwork_Services.Display() ;
            System.out.println(data2);
   
        */
        
       
    }
    
}
