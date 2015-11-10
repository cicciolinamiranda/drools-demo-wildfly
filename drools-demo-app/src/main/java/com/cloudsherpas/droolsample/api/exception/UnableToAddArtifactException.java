package com.cloudsherpas.droolsample.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author CMiranda
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND,
                reason = "Unable to artifact. The credentials entered are already existing")
public class UnableToAddArtifactException extends RuntimeException {

}
