package com.petrovvich.webapp.storage;

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

    protected void insertElementInStorage(Object searchIndex, Resume resume) {
        insertElement(resume, (Integer) searchIndex);
        sizeOfArray++;
    }

    protected abstract void insertElement(Resume r, int index);

    protected abstract void deleteElement(int index);

    protected void deleteResumeFromStorage(Object searchIndex) {
        deleteElement((Integer) searchIndex);
        storage[sizeOfArray - 1] = null;
        sizeOfArray--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, sizeOfArray);
    }

    @Override


    protected Resume getResumeFromStorage(Object searchIndex) {
        return storage[(Integer) searchIndex];
    }

    protected boolean checkIndex(Object searchIndex) {
        if ((Integer) searchIndex < 0) {
            return false;
        }
        return true;
    }

    @Override
    public int size() {
        return sizeOfArray;
    }

    protected void updateElementInStorage(Object searchIndex, Resume resume) {
        insertElement(resume, (Integer) searchIndex);
    }
}