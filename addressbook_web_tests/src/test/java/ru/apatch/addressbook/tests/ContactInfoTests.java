package ru.apatch.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.apatch.addressbook.model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().CreateContact(new ContactData("1", "Luda", "Ed", "LA", "+57687686", "luda@mail.com", "", "", "", "", "", "", ""));
        }
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);
    }

    @Test
    void testEmails() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().CreateContact(new ContactData("1", "Luda", "Ed", "LA", "+57687686", "luda@mail.com", "", "", "", "", "", "", ""));
        }
        var contacts = app.hbm().getContactList();
        var contact = contacts.getFirst();
        var emails = app.contacts().getEmails(contact);
        var expected = Stream.of(contact.email(), contact.email2(), contact.email3())
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, emails);
    }

    @Test
    void testAdresses() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().CreateContact(new ContactData("1", "Luda", "Ed", "LA", "+57687686", "luda@mail.com", "", "", "", "", "", "", ""));
        }
        var contacts = app.hbm().getContactList();
        var contact = contacts.getFirst();
        var addresses = app.contacts().getAddresses(contact);
        var expected = Stream.of(contact.address())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, addresses);
    }
}