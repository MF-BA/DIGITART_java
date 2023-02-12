/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitart;

/**
 *
 * @author mohamed
 */
public class Room {
    private int id_room;
    private String name_room;
    private int area;
    private String state;
    private String description;

    
    
    public Room(int id_room, String name_room, int area, String state, String description) {
        this.id_room = id_room;
        this.name_room = name_room;
        this.area = area;
        this.state = state;
        this.description = description;
    }

    public Room(String name_room, int area, String state, String description) {
        this.name_room = name_room;
        this.area = area;
        this.state = state;
        this.description = description;
    }

    
    
    public int getId_room() {
        return id_room;
    }

    public String getName_room() {
        return name_room;
    }

    public int getArea() {
        return area;
    }

    public String getState() {
        return state;
    }

    public String getDescription() {
        return description;
    }

    
    
    
    public void setId_room(int id_room) {
        this.id_room = id_room;
    }

    public void setName_room(String name_room) {
        this.name_room = name_room;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
}
