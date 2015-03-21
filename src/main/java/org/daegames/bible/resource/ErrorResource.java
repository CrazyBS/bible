package org.daegames.bible.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.validation.ObjectError;

import java.util.List;

public class ErrorResource<T extends ResourceSupport> {
    private T subject;
    private List<? extends ObjectError> errors;

    public ErrorResource(T subject, List<? extends ObjectError> errors) {
        this.subject = subject;
        this.errors = errors;
    }

    @JsonProperty("subject")
    public T getSubject() {
        return subject;
    }

    @JsonProperty("errors")
    public List<? extends ObjectError> getErrors() {
        return errors;
    }
}
