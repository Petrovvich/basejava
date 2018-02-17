package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.exception.StorageException;
import com.petrovvich.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    protected static final int STORAGE_CAPACITY = 10000;

    protected Resume[] storage = new Resume[STORAGE_CAPACITY];

    protected int sizeOfArray = 0;

    @Override
    protected Resume getResumeFromStorage(Integer searchIndex) {
        return storage[searchIndex];
    }

    @Override
    protected boolean checkIndex(Integer searchIndex) {
        return  searchIndex < 0;
    }

    @Override
    protected void deleteResumeFromStorage(Integer searchIndex) {
        deleteElement( searchIndex);
        storage[sizeOfArray - 1] = null;
        sizeOfArray--;
    }

    protected abstract void deleteElement(int index);

    @Override
    protected void insertElementInStorage(Integer searchIndex, Resume resume) {
        if (size() == STORAGE_CAPACITY) {
            throw new StorageException("База резюме переполнена!", resume.getUuid());
        } else {
            insertElement(resume, searchIndex);
            sizeOfArray++;
        }
    }

    protected abstract void insertElement(Resume r, int index);

    @Override
    protected void updateElementInStorage(Integer searchIndex, Resume resume) {
        storage[searchIndex] = resume;
    }

    @Override
    protected List<Resume> getListedResumes() {
        return Arrays.asList(Arrays.copyOf(storage, sizeOfArray));
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
}