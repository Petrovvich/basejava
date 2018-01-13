package com.petrovvich.webapp.storage;

import org.junit.Test;

import static org.junit.Assert.*;

public abstract class ArrayStorageTest extends AbstractArrayStorageTest {

    public ArrayStorageTest(Storage storage) {
        super(new ArrayStorage());
    }
}