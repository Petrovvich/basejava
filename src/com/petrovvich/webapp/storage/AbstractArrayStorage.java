package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {

    protected Resume[] storage = new Resume[STORAGE_CAPACITY];

    protected int sizeOfArray = 0;

    @Override
    protected Resume getResumeFromStorage(Object searchIndex) {
        return storage[(Integer) searchIndex];
    }

    @Override
    protected boolean checkIndex(Object searchIndex) {
        return (Integer) searchIndex < 0;
    }

    @Override
    protected void deleteResumeFromStorage(Object searchIndex) {
        deleteElement((Integer) searchIndex);
        storage[sizeOfArray - 1] = null;
        sizeOfArray--;
    }

    protected abstract void deleteElement(int index);

    @Override
    protected void insertElementInStorage(Object searchIndex, Resume resume) {
        insertElement(resume, (Integer) searchIndex);
        sizeOfArray++;
    }

    protected abstract void insertElement(Resume r, int index);

    @Override
    protected void updateElementInStorage(Object searchIndex, Resume resume) {
        storage[(Integer) searchIndex] = resume;
    }

    @Override
    public int size() {
        return sizeOfArray;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, sizeOfArray, null);
        sizeOfArray = 0;
    }

    @Override
    public List<Resume> getAllSorted() {
        return Arrays.asList(Arrays.copyOf(storage, sizeOfArray));
    }
}