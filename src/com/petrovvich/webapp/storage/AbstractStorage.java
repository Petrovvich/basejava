package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.exception.NotExistStorageException;
import com.petrovvich.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected static final int STORAGE_CAPACITY = 10000;

    public void update(Resume resume) {
        Object definition = definitionForAssert(resume); //getIndex(resume.getUuid());
        if (definition.equals(true)) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateElement((Integer) definitionForAssert(resume), resume);//storage.set(index, resume);
        }
    }

    protected abstract void updateElement(int index, Resume resume);

    protected abstract Object definitionForAssert(Resume resume);
}
