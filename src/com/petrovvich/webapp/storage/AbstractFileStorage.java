package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.exception.StorageException;
import com.petrovvich.webapp.model.Resume;
import java.io.File;
import java.io.IOException;
import java.util.List;

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
        return readData(searchIndex);
    }

    protected abstract Resume readData(File searchIndex);

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
        if (!searchIndex.delete()) {
            throw new StorageException("Can't delete resume", searchIndex.getName());
        }
        searchIndex.delete();
    }

    @Override
    protected void insertElementInStorage(File searchIndex, Resume resume) {
        try {
            searchIndex.createNewFile();
            writeData(searchIndex, resume);
        } catch (IOException e) {
            throw new StorageException("ERROR: ", searchIndex.getName(), e);
        }
    }

    protected abstract void writeData (File searchIndex, Resume resume);

    @Override
    protected void updateElementInStorage(File searchIndex, Resume resume) {
        writeData(searchIndex, resume);
    }

    @Override
    protected List<Resume> getListedResumes() {
        List<Resume> result = null;
        try {
            for (File files : new File(directory.getName()).listFiles())
                result.add(getResumeFromStorage(files));
        } catch (Exception e) {
            throw new StorageException("ERROR: directory is not exist or empty", directory.getName(), e);
        }
        return result;
    }

    @Override
    public void clear() {
        try {
            for (File files : new File(directory.getName()).listFiles()) {
                if (files.isFile()) {
                    deleteResumeFromStorage(files);
                }
            }
        } catch (Exception e) {
            throw new StorageException("ERROR: directory is not exist", directory.getName(), e);
        }
    }

    @Override
    public int size() {
        return getListedResumes().size();
    }
}
