package com.petrovvich.webapp.storage.serialization;

import com.petrovvich.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Serialization {

    void writeData(OutputStream os, Resume resume) throws IOException;

    Resume readData(InputStream is) throws IOException;
}
