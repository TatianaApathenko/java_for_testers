package model;

public record ContactData(String id,String firstname, String lastname, String address, String mobile, String email) {
    public ContactData() {
        this("","", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstname, this.lastname, this.address, this.mobile, this.email);
    }
    public ContactData withName(String firstname) {
        return new ContactData(this.id,firstname, this.lastname, this.address, this.mobile, this.email);
    }

    public ContactData withLastName(String lastname) {
        return new ContactData(this.id,this.firstname, lastname, this.address, this.mobile, this.email);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id,this.firstname, this.lastname, address, this.mobile, this.email);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.id,this.firstname, this.lastname, this.address, mobile, this.email);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id,this.firstname, this.lastname, this.address, this.mobile, email);
    }
}