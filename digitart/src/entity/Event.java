/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author kossay
 */
public class Event {
     private int event_id;
    private Date start_date;
    private Date end_date;
    private int start_time;
    private String event_name;
    private String detail;
    private int nb_participants;
    private int id_room;
    private String image;
    
    public Event(int event_id,Date start_date,Date end_date,int start_time,String event_name,String detail,int nb_participants) {
        this.event_id = event_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.start_time = start_time;
        this.event_name = event_name;
        this.detail = detail;
        this.nb_participants=nb_participants;
        
}
         public Event(int event_id,Date start_date,Date end_date,int start_time,String event_name,String detail,int nb_participants,int id_room) {
        this.event_id = event_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.start_time = start_time;
        this.event_name = event_name;
        this.detail = detail;
        this.nb_participants=nb_participants;
        this.id_room=id_room;
        
}
           public Event(int event_id,Date start_date,Date end_date,int start_time,String event_name,String detail,int nb_participants,int id_room,String image) {
        this.event_id = event_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.start_time = start_time;
        this.event_name = event_name;
        this.detail = detail;
        this.nb_participants=nb_participants;
        this.id_room=id_room;
        this.image=image;
}

    

    public Event(Date start_date, Date end_date, int start_time, String event_name, String detail, int nb_participants) {
      
        this.start_date = start_date;
        this.end_date = end_date;
        this.start_time = start_time;
        this.event_name = event_name;
        this.detail = detail;
        this.nb_participants=nb_participants;
    }
    public Event(Date start_date, Date end_date, int start_time, String event_name, String detail, int nb_participants,String image) {
      
        this.start_date = start_date;
        this.end_date = end_date;
        this.start_time = start_time;
        this.event_name = event_name;
        this.detail = detail;
        this.nb_participants=nb_participants;
        this.image=image;
    }

    public int getEvent_id() {
        return event_id;
    }

    public String getImage() {
        String url = "http://127.0.0.1:8000/uploads/"+this.image;
        return url;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getStart_date() {
        return start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public int getStart_time() {
        return start_time;
    }
    
     public int getId_room() {
        return id_room;
    }
     
      public void setId_room(int id_room) {
        this.id_room = id_room;
    }


    public String getEvent_name() {
        return event_name;
    }

    public String getDetail() {
        return detail;
    }

    public int getNb_participants() {
        return nb_participants;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setNb_participants(int nb_participants) {
        this.nb_participants = nb_participants;
    }

    @Override
    public String toString() {
        return "Event{" + "event_id=" + event_id + ", start_date=" + start_date + ", end_date=" + end_date + ", start_time=" + start_time + ", event_name=" + event_name + ", detail=" + detail + ", nb_participants=" + nb_participants + '}';
    }
    
}
