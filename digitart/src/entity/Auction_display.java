/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import Services.Auction_Services;
import java.time.LocalDate;

/**
 *
 * @author fedi1
 */
public class Auction_display {

    LocalDate Date;
    String desc;
    int increment, starting_price;
    String name;

  
    int bid;
    String img ;
    String name_artist;

    int id_auction, id_artwork;

    public Auction_display(Auction auction, String name, int bid) {
        this.id_auction = auction.getId_auction();
        this.id_artwork = auction.getId_artwork();
        this.desc = auction.getDescription();
        this.Date = auction.getDate();
        this.starting_price = auction.getStarting_price();
        this.increment = auction.getIncrement();
        this.Date = auction.getDate();
        this.name = name;
        this.bid = bid;
        img = Auction_Services.get_img(id_artwork);
        name_artist = Auction_Services.find_artist_name(Auction_Services.find_artist_id(name));
    }
  public String getName_artist() {
        return name_artist;
    }

    public void setName_artist(String name_artist) {
        this.name_artist = name_artist;
    }
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Auction auc() {
        return new Auction(id_auction, starting_price, increment, id_artwork, Date, desc);
    }

    public int getId_auction() {
        return id_auction;
    }

    public void setId_auction(int id_auction) {
        this.id_auction = id_auction;
    }

    public int getId_artwork() {
        return id_artwork;
    }

    public void setId_artwork(int id_artwork) {
        this.id_artwork = id_artwork;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate Date) {
        this.Date = Date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getIncrement() {
        return increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }

    public int getStarting_price() {
        return starting_price;
    }

    public void setStarting_price(int starting_price) {
        this.starting_price = starting_price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

   @Override
public String toString() {
    return "Auction{" +
            "Date=" + Date +
            ", desc='" + desc + '\'' +
            ", increment=" + increment +
            ", starting_price=" + starting_price +
            ", name='" + name + '\'' +
            ", bid=" + bid +
            ", img='" + img + '\'' +
            ", name_artist='" + name_artist + '\'' +
            ", id_auction=" + id_auction +
            ", id_artwork=" + id_artwork +
            '}';
}

}
