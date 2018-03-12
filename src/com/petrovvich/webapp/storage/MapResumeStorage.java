package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage<Resume> {

    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Resume getResume(Resume searchIndex) {
        return storage.get(searchIndex.getUuid());
    }

    @Override
    protected boolean checkIndex(Resume searchIndex) {
        return searchIndex == null;
    }

    @Override
    protected Resume getSearchIndex(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void deleteResume(Resume searchIndex) {
        storage.remove(searchIndex.getUuid());
    }

    @Override
    protected void insertElement(Resume searchIndex, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateElement(Resume searchIndex, Resume resume) {
        storage.replace(searchIndex.getUuid(), storage.get(searchIndex.getUuid()), resume);
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
