package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase{

    @Test
    void canModifyContact(){
        if (app.contacts().getCount() == 0){
            app.contacts().CreateContact(new ContactData("1", "Irishka", "Mask", "Gdetotam", "+0999923321444", "irishka@mail.com",""));
        }
        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var modifiedContact = new ContactData()
                .withId(oldContacts.get(index).id())
                .withName("Luda")
                .withLastName(oldContacts.get(index).lastname())
                .withAddress(oldContacts.get(index).address())
                .withMobile(oldContacts.get(index).mobile())
                .withEmail(oldContacts.get(index).email());

        app.contacts().modifyContact(oldContacts.get(index), modifiedContact);

        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, modifiedContact);

        Comparator<ContactData> compareById = (o1, o2) -> Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        newContacts.sort(compareById);
        expectedList.sort(compareById);

        Assertions.assertEquals(newContacts, expectedList);
    }
}