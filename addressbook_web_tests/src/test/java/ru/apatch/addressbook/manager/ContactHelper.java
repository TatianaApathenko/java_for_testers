package ru.apatch.addressbook.manager;

import ru.apatch.addressbook.model.ContactData;
import ru.apatch.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void CreateContact(ContactData contact) {
        click(By.linkText("add new"));
        fillContactForm(contact);
        click(By.name("submit"));
        returnToHomePAge();
    }

    public void CreateContact(ContactData contact, GroupData group) {
        click(By.linkText("add new"));
        fillContactForm(contact);
        selectGroup(group);
        click(By.name("submit"));
        returnToHomePAge();
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    public void removeContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
        deleteContacts();
        click(By.id("logo"));
    }

    public void removeAllContacts() {
        selectAllContacts();
        deleteContacts();
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        returnToHomePAge();
        selectContact(contact);
        initContactModification(contact);
        fillContactForm(modifiedContact);
        updateContactModification();
        click(By.linkText("home page"));
    }

    private void updateContactModification() {
        click(By.xpath("//input[@name='update']"));

    }

    private void initContactModification(ContactData contact) {
        var edit = manager.driver.findElement(By.xpath(String.format("//a[@href='edit.php?id=%s']", contact.id())));
        edit.click();
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("lastname"), contact.lastname());
        type(By.name("address"), contact.address());
        type(By.name("mobile"), contact.mobile());
        type(By.name("email"), contact.email());
        //attach(By.name("photo"), contact.photo());
    }


    private void returnToHomePAge() {
        manager.driver.findElement(By.linkText("home")).click();
    }

    private void deleteContacts() {
        click(By.cssSelector(".left:nth-child(8) > input"));
        //manager.driver.switchTo().alert().accept();
    }

    public int getCount() {
        return manager.driver.findElements(By.name("selected[]")).size();
    }


    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public List<ContactData> getList() {
        //returnToHomePAge();
        var contacts = new ArrayList<ContactData>();
        var trs = manager.driver.findElements(By.name("entry"));
        for (var tr : trs) {
            var td = tr.findElements(By.tagName("td"));
            String id = String.valueOf(Integer.parseInt(td.get(0).findElement(By.cssSelector("input[type='checkbox']")).getAttribute("value")));
            String lastname = td.get(1).getText();
            String firstname = td.get(2).getText();
            contacts.add(new ContactData().withId(id).withName(firstname).withLastName(lastname));
        }
        return contacts;
    }

    public void addContactInToGroup(ContactData contactForAddToGroup, GroupData groupData) {
        returnToHomePAge();
        selectContact(contactForAddToGroup);
        selectToGroup(groupData);
        addToContact();
    }

    private void addToContact() {
        manager.driver.findElement(By.name("add")).click();
    }

    private void selectToGroup(GroupData groupData) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(groupData.id());
    }

    public void selectGroupById(GroupData groupData) {
        returnToHomePAge();
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(groupData.id());
    }

    public void removeContactFromGroup(ContactData contactForDelete) {
        selectContact(contactForDelete);
        manager.driver.findElement(By.name("remove")).click();
    }
}