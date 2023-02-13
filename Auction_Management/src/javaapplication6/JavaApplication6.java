/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import java.time.*;
import java.util.ArrayList;

/**
 *
 * @author fedi1
 */
public class JavaApplication6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*
        Add to table 
         
        Auction auction1 = new Auction(100, 10, 1, LocalDate.of(2023, Month.MARCH, 10), "new edition");
        Auction_Services.add(auction1);
         */
        //display table
        ArrayList<Auction> data;
        data = Auction_Services.Display();
        System.out.println(data);

        /*
        Delete and display table
         
        Auction auction2 = data.get(0);
        Auction_Services.delete(auction2.getId_auction());
        data = Auction_Services.Display();
        System.out.println(data);
         */

 /*
        Modify and display table
        
        Auction auction3 = new Auction(6, 500, 50, 1, LocalDate.of(2023, Month.FEBRUARY, 15), "new edition");
        Auction_Services.modify(auction3);

        data = Auction_Services.Display();
        System.out.println(data);
         */
    }

}
