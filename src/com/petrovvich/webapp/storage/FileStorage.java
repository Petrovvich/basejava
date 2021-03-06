package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.exception.StorageException;
import com.petrovvich.webapp.model.Resume;
import com.petrovvich.webapp.storage.serialization.ObjectStreamSerialization;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class FileStorage extends AbstractStorage<File> {

    private File directory;
    private ObjectStreamSerialization objectStreamSerialization;

    protected FileStorage(File directory, ObjectStreamSerialization objectStreamSerialization) {
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Directory " + directory.getAbsolutePath() + " isn't directory!");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException("Directory " + directory.getAbsolutePath() + " isn't readable/writeable!");
        }
        this.objectStreamSerialization = objectStreamSerialization;
    }

    @Override
    protected Resume getResume(File file) {
        try {
            return objectStreamSerialization.readData(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Can't get resume: ", file.getName(), e);
        }
    }

    @Override
    protected boolean checkIndex(File file) {
        return !file.exists();
    }

    @Override
    protected File getSearchIndex(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void deleteResume(File file) {
        if (!file.delete()) {
            throw new StorageException("Can't delete resume: ", file.getName());
        }
    }

    @Override
    protected void insertElement(File file, Resume resume) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Can't create file ", file.getAbsolutePath(), e);
        }
        updateElement(file, resume);
    }

    @Override
    protected void updateElement(File file, Resume resume) {
        try {
            objectStreamSerialization.writeData(new BufferedOutputStream(new FileOutputStream(file)), resume);
        } catch (IOException e) {
            throw new StorageException(resume.getUuid(), "File can't write", e);
        }
    }

    @Override
    protected List<Resume> getListedResumes() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException(null, "Directory is not exist or empty");
        }
        List<Resume> result = new ArrayList<>(files.length);
        if (directory.listFiles() != null) {
            for (File file : files)
                    result.add(getResume(file));
        }
        return result;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file: files) {
                deleteResume(file);
            }
        }
    }

    @Override
    public int size() {
        String[] list = directory.list();
        if (list == null) {
            throw new StorageException("Directory is empty ", null);
        }
        return list.length;
    }
}
