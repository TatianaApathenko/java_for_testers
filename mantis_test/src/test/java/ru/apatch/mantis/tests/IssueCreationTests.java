package ru.apatch.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.apatch.mantis.common.CommonFunctions;
import ru.apatch.mantis.model.IssueData;

public class IssueCreationTests extends TestBase{

    @Test
    void canCreateIssue() {
        app.rest().createIssue(new IssueData()
                .withSummary(CommonFunctions.randomString(10))
                .withDescription(CommonFunctions.randomString(50))
                .withProject(1L));
    }
}