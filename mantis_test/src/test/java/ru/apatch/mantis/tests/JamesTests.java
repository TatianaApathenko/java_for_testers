package ru.apatch.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.apatch.mantis.common.CommonFunctions;

public class JamesTests extends TestBase {

    @Test
    void CanCreateUser() {
        app.jamesCli().addUser(
                String.format("%s@localhost", CommonFunctions.randomString(8)),
                "password");
    }
}