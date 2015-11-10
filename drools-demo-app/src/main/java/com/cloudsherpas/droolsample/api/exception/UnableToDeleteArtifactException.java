package com.cloudsherpas.droolsample.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author CMiranda
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND,
                reason = "Unable to delete artifact. Its either the id is not existing or the artifact is active")
public class UnableToDeleteArtifactException extends RuntimeException {

}