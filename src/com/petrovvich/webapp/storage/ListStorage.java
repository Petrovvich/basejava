package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.exception.ExistStorageException;
import com.petrovvich.webapp.exception.NotExistStorageException;
import com.petrovvich.webapp.exception.StorageException;
import com.petrovvich.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    protected List<Resume> storage = new ArrayList<>(STORAGE_CAPACITY);


    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void update(Resume resume) {

    }

    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else if (storage.size() == STORAGE_CAPACITY) {
            throw new StorageException("База резюме переполнена!", r.getUuid());
        } else {
            storage.add(r);
        }
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
