package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertElement(Resume r, int index) {
        storage[sizeOfArray] = r;
    }

    @Override
    protected void deleteElement(int index) {
        storage[index] = storage[sizeOfArray - 1];
    }

    protected Integer getSearchIndex(String uuid) {
        for (int i = 0; i < sizeOfArray; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}