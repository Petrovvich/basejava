package com.petrovvich.webapp.exception;

public class ExistStorageException extends StorageException {

    public ExistStorageException(String uuid) {
        super("Резюме " + uuid + " уже есть в базе!", uuid);
    }
}
