package ru.apatch.addressbook.manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;


@Entity
@Table(name = "addressbook")
public class ContactRecord {

    @Id
    @Column(name = "id")
    public int id;

    @Column(name = "firstname")
    public String firstname;

    @Column(name = "lastname")
    public String lastname;

    @Column(name = "address")
    public String address;

    @Column(name = "mobile")
    public String mobile;

    @Column(name = "email")
    public String email;

    public String middlename = "";
    public String nickname = "";
    public String company = "";
    public String title = "";
    public String work = "";
    public String home = "";
    public String fax = "";
    public String email1 = "";
    public String email2 = "";
    public String inm = "";
    public String inm2 = "";
    public String inm3 = "";
    public String homepage = "";
    public int birday = 18;
    public int bmonth = 12;
    public int year = 1992;
    public int aday = 1;
    public int amonth = 3;
    public int ayear = 12;
    public String address2 = "";
    public String phone2 = "";
    public String notes = "";
    public String photo = "";
    public String x_vcard = "";
    public String x_activesync = "";
    public Date created = new Date();
    public Date modified = new Date();
    public String role = "";
    public String addr_long = "";
    public String addr_lat = "";
    public String addr_status = "";

    public ContactRecord() {
    }

    public ContactRecord(int id, String firstname, String lastname, String address, String mobile, String email) {

        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
    }
}