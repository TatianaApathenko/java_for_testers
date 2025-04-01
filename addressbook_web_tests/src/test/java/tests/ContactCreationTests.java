package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContactWithFullData() {
        app.contacts().createContact(new ContactData("Irishka", "Mask", "Gdetotam", "+0999923321444", "horoshaya", "irishka.mail.ru"));
    }

    @Test
    public void canCreateAnotherContact() {
        app.contacts().createContact(new ContactData("Dimka", "Sidorov", "nutam", "+0666666678874", "plohaya", "dimka.gmail.com"));
    }

    @Test
    public void canCreateContactWithEmpty() {
        app.contacts().createContact(new ContactData());
    }

    @Test
    public void canCreateContactWithOnlyName() {
        app.contacts().createContact(new ContactData().withFirstname("Tester"));
    }

    @Test
    public void canCreateContactWithOnlyMobile() {
        app.contacts().createContact(new ContactData().withMobile("+3242343"));
    }
}
