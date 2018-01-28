package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    protected Map<String, Resume> storage = new HashMap<>(STORAGE_CAPACITY);

    @Override
    protected Resume getResumeFromStorage(Object searchIndex) {
        return storage.get(searchIndex);
    }

    @Override
    protected boolean checkIndex(Object searchIndex) {
        return !storage.containsKey(searchIndex);
    }

    @Override
    protected Object getSearchIndex(String uuid) {
        return uuid;
    }

    @Override
    protected void deleteResumeFromStorage(Object searchIndex) {
        storage.remove(searchIndex);
    }

    @Override
    protected void insertElementInStorage(Object searchIndex, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateElementInStorage(Object searchIndex, Resume resume) {
        storage.replace((String) searchIndex, storage.get(searchIndex), resume);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        Resume[] result = (Resume[]) storage.values().toArray();
        return result;
    }

    public static void main(String[] args) {
        MapStorage mapStorage = new MapStorage();
        Resume r = new Resume("45");
        mapStorage.save(r);
        mapStorage.update(r);
        System.out.println(mapStorage.size());
        System.out.println(mapStorage.get("45"));

    }
}
