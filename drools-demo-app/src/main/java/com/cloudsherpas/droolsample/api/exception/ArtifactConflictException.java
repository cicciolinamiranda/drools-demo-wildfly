package com.cloudsherpas.droolsample.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author CMiranda
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND,
                reason = "Artifact with the same details already exists.")
public class ArtifactConflictException extends RuntimeException {

}
