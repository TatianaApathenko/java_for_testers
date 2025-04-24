package ru.apatch.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.apatch.mantis.common.CommonFunctions;
import ru.apatch.mantis.model.DeveloperMailUser;
import ru.apatch.mantis.model.UserRegistration;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase {

    private DeveloperMailUser user;


    @ParameterizedTest
    @MethodSource("singleUser")
    void canRegisterUser(UserRegistration registration) {
        app.jamesCli().addUser(registration.email(), "password");
        app.registration().canCreateUser(registration);
        var messages = app.mail().receive(registration.email(), "password", Duration.ofSeconds(60));
        var text = messages.getFirst().content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        String url = null;
        if (matcher.find()) {
            url = text.substring(matcher.start(), matcher.end());
        }
        app.driver().get(url);
        app.registration().confirmUser(CommonFunctions.randomString(10), "password");
        app.http().login(registration.username(), "password");
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    public static List<UserRegistration> singleUser() {
        var name = CommonFunctions.randomString(10);
        return List.of(new UserRegistration()
                .withUsername(name)
                .withEmail(String.format("%s@localhost", name)));
    }

    @Test
    void canCreateUser() {
        var password = "password";
        user = app.developerMail().addUser();
        var email = String.format("%s@developermail.com", user.name());
    }


    @AfterEach
    void deleteMailUser() {
        app.developerMail().deleteUser(user);
    }
}