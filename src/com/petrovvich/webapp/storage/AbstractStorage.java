package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.exception.NotExistStorageException;
import com.petrovvich.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected static final int STORAGE_CAPACITY = 10000;

    @Override
    public Resume get(String uuid) {
        Object searchIndex = getSearchIndex(uuid);
        boolean validateIndex = checkIndex(searchIndex);
        if (!validateIndex) {
            throw new NotExistStorageException(uuid);
        }
        return getResumeFromStorage(searchIndex); //storage[index];
    }

    protected abstract Resume getResumeFromStorage(Object searchIndex);

    protected boolean checkIndex(Object searchIndex) {
        if ((Integer) searchIndex < 0) {
            return false;
        }
        return true;
    }

    protected abstract Object getSearchIndex(String uuid);

}
