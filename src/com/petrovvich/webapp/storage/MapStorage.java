package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {

    protected Map<String, Resume> storage = new HashMap<>();

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
    protected List<Resume> getListedResumes() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }
}
