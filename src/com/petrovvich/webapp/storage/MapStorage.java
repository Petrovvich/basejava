package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.exception.NotExistStorageException;
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
        return storage.containsKey(searchIndex);
    }

    @Override
    protected Object getSearchIndex(String uuid) {
        if (storage.get(uuid) == null) {
            return -1;
        }
        return storage.get(uuid).getUuid();
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
    public void update(Resume resume) {
        String index = (String) getSearchIndex(resume.getUuid());
        if (!checkIndex(index)) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storage.replace(index, storage.get(index), resume);
        }
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
}
