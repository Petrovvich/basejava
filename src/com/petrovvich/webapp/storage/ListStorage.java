package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.model.Resume;
import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    protected List<Resume> storage = new ArrayList<>();

    @Override
    protected Resume getResume(Integer searchIndex) {
        return storage.get(searchIndex);
    }

    @Override
    protected boolean checkIndex(Integer searchIndex) {
        return searchIndex < 0;
    }

    @Override
    protected Integer getSearchIndex(String uuid) {
        int result = -1;
        for (int i = 0; i < storage.size(); i++) {
            if(storage.get(i).getUuid().equals(uuid)) {
                result = i;
            }
        }
        return result;
    }

    @Override
    protected void deleteResume(Integer searchIndex) {
        storage.remove(searchIndex.intValue());
    }

    @Override
    protected void insertElement(Integer searchIndex, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void updateElement(Integer searchIndex, Resume resume) {
        storage.set(searchIndex, resume);
    }

    @Override
    protected List<Resume> getListedResumes() {
        return new ArrayList<>(storage);
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
