package com.petrovvich.webapp.storage;

import java.util.Arrays;
import com.petrovvich.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int sizeOfArray = 0;

    public void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
        sizeOfArray = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index == -1) {
            System.out.println("Резюме не найдено");
        } else {
            storage[index] = r;
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("Такое резюме уже есть в базе!");
        } else if (sizeOfArray == storage.length) {
            System.out.println("База резюме заполнена, удалите элементы, прежде чем вставлять новые!");
        } else {
            storage[sizeOfArray] = r;
            sizeOfArray++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Такого резюме нет в базе");
            return null;
        }
        return storage[index];
    }

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

    private int getIndex(String uuid) {
        for (int i = 0; i < sizeOfArray; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, sizeOfArray);
    }

    public int size() {
        return sizeOfArray;
    }
}