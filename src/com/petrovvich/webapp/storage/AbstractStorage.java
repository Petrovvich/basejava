package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.exception.NotExistStorageException;
import com.petrovvich.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected static final int STORAGE_CAPACITY = 10000;

}
