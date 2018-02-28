package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.exception.StorageException;
import com.petrovvich.webapp.model.Resume;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {

    private File directory;

    protected AbstractFileStorage(File directory) {
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Directory " + directory.getAbsolutePath() + " isn't directory!");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException("Directory " + directory.getAbsolutePath() + " isn't readable/writeable!");
        }
        this.directory = directory;
    }

    @Override
    protected Resume getResumeFromStorage(File searchIndex) {
        return new Resume(searchIndex.getName());
    }

    @Override
    protected boolean checkIndex(File searchIndex) {
        return searchIndex.exists();
    }

    @Override
    protected File getSearchIndex(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void deleteResumeFromStorage(File searchIndex) {
        searchIndex.delete();
    }

    @Override
    protected void insertElementInStorage(File searchIndex, Resume resume) {
        try {
            searchIndex.createNewFile();
            writeDataInResume(searchIndex, resume);
        } catch (IOException e) {
            throw new StorageException("ERROR: ", searchIndex.getName(), e);
        }
    }

    protected abstract void writeDataInResume(File searchIndex, Resume resume);

    @Override
    protected void updateElementInStorage(File searchIndex, Resume resume) {
        writeDataInResume(searchIndex, resume);
    }

    @Override
    protected List<Resume> getListedResumes() {
        List<Resume> result = null;
        for (File files : Objects.requireNonNull(new File(directory.getName()).listFiles()))
            result.add(new Resume(files.getName()));
        return result;
    }

    @Override
    public void clear() {
        for (File files : Objects.requireNonNull(new File(directory.getName()).listFiles())) {
            if (files.isFile()) {
                files.delete();
            }
        }
    }

    @Override
    public int size() {
        return getListedResumes().size();
    }
}
