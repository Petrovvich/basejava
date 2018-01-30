package com.petrovvich.webapp.storage;


import com.petrovvich.webapp.exception.StorageException;
import com.petrovvich.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void storageOverloaded() {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_CAPACITY; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume());
    }

}