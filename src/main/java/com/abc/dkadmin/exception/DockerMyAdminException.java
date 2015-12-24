package com.abc.dkadmin.exception;

public class DockerMyAdminException extends RuntimeException  {

    public DockerMyAdminException() {
        super();
    }

    public DockerMyAdminException(String message) {
        super(message);
    }

    public DockerMyAdminException(String message, Throwable cause) {
        super(message, cause);
    }

    public DockerMyAdminException(Throwable cause) {
        super(cause);
    }
}
