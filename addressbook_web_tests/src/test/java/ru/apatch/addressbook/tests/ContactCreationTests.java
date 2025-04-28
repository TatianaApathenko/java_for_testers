package ru.apatch.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.apatch.addressbook.common.CommonFunctions;
import io.qameta.allure.Allure;
import ru.apatch.addressbook.model.ContactData;
import ru.apatch.addressbook.model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase{

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
//        for (var firstname : List.of("", "firstname")){
//            for (var lastname : List.of("", "lastname")){
//                result.add(new ContactData()
//                        .withName(firstname)
//                        .withLastName(lastname));
//            }
//        }
//        for(int i = 0; i < 5; i++){
//            result.add(new ContactData()
//                    .withName(CommonFunctions.randomString((i+1)*10))
//                    .withLastName(CommonFunctions.randomString((i+1)*10)));
//        }
        var json = Files.readString(Paths.get("contacts.json"));
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json, new TypeReference<List<ContactData>>() {});
        result.addAll(value);
        return result;
    }

    public static List<ContactData> singleRandomContact() {
        return List.of(new ContactData()
                .withName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(20)));
    }

    @ParameterizedTest
    @MethodSource("singleRandomContact")
    public void CanCreateContact(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().CreateContact(contact);
        var newContacts = app.hbm().getContactList();
        var extraContact = newContacts.stream().filter(c -> ! oldContacts.contains(c)).toList();
        var newId = extraContact.get(0).id();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newId));
        Allure.step("Validating results", step -> {
            Assertions.assertEquals(Set.copyOf(newContacts), Set.copyOf(expectedList));
        });
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("", "contact'", "", "", "", "", "", "", "", "", "", "", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void CanNotCreateContact(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().CreateContact(contact);
        var newContacts = app.contacts().getList();
        Allure.step("Validating results", step -> {
            Assertions.assertEquals(oldContacts, newContacts);
        });
    }


    @Test
    void canCreateContactInGroup() {
        var contact = new ContactData()
                .withName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withAddress(CommonFunctions.randomString(10))
                .withMobile(CommonFunctions.randomString(10))
                .withEmail(CommonFunctions.randomString(10));
        if (app.hbm().getGroupCount() == 0){
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().get(0);

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().CreateContact(contact,group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Allure.step("Validating results", step -> {
            Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
        });
    }
}