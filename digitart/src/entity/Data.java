/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author mohamed
 */
public class Data {
  
   public static Room room;
   public static Artwork artwork;

    public static Room getRoom() {
        return room;
    }

    public static Artwork getArtwork() {
        return artwork;
    }

    public static void setRoom(Room room) {
        Data.room = room;
    }

    public static void setArtwork(Artwork artwork) {
        Data.artwork = artwork;
    }
    
}
