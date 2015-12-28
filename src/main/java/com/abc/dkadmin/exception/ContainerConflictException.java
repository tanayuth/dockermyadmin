package com.abc.dkadmin.exception;

public class ContainerConflictException extends RuntimeException {

    public ContainerConflictException(String containerId) {
        super("Failed to remove containers: [" + containerId + "]. " +
                "You cannot remove a running container. Stop the container before attempting removal or use -f");
    }
}
