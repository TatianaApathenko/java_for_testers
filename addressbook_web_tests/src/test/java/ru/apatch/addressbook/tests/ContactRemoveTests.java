package ru.apatch.addressbook.tests;

import ru.apatch.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactRemoveTests extends TestBase {


    @Test
    public void CanRemoveContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().CreateContact(new ContactData("", "Irishka", "Mask", "Gdetotam", "+0999923321444", "irishka@mail.com", ""));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void CanRemoveAllContacts() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().CreateContact(new ContactData
                    ("", "Irishka", "Mask", "Gdetotam", "+0999923321444", "irishka@mail.com", ""));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }
}