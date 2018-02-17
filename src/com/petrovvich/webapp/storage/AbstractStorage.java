package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.exception.ExistStorageException;
import com.petrovvich.webapp.exception.NotExistStorageException;
import com.petrovvich.webapp.exception.StorageException;
import com.petrovvich.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOGGER = Logger.getLogger(AbstractStorage.class.getName());

    protected static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid).thenComparing(Resume::getFullname);

    @Override
    public Resume get(String uuid) {
        LOGGER.info("Get resume with uuid " + uuid);
        SK searchIndex = getSearchIndex(uuid);
        boolean validateIndex = checkIndex(searchIndex);
        if (validateIndex) {
            throw new NotExistStorageException(uuid);
        }
        return getResumeFromStorage(searchIndex);
    }

    protected abstract Resume getResumeFromStorage(SK searchIndex);

    protected abstract boolean checkIndex(SK searchIndex);

    protected abstract SK getSearchIndex(String uuid);

    @Override
    public void delete(String uuid) {
        LOGGER.info("Delete resume with uuid " + uuid);
        SK searchIndex = getSearchIndex(uuid);
        boolean validateIndex = checkIndex(searchIndex);
        if (validateIndex) {
            LOGGER.warning("Can't delete resume " + uuid + ". Resume is not in Storage!");
            throw new NotExistStorageException(uuid);
        } else {
            deleteResumeFromStorage(searchIndex);
        }
    }

    protected abstract void deleteResumeFromStorage(SK searchIndex);

    @Override
    public void save(Resume r) {
        LOGGER.info("Save resume " + r);
        SK searchIndex = getSearchIndex(r.getUuid());
        boolean validateIndex = checkIndex(searchIndex);
        if (!validateIndex) {
            LOGGER.warning("Can't save resume " + r + ". Resume is already in Storage!");
            throw new ExistStorageException(r.getUuid());
        } else {
            insertElementInStorage(searchIndex, r);
        }
    }

    protected abstract void insertElementInStorage(SK searchIndex, Resume resume);

    public void update(Resume resume) {
        LOGGER.info("Update resume " + resume);
        SK searchIndex = getSearchIndex(resume.getUuid());
        boolean validateIndex = checkIndex(searchIndex);
        if (validateIndex) {
            LOGGER.warning("Can't update resume " + resume + ". Resume is not in Storage!");
            throw new ExistStorageException(resume.getUuid());
        }
        updateElementInStorage(searchIndex, resume);
    }

    protected abstract void updateElementInStorage(SK searchIndex, Resume resume);

    public List<Resume> getAllSorted() {
        LOGGER.info("Get all sorted");
        List<Resume> result = getListedResumes();
        result.sort(RESUME_COMPARATOR);
        return result;
    }

    protected abstract List<Resume> getListedResumes();

    public abstract int size();
}
