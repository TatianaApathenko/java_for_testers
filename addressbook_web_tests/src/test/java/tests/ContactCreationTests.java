package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {



    @Test
    public void CanCreationCon() {
        app.CreateContact(new ContactData("Irishka", "Mask", "Gdetotam", "+0999923321444", "horoshaya", "irishka.mail.ru"));
    }

    @Test
    public void CanCreationContact() {
        app.CreateContact(new ContactData("Dimka", "Sidorov", "nutam", "+0666666678874", "plohaya", "dimka.gmail.com"));
    }

    @Test
    public void CanCreationContactEmpty() {
        app.CreateContact(new ContactData());
    }

    @Test
    public void CanCreateContactWithNameOnly() {
        app.CreateContact(new ContactData().withName("Tester"));
    }

    @Test
    public void CanCreateContactWithMobailOnly() {
        app.CreateContact(new ContactData().withMobile("+3242343"));
    }

    @Test
    public void CanCreateContactWithEmailOnly() {
        app.CreateContact(new ContactData().withEmail("asas@asd.ru"));
    }

    @Test
    public void CanCreateContactWithLastNameOnly() {
        app.CreateContact(new ContactData().withLastName("Valuev"));
    }
}
