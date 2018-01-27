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
    protected void updateElementInStorage(Object searchIndex, Resume resume) {
        storage.replace(resume.getUuid(), resume);
    }

    @Override
    protected void insertElementInStorage(Object searchIndex, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResumeFromStorage(Object searchIndex) {
        return storage.get(searchIndex);
    }

    @Override
    protected boolean checkIndex(Object searchIndex) {
        return storage.containsKey(searchIndex);
    }

    @Override
    protected Object getSearchIndex(String uuid) {
        if (storage.get(uuid) == null) {
            return -1;
        }
        return storage.get(uuid);
    }

    @Override
    protected void deleteResumeFromStorage(Object searchIndex) {
        storage.remove(searchIndex);
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
