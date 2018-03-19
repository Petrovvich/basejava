package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.storage.serialization.ObjectStreamSerialization;

public class PathStorageTest extends AbstractStorageTest {

    public PathStorageTest() {
        super(new PathStorage("C:\\Projects\\basejava\\storage", new ObjectStreamSerialization()));
    }
}