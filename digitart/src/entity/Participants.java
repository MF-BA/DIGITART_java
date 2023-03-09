/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author kossay
 */
public class Participants {
    
    private String first_name;
    private String last_name;
    private int id_user;
    private int id_event;
    private String adress;
    private String gender;

    public Participants() {
    }

    public Participants(String first_name, String last_name, int id_user, int id_event, String adress, String gender) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.id_user = id_user;
        this.id_event = id_event;
        this.adress = adress;
        this.gender = gender;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_event() {
        return id_event;
    }

    public String getAdress() {
        return adress;
    }

    public String getGender() {
        return gender;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Participants{" + "first_name=" + first_name + ", last_name=" + last_name + ", id_user=" + id_user + ", id_event=" + id_event + ", adress=" + adress + ", gender=" + gender + '}';
    }
    
    
    
}
