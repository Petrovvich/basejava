package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.storage.serialization.XmlSerialization;

public class XmlPathStorageTest extends AbstractStorageTest {

    public XmlPathStorageTest() {
        super(new PathStorage(DIRECTORY.getAbsolutePath(), new XmlSerialization()));
    }
}