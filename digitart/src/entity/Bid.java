/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import Services.Auction_Services;
import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 *
 * @author fedi1
 */
public class Bid {

    int id_bid, id_user, id_auction, offer;
    ZonedDateTime date;

    public Bid(int id_bid, int id_user, int id_auction, int offer, ZonedDateTime date) {
        this.id_bid = id_bid;
        this.id_user = id_user;
        this.id_auction = id_auction;
        this.offer = offer;
        this.date = date;
    }

    public Bid(int id_bid, int id_user, int id_auction, int offer) {
        this.id_bid = id_bid;
        this.id_user = id_user;
        this.id_auction = id_auction;
        this.offer = offer;
        this.date = ZonedDateTime.now();
    }

    public Bid(int id_user, int id_auction, int offer) {
        this.id_user = id_user;
        this.id_auction = id_auction;
        this.offer = offer;
        this.date = ZonedDateTime.now();
    }

    public Bid(int aInt, LocalDate D, int aInt0, int aInt1, LocalDate D0, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_bid() {
        return id_bid;
    }

    public void setId_auction(int id_auction) {
        this.id_auction = id_auction;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_auction() {
        return id_auction;
    }

    public int getOffer() {
        return offer;
    }

    public void setOffer(int offer) {
        this.offer = offer;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.valueOf(offer) +", " + date +", " + Auction_Services.find_artist_name(id_user);
    }

}
