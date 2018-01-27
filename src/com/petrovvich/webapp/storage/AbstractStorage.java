package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.exception.ExistStorageException;
import com.petrovvich.webapp.exception.NotExistStorageException;
import com.petrovvich.webapp.exception.StorageException;
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

    @Override
    public void save(Resume r) {
        Object searchIndex = getSearchIndex(r.getUuid());
        boolean validateIndex = checkIndex(searchIndex);
        if (validateIndex) {
            throw new ExistStorageException(r.getUuid());
        } else if (size() == STORAGE_CAPACITY) {
            throw new StorageException("База резюме переполнена!", r.getUuid());
        } else {
            insertElementInStorage(searchIndex, r);
        }
    }

    protected abstract void insertElementInStorage(Object searchIndex, Resume resume);

    public void update(Resume resume) {
        Object searchIndex = getSearchIndex(resume.getUuid());
        boolean validateIndex = checkIndex(searchIndex);
        if (!validateIndex) {
            throw new NotExistStorageException(resume.getUuid());
        }
        updateElementInStorage(searchIndex, resume);
    }

    protected abstract void updateElementInStorage(Object searchIndex, Resume resume);

    public abstract int size();
}
