package com.petrovvich.webapp.storage;

import java.util.Arrays;
import com.petrovvich.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    private static final int STORAGE_CAPACITY = 10000;

    private Resume[] storage = new Resume[STORAGE_CAPACITY];

    private int sizeOfArray = 0;

    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("Такое резюме уже есть в базе!");
        } else if (sizeOfArray == STORAGE_CAPACITY) {
            System.out.println("База резюме заполнена, удалите элементы, прежде чем вставлять новые!");
        } else {
            storage[sizeOfArray] = r;
            sizeOfArray++;
        }
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("Резюме не найдено");
        } else {
            storage[index] = resume;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Такого резюме нет в базе");
        } else {
            storage[index] = storage[sizeOfArray - 1];
            storage[sizeOfArray - 1] = null;
            sizeOfArray--;
        }
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < sizeOfArray; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}