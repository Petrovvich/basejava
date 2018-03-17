package com.petrovvich.webapp.storage;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {

    public ObjectStreamPathStorageTest() {
        super(new ObjectStreamPathStorage("C:\\Projects\\basejava\\storage") {
        });
    }
}