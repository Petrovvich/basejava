package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.exception.ExistStorageException;
import com.petrovvich.webapp.exception.NotExistStorageException;
import com.petrovvich.webapp.exception.StorageException;
import com.petrovvich.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {

    protected Resume[] storage = new Resume[STORAGE_CAPACITY];

    protected int sizeOfArray = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, sizeOfArray, null);
        sizeOfArray = 0;
    }

    protected Boolean definitionForAssert(Resume resume) {
        return getIndex(resume.getUuid()) < 0;
    }

    protected void updateElement(int index, Resume resume) {
        storage[index] = resume;
    }

    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else if (sizeOfArray == STORAGE_CAPACITY) {
            throw new StorageException("База резюме переполнена!", r.getUuid());
        } else {
            insertElement(r, index);
            sizeOfArray++;
        }
    }

    protected abstract void insertElement(Resume r, int index);

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteElement(index);
            storage[sizeOfArray - 1] = null;
            sizeOfArray--;
        }
    }

    protected abstract void deleteElement(int index);

    protected abstract int getIndex(String uuid);

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, sizeOfArray);
    }

    @Override
    public int size() {
        return sizeOfArray;
    }
}