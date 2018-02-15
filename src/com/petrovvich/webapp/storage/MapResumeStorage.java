package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {

    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Resume getResumeFromStorage(Object searchIndex) {
        return storage.get(((Resume) searchIndex).getUuid());
    }

    @Override
    protected boolean checkIndex(Object searchIndex) {
        return searchIndex == null;
    }

    @Override
    protected Object getSearchIndex(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void deleteResumeFromStorage(Object searchIndex) {
        storage.remove(((Resume) searchIndex).getUuid());
    }

    @Override
    protected void insertElementInStorage(Object searchIndex, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateElementInStorage(Object searchIndex, Resume resume) {
        storage.replace(((Resume) searchIndex).getUuid(), storage.get(((Resume) searchIndex).getUuid()), resume);
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
