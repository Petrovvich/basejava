package com.petrovvich.webapp.storage;

import org.junit.Test;

import static org.junit.Assert.*;

public abstract class SortedArrayStorageTest extends AbstractArrayStorageTest {

    public SortedArrayStorageTest(Storage storage) {
        super(new SortedArrayStorage());
    }
}