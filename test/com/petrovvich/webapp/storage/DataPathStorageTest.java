package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.storage.serialization.DataStreamSerialization;

public class DataPathStorageTest extends AbstractStorageTest {

    public DataPathStorageTest() {
        super(new PathStorage(DIRECTORY.getAbsolutePath(), new DataStreamSerialization()));
    }
}