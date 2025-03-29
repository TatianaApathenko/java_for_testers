package model;

public record ContactData(String firstname, String lastname, String address, String mobile,String work, String email) {
    public ContactData() {
        this("","","","","","");
    }

    public ContactData withName(String firstname) {
        return new ContactData(firstname, this.lastname, this.address, this.mobile, this.work,this.email);
    }

    public ContactData withLastName(String lastname) {
        return new ContactData(this.firstname, lastname, this.address, this.mobile,this.work, this.email);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.firstname, this.lastname, this.address, mobile,this.work, this.email);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.firstname, this.lastname, this.address,this.work, this.mobile, email);
    }
}