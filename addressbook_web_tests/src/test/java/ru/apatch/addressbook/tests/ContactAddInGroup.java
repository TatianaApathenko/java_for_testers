package ru.apatch.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.apatch.addressbook.model.ContactData;
import ru.apatch.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Set;

public class ContactAddInGroup extends TestBase {

    @Test
    void canAddContactInGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        if (app.contacts().getCount() == 0) {
            app.contacts().CreateContact(new ContactData("1", "Luda", "Ed", "LA", "+57687686", "luda@mail.com", "", "", "", "", "", "", ""));
        }
        var groupList = app.hbm().getGroupList();

        ContactData contactForAddToGroup = null;
        GroupData groupData = groupList.get(0);
        var oldContactListInGroup = app.hbm().getContactsInGroup(groupData);
        var contactListNotInGroup = app.hbm().getContactsNotInGroup();
        if ((contactListNotInGroup != null) && (!contactListNotInGroup.isEmpty())) {
            contactForAddToGroup = contactListNotInGroup.get(0);
            app.contacts().addContactInToGroup(contactForAddToGroup, groupData);
        }
        if (contactForAddToGroup == null) {
            app.contacts().CreateContact(
                    new ContactData("1", "Luda", "Ed", "LA", "+57687686", "luda@mail.com", "", "", "", "", "", "", ""),
                    groupData
            );
            var contacts = app.hbm().getContactsInGroup(groupData);
            contactForAddToGroup = contacts.get(contacts.size() - 1);
        }
        var expectedContactListInGroup = app.hbm().getContactsInGroup(groupData);
        var newContactListInGroup = new ArrayList<>(oldContactListInGroup);
        newContactListInGroup.add(contactForAddToGroup);
        Assertions.assertEquals(Set.copyOf(expectedContactListInGroup), Set.copyOf(newContactListInGroup));
    }
}