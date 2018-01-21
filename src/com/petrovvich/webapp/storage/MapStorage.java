package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.exception.ExistStorageException;
import com.petrovvich.webapp.exception.NotExistStorageException;
import com.petrovvich.webapp.exception.StorageException;
import com.petrovvich.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    protected Map<String, Resume> storage = new HashMap<>(STORAGE_CAPACITY);

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void update(Resume resume) {
        if (containsInStorage(resume)) {
            storage.replace(resume.getUuid(), resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void save(Resume r) {
        if (containsInStorage(r)) {
            throw new ExistStorageException(r.getUuid());
        } else if (storage.size() == STORAGE_CAPACITY) {
            throw new StorageException("База резюме переполнена!", r.getUuid());
        } else {
            storage.put(r.getUuid(), r);
        }
    }

    @Override
    public Resume get(String uuid) {
        Resume toFind = new Resume(uuid);
        if (containsInStorage(toFind)) {
            return storage.get(uuid);
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public void delete(String uuid) {
        Resume toDelete = new Resume(uuid);
        if (containsInStorage(toDelete)) {
            storage.remove(uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public void getAll() {
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            System.out.println(entry.getValue());
        }

    }

    @Override
    public int size() {
        return storage.size();
    }

    private boolean containsInStorage(Resume r) {
        return storage.containsKey(r.getUuid());
    }
}
