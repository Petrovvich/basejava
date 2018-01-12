package com.petrovvich.webapp.exception;

public class ExistStorageException extends StorageException {

    public ExistStorageException(String uuid) {
        super(uuid);
    }
}
