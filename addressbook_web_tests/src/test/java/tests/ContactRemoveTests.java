package tests;
import model.ContactData;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class ContactRemoveTests extends TestBase {
    private WebDriver driver;

    @Test
    public void CanRemoveContact() {
        if (!app.isContactPresent()){
            app.CreateContact(new ContactData("Irishka","Mask","Gdetotam","+0999923321444","horoshaya","irishka.mail.ru"));
        }
        app.removeContact();
    }
}


