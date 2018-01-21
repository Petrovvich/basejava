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
    protected void updateElement(int index, Resume resume) {
        storage.set(index, resume);
    }

    @Override
    protected Boolean definitionForAssert(Resume resume) {
        return getIndex(resume.getUuid()) < 0;
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
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage.get(index);
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            storage.remove(index);
        }
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

    protected int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return storage.indexOf(resume);
    }
}
