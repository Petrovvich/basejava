package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.model.Resume;
import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    protected List<Resume> storage = new ArrayList<>(STORAGE_CAPACITY);

    @Override
    protected Resume getResumeFromStorage(Object searchIndex) {
        return storage.get((Integer) searchIndex);
    }

    @Override
    protected boolean checkIndex(Object searchIndex) {
        return (Integer) searchIndex < 0;
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
    protected void deleteResumeFromStorage(Object searchIndex) {
        storage.remove(((Integer) searchIndex).intValue());
    }

    @Override
    protected void insertElementInStorage(Object searchIndex, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void updateElementInStorage(Object searchIndex, Resume resume) {
        storage.set((Integer) searchIndex, resume);
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
        return storage.toArray(new Resume[storage.size()]);
    }
}
