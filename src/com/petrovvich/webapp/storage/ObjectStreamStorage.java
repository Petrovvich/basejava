package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.storage.serialization.SerializationStrategy;

import java.io.*;

public class ObjectStreamStorage extends FileStorage {

    protected ObjectStreamStorage(File directory) {
        super(directory, new SerializationStrategy());
    }
}