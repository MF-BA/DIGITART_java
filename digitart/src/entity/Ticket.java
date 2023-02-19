package entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
import java.util.Date;

public class Ticket {
    private int ticket_id;
    private Date ticket_date;
    private String ticket_type;
    private int price;
    private int ticket_nb;
    private String ticket_desc;

    public Ticket(int ticket_id, Date ticket_date, String ticket_type, int price, int ticket_nb , String ticket_desc) {
        this.ticket_id = ticket_id;
        this.ticket_date = ticket_date;
        this.ticket_type = ticket_type;
        this.price = price;
        this.ticket_nb = ticket_nb;
        this.ticket_desc = ticket_desc;
    }

    //get
    public int getTicket_id() {
        return ticket_id;
    }
    public Date getTicket_date() {
        return ticket_date;
    }
     
    public String getTicket_type() {
        return ticket_type;
    }
    
    public int getPrice() {
        return price;
    }
    
     public int getTicket_nb() {
        return ticket_nb;
    }
     
    public String getTicket_desc() {
        return ticket_desc;
    }
    
    
    //set
    
    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public void setTicket_date(Date ticket_date) {
        this.ticket_date = ticket_date;
    }

    public void setTicket_type(String ticket_type) {
        this.ticket_type = ticket_type;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTicket_nb(int ticket_nb) {
        this.ticket_nb = ticket_nb;
    }

    public void setTicket_desc(String ticket_desc) {
        this.ticket_desc = ticket_desc;
    }

    
    @Override
    public String toString() {
        return "Ticket{" +
                "ticket_id=" + ticket_id +
                ", ticket_date=" + ticket_date +
                ", ticket_type='" + ticket_type + '\'' +
                ", price=" + price +
                ", ticket_nb=" + ticket_nb +
                ", ticket_desc='" + ticket_desc + '\'' +
                '}';
    }
}
