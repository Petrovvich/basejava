package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.exception.ExistStorageException;
import com.petrovvich.webapp.exception.StorageException;
import com.petrovvich.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    protected List<Resume> storage = new ArrayList<>(STORAGE_CAPACITY);

    protected int sizeOfArray = storage.size();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void update(Resume resume) {

    }

    @Override
    protected void updateElementInStorage(Object searchIndex, Resume resume) {
        storage.add(resume);
    }



    @Override
    protected void insertElementInStorage(Object searchIndex, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected Resume getResumeFromStorage(Object searchIndex) {
        return storage.get((Integer) searchIndex);
    }

    @Override
    protected boolean checkIndex(Object searchIndex) {
        if ((Integer) searchIndex < 0) {
            return false;
        }
        return true;
    }

    @Override
    protected void deleteResumeFromStorage(Object searchIndex) {
        storage.remove(searchIndex);
    }

    @Override
    public Resume[] getAll() {
        //TODO: заглушка пока не придумаю как адаптировать для Map этот метод
        return new Resume[0];
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Integer getSearchIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return storage.indexOf(resume);
    }
}
