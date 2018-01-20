package com.petrovvich.webapp.storage;

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
        storage.replace(resume.getUuid(), resume);
    }

    @Override
    public void save(Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    public Resume get(String uuid) {
        return storage.get(uuid);
    }

    @Override
    public void delete(String uuid) {
        storage.remove(uuid);
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

}
