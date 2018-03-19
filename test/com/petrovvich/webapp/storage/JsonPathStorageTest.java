package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.storage.serialization.JsonSerialization;

public class JsonPathStorageTest extends AbstractStorageTest {

    public JsonPathStorageTest() {
        super(new PathStorage(DIRECTORY.getAbsolutePath(), new JsonSerialization()));
    }
}