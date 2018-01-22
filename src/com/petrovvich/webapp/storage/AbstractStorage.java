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

    protected abstract boolean checkIndex(Object searchIndex);

    protected abstract Object getSearchIndex(String uuid);

    @Override
    public void delete(String uuid) {
        Object searchIndex = getSearchIndex(uuid);
        boolean validateIndex = checkIndex(searchIndex);
        if (!validateIndex) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResumeFromStorage(searchIndex);
        }
    }

    protected abstract void deleteResumeFromStorage(Object searchIndex);

}
