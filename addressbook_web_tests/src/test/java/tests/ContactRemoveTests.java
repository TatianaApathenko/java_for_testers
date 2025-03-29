package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactRemoveTests extends TestBase {

    @Test
    public void canRemoveContact() {

        if (!app.isContactPresent()) {
            app.createContact(new ContactData("Irishka", "Mask", "Gdetotam",
                    "+0999923321444", "horoshaya", "irishka.mail.ru"));
        }
        app.removeContact();

    }
}
