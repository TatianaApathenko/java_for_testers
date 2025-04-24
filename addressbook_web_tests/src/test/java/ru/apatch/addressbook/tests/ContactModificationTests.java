package ru.apatch.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.apatch.addressbook.common.CommonFunctions;
import ru.apatch.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class ContactModificationTests extends TestBase {

    @Test
    void canModifyContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().CreateContact(new ContactData("1", "Luda", "Ed", "LA", "+57687686", "luda@mail.com", "", "", "", "", "", "", ""));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData().withName(CommonFunctions.randomString(12));
        app.contacts().modifyContact(oldContacts.get(index), testData);
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()));
        Assertions.assertEquals(Set.copyOf(newContacts), Set.copyOf(expectedList));
    }
}