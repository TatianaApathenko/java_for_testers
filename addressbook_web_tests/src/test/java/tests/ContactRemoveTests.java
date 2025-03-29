package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ContactRemoveTests extends TestBase {

    @Test
    public void canRemoveContact() {
        // Создаем контакт, если он не существует
        if (!app.isContactPresent()) {
            app.createContact(new ContactData("Irishka", "Mask", "Gdetotam",
                    "+0999923321444", "horoshaya", "irishka.mail.ru"));
        }
        // Запо

        // Удаляем контакт
        app.removeContact();


    }
}
