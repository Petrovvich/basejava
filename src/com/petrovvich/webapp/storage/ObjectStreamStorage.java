package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.exception.StorageException;
import com.petrovvich.webapp.model.Resume;

import java.io.*;

public class ObjectStreamStorage extends AbstractFileStorage {

    protected ObjectStreamStorage(File directory) {
        super(directory);
    }

    @Override
    protected void writeData(OutputStream os, Resume resume) throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(resume);
        }
    }

    @Override
    protected Resume readData(InputStream is) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
                return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
                throw new StorageException("Error read Resume", null, e);
        }
    }
}