package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage<String> {

    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Resume getResumeFromStorage(String searchIndex) {
        return storage.get(searchIndex);
    }

    @Override
    protected boolean checkIndex(String searchIndex) {
        return !storage.containsKey(searchIndex);
    }

    @Override
    protected String getSearchIndex(String uuid) {
        return uuid;
    }

    @Override
    protected void deleteResumeFromStorage(String searchIndex) {
        storage.remove(searchIndex);
    }

    @Override
    protected void insertElementInStorage(String searchIndex, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateElementInStorage(String searchIndex, Resume resume) {
        storage.replace(searchIndex, storage.get(searchIndex), resume);
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
