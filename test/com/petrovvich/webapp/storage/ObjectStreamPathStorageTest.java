package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.storage.serialization.SerializationContext;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {

    public ObjectStreamPathStorageTest() {
        super(new ObjectStreamPathStorage("C:\\Projects\\basejava\\storage", new SerializationContext()));
    }
}