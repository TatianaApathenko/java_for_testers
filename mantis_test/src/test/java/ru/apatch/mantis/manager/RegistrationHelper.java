package ru.apatch.mantis.manager;

import org.openqa.selenium.By;
import ru.apatch.mantis.model.DeveloperMailUser;
import ru.apatch.mantis.model.UserRegistration;
import ru.apatch.mantis.tests.TestBase;

public class RegistrationHelper extends HelperBase {


    public RegistrationHelper(ApplicationManager manager) {
        super(manager);
    }

    public void canCreateUser(UserRegistration registration) {
        pushLinkForRegistration();
        fillRegistrationForm(registration);
        clickSignup();
    }

    private void pushLinkForRegistration() {
        manager.driver().findElement(By.xpath("//a[@href='signup_page.php']")).click();
    }

    private void fillRegistrationForm(UserRegistration registration) {
        type(By.name("username"), registration.username());
        type(By.name("email"), registration.email());
    }

    private void clickSignup() {
        manager.driver().findElement(By.xpath("//input[@value='Signup']")).click();
    }

    public void confirmUser(String realname, String password) {
        type(By.name("realname"), realname);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        updateUser();
    }

    private void updateUser() {
        manager.driver().findElement(By.xpath("//button[@type='submit']")).click();
    }

    public void startCreation(String user, String email) {
    }

    public void canConfirmUser(String name, String password) {
        type(By.name("realname"), name);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        updateUser();
    }
}