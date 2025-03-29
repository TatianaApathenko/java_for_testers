package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {


    @Test
    public void CanCreationCon() {
        app.createContact(new ContactData("Irishka", "Mask", "Gdetotam", "+0999923321444", "horoshaya", "irishka.mail.ru"));
    }

    @Test
    public void CanCreationContact() {
        app.createContact(new ContactData("Dimka", "Sidorov", "nutam", "+0666666678874", "plohaya", "dimka.gmail.com"));
    }

    @Test
    public void CanCreationContactEmpty() {
        app.createContact(new ContactData());
    }

    @Test
    public void CanCreateContactWithNameOnly() {
        app.createContact(new ContactData().withFirstname("Tester"));
    }

    @Test
    public void CanCreateContactWithMobailOnly() {
        app.createContact(new ContactData().withMobile("+3242343"));
    }


}
