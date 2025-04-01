package manager;

import model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        manager.getDriver().findElement(By.linkText("add new")).click();
        fillContactForm(contact);
        manager.getDriver().findElement(By.cssSelector("input[type='submit']")).click();
        manager.getDriver().findElement(By.linkText("home page")).click();
    }

    private void fillContactForm(ContactData contact) {
        manager.getDriver().findElement(By.name("firstname")).sendKeys(contact.firstname());
        manager.getDriver().findElement(By.name("lastname")).sendKeys(contact.lastname());
        manager.getDriver().findElement(By.name("address")).sendKeys(contact.address());
        manager.getDriver().findElement(By.name("mobile")).sendKeys(contact.mobile());
        manager.getDriver().findElement(By.name("work")).sendKeys(contact.work());
        manager.getDriver().findElement(By.name("email")).sendKeys(contact.email());
    }

    public void removeContact() {
        manager.getDriver().findElement(By.name("selected[]")).click();
        manager.getDriver().findElement(By.cssSelector(".left:nth-child(8) input[type='button'][value='Delete']")).click();
        acceptAlert();
    }

    private void acceptAlert() {
        if (isAlertPresent()) {
            manager.getDriver().switchTo().alert().accept();
        }
    }

    private boolean isAlertPresent() {
        try {
            manager.getDriver().switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public boolean isContactPresent() {
        return isElementPresent(By.name("selected[]"));
    }

    private boolean isElementPresent(By locator) {
        return manager.isElementPresent(locator);
    }
}

