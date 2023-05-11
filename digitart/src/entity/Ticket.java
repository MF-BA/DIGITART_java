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
import java.time.LocalDate;
import java.util.Date;


public class Ticket {
    private int ticket_id;
    private Date ticket_date;
    private Date ticket_edate;
    private int price;
    private String ticket_type;
    private boolean paid;

    public Ticket(int ticket_id, Date ticket_date, Date ticket_edate, int price, String ticket_type) {
        this.ticket_id = ticket_id;
        this.ticket_date = ticket_date;
        this.ticket_edate = ticket_edate;
        this.price = price;
        this.ticket_type = ticket_type;
    }
    
    public Ticket(Date date, Date edate, int price, String type) {
    this.ticket_date = date;
    this.ticket_edate = edate;
    this.price = price;
    this.ticket_type = type;
}



    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public Date getTicket_date() {
        return ticket_date;
    }

    public void setTicket_date(Date ticket_date) {
        this.ticket_date = ticket_date;
    }

    public Date getTicket_edate() {
        return ticket_edate;
    }

    public void setTicket_edate(Date ticket_edate) {
        this.ticket_edate = ticket_edate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public String getTicket_type() {
        return ticket_type;
    }

    public void setTicket_type(String ticket_type) {
        this.ticket_type = ticket_type;
    }
    
    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticket_id=" + ticket_id +
                ", ticket_date=" + ticket_date +
                ", ticket_edate=" + ticket_edate +
                ", price=" + price +
                ", ticket_type='" + ticket_type + '\'' +
                '}';
    }
}
