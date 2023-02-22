/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.time.LocalDate;

/**
 *
 * @author User
 */
public class Payment {

    private int paymentid;
    private int userid;
    private LocalDate purchaseDate;
    private int nbAdult;
    private int nbTeenager;
    private int nbStudent;
    private int totalPayment;

    public Payment(int paymentid, int userid, LocalDate purchaseDate, int nbAdult, int nbTeenager, int nbStudent, int totalPayment) {
        this.paymentid = paymentid;
        this.userid = userid;
        this.purchaseDate = purchaseDate;
        this.nbAdult = nbAdult;
        this.nbTeenager = nbTeenager;
        this.nbStudent = nbStudent;
        this.totalPayment = totalPayment;
    }

    public Payment(int userid, LocalDate purchaseDate, int nbAdult, int nbTeenager, int nbStudent, int totalPayment) {
        this.userid = 0;
        this.purchaseDate = purchaseDate;
        this.nbAdult = nbAdult;
        this.nbTeenager = nbTeenager;
        this.nbStudent = nbStudent;
        this.totalPayment = totalPayment;
    }

    public int getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(int paymentid) {
        this.paymentid = paymentid;
    }

    public int getId() {
        return userid;
    }

    public void setId(int id) {
        this.userid = id;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getNbAdult() {
        return nbAdult;
    }

    public void setNbAdult(int nbAdult) {
        this.nbAdult = nbAdult;
    }

    public int getNbTeenager() {
        return nbTeenager;
    }

    public void setNbTeenager(int nbTeenager) {
        this.nbTeenager = nbTeenager;
    }

    public int getNbStudent() {
        return nbStudent;
    }

    public void setNbStudent(int nbStudent) {
        this.nbStudent = nbStudent;
    }

    public int getTotalPayment() {
        return (int) Math.round(this.totalPayment);
    }

    public void setTotalPayment(int totalPayment) {
        this.totalPayment = totalPayment;
    }
}
