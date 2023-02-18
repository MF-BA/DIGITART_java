/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author Islem
 */
public class users {
    
   String firstname,lastname,email,pwd,address,gender,role;
   int id,cin,phone_number;
   Date birth_date;

    public users(String firstname, String lastname, String email, String address, String gender, int id, int cin, int phone_number, Date birth_date) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.id = id;
        this.cin = cin;
        this.phone_number = phone_number;
        this.birth_date = birth_date;
    }
    
    public users(String firstname, String lastname, String email, String pwd, String address, String gender, int id, int cin, int phone_number, Date birth_date) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.pwd = pwd;
        this.address = address;
        this.gender = gender;
        this.id = id;
        this.cin = cin;
        this.phone_number = phone_number;
        this.birth_date = birth_date;
    }

    public users(String firstname, String lastname, String email, String pwd, String address, String gender, String role, int id, int cin, int phone_number, Date birth_date) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.pwd = pwd;
        this.address = address;
        this.gender = gender;
        this.role = role;
        this.id = id;
        this.cin = cin;
        this.phone_number = phone_number;
        this.birth_date = birth_date;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }
   
   
    
    
}
