package com.abc.dkadmin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ContainerNotFoundException extends RuntimeException {

    public ContainerNotFoundException(String containerId) {
        super("Could not find container: " + containerId);
    }

}
