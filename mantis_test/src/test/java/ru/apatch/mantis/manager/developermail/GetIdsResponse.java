package ru.apatch.mantis.manager.developermail;

import java.util.List;

public record GetIdsResponse(Boolean success, Object errors, List<String> result) {
}