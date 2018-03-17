package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.exception.StorageException;
import com.petrovvich.webapp.model.Resume;

import java.io.*;
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
    protected Resume getResume(File searchIndex) {
        try {
            return readData(new BufferedInputStream(new FileInputStream(searchIndex)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract Resume readData (InputStream searchIndex) throws IOException;

    @Override
    protected boolean checkIndex(File searchIndex) {
        return searchIndex.exists();
    }

    @Override
    protected File getSearchIndex(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void deleteResume(File searchIndex) {
        if (!searchIndex.delete()) {
            throw new StorageException("Can't delete resume", searchIndex.getName());
        }
    }

    @Override
    protected void insertElement(File searchIndex, Resume resume) {
            updateElement(searchIndex, resume);
    }

    protected abstract void writeData (OutputStream searchIndex, Resume resume) throws IOException;

    @Override
    protected void updateElement(File searchIndex, Resume resume) {
        try {
            writeData(new BufferedOutputStream(new FileOutputStream(searchIndex)), resume);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected List<Resume> getListedResumes() {
        List<Resume> result = null;
        if (directory.listFiles() != null) {
            try {
                for (File files : directory.listFiles())
                    result.add(getResume(files));
            } catch (Exception e) {
                throw new StorageException("ERROR: directory is not exist or empty", directory.getName(), e);
            }
        }
        return result;
    }

    @Override
    public void clear() {
        if (directory.listFiles() != null) {
            try {
                for (File files : directory.listFiles()) {
                    if (files.isFile()) {
                        deleteResume(files);
                    }
                }
            } catch (Exception e) {
                throw new StorageException("ERROR: directory is not exist", directory.getName(), e);
            }
        }
    }

    @Override
    public int size() {
        return getListedResumes().size();
    }
}
