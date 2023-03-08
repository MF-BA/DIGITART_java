/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import Services.Artwork_Services;
import java.time.*;
/**
 *
 * @author mohamed
 */
public class Artwork {
    private int id_art;
    private String artwork_name;
    private int id_artist;
    private String artist_name;
    private LocalDate date_art;
    private String description;
    private String image_art;
    private int id_room;

    public Artwork(int id_art, String artwork_name, int id_artist, String artist_name, LocalDate date_art, String description, String image_art, int id_room) {
        this.id_art = id_art;
        this.artwork_name = artwork_name;
        this.id_artist = id_artist;
        this.artist_name = artist_name;
        this.date_art = date_art;
        this.description = description;
        this.image_art = image_art;
        this.id_room = id_room;
    }

      @Override
public String toString() {
    return String.format("%s, %s, %s, %s, %s", 
            artwork_name, artist_name, date_art, description, id_room);
}

    public Artwork(String artwork_name, int id_artist, String artist_name, LocalDate date_art, String description, String image_art, int id_room) {
        this.artwork_name = artwork_name;
        this.id_artist = id_artist;
        this.artist_name = artist_name;
        this.date_art = date_art;
        this.description = description;
        this.image_art = image_art;
        this.id_room = id_room;
    }

    public int getId_art() {
        return id_art;
    }

    public String getArtwork_name() {
        return artwork_name;
    }

    public int getId_artist() {
        return id_artist;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public LocalDate getDate_art() {
        return date_art;
    }

    public String getDescription() {
        return description;
    }

    public String getImage_art() {
        return image_art;
    }

    public int getId_room() {
        return id_room;
    }

    public void setId_art(int id_art) {
        this.id_art = id_art;
    }

    public void setArtwork_name(String artwork_name) {
        this.artwork_name = artwork_name;
    }

    public void setId_artist(int id_artist) {
        this.id_artist = id_artist;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public void setDate_art(LocalDate date_art) {
        this.date_art = date_art;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage_art(String image_art) {
        this.image_art = image_art;
    }

    public void setId_room(int id_room) {
        this.id_room = id_room;
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
