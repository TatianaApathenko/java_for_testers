package manager;

import model.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void CreateContact(ContactData contact) {
        click(By.linkText("add new"));
        fillContactForm(contact);
        submitContactCreation(By.name("submit"));
        returnToHomePage();
    }

    public void removeContact(ContactData contact) {
        selectContact(contact);
        deleteContacts();
        returnToHomePage();
    }

    public void removeAllContacts() {
        selectAllContacts();
        deleteContacts();
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        returnToHomePage();
        selectContact(contact);
        initContactModification(contact);
        fillContactForm(modifiedContact);
        updateContactModification();
        returnToHomePage();
    }

    private void updateContactModification() {
        click(By.name("update"));
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
        attach(By.name("photo"), contact.photo());
    }

    private void submitContactCreation(By locator) {
        manager.driver.findElement(locator).click();
    }

    private void returnToHomePage() {
        manager.driver.findElement(By.linkText("home")).click();
    }

    private void deleteContacts() {
        click(By.cssSelector(".left:nth-child(8) > input"));
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
        returnToHomePage();
        var contacts = new ArrayList<ContactData>();
        var trs = manager.driver.findElements(By.name("entry"));
        for (var tr : trs) {
            var td = tr.findElements(By.tagName("td"));
            String id = td.get(0).findElement(By.cssSelector("input[type='checkbox']")).getAttribute("value");
            String lastname = td.get(1).getText();
            String firstname = td.get(2).getText();
            contacts.add(new ContactData().withId(id).withName(firstname).withLastName(lastname));
        }
        return contacts;
    }
}