package ru.apatch.addressbook.tests;

import ru.apatch.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemoveTests extends TestBase{

    @Test
    public void CanRemoveContact() {
        if (app.contacts().getCount() == 0){
            app.contacts().CreateContact(new ContactData("","Irishka", "Mask", "Gdetotam", "+0999923321444", "irishka@mail.com",""));
        }
        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void CanRemoveAllContacts(){
        if (app.contacts().getCount() == 0){
            app.contacts().CreateContact(new ContactData("","Irishka", "Mask", "Gdetotam", "+0999923321444", "irishka@mail.com",""));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }
}

