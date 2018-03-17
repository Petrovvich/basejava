package com.petrovvich.webapp.storage;

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
        return null;
    }
}
