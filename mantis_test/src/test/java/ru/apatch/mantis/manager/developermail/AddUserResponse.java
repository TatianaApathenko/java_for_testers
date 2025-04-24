package ru.apatch.mantis.manager.developermail;

import ru.apatch.mantis.model.DeveloperMailUser;

public record AddUserResponse(Boolean success, Object errors, DeveloperMailUser result) {
}