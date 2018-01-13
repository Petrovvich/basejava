package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.exception.NotExistStorageException;
import com.petrovvich.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class AbstractArrayStorageTest {

    private Storage storage = new ArrayStorage();

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        Assert.assertEquals(3, storage.size());

    }

    @Test
    public void save() throws Exception {
        Assert.assertEquals(3, storage.size());
        Resume testResume = new Resume("123456");
        storage.save(testResume);
        Assert.assertEquals(4, storage.size());
        Assert.assertSame(testResume, storage.get("123456"));
    }

    @Test
    public void get() throws Exception {
        String find = "uuid1";
        Assert.assertSame(find, storage.get("uuid1").toString());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("putin");
    }

    @Test
    public void delete() throws Exception {
        Assert.assertEquals(3, storage.size());
        storage.delete("uuid1");
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void getAll() throws Exception {
        Resume[] result = storage.getAll();
        Assert.assertArrayEquals(result, storage.getAll());
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

}