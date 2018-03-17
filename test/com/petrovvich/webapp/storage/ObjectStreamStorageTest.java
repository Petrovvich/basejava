package com.petrovvich.webapp.storage;

public class ObjectStreamStorageTest extends AbstractStorageTest {

    public ObjectStreamStorageTest() {
        super(new ObjectStreamStorage(DIRECTORY));
    }
}