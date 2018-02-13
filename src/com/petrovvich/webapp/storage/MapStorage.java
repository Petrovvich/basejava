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
        Resume resume = new Resume();
        resume.setFullname(uuid);
        return resume.getFullname();
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
    public List<Resume> getAllSorted() {
        List<Resume> resumes = new ArrayList<>(storage.values());
        resumes.sort(RESUME_COMPARATOR);
        return resumes;
    }
}
