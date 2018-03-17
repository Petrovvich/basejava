package com.petrovvich.webapp.storage.serialization;

import com.petrovvich.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SerializationContext implements SerializationStrategy {

    @Override
    public void writeData(OutputStream os, Resume resume) throws IOException {

    }

    @Override
    public Resume readData(InputStream is) throws IOException {
        return null;
    }
}
