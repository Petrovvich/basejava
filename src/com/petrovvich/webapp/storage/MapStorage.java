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

    }


    @Override
    public void save(Resume r) {
        boolean containsInStorage = storage.containsKey(r.getUuid());
        if (containsInStorage) {
            throw new ExistStorageException(r.getUuid());
        } else if (storage.size() == STORAGE_CAPACITY) {
            throw new StorageException("База резюме переполнена!", r.getUuid());
        } else {
            storage.put(r.getUuid(), r);
        }
    }

    @Override
    public Resume get(String uuid) {
        boolean containsInStorage = storage.containsKey(uuid);
        if (containsInStorage) {
            return storage.get(uuid);
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public void delete(String uuid) {
        boolean containsInStorage = storage.containsKey(uuid);
        if (!containsInStorage) {
            throw new NotExistStorageException(uuid);
        } else {
            storage.remove(uuid);
        }
    }

    @Override
    public Resume[] getAll() {
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            System.out.println(entry.getValue());
        }
        //TODO: заглушка пока не придумаю как адаптировать для Map этот метод
        return new Resume[10];
    }

    @Override
    public int size() {
        return storage.size();
    }
}
