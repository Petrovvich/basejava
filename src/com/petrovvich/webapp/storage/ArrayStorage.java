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
        for (int i = 0; i < sizeOfArray; i++) {
            if (storage[i].toString().equals(r.getUuid())) {
                storage[i].setUuid(r.getUuid());
                break;
            } else {
                System.out.println("Резюме не найдено");
            }
        }
    }

    private boolean findResume (Resume r) {
        for (int i = 0; i < sizeOfArray; i++) {
            if (storage[i] != null && storage[i].toString().equals(r.getUuid())) {
                return true;
            }
        }
        return false;
    }

    public void save(Resume r) {
        if (sizeOfArray == storage.length) {
            System.out.println("База резюме заполнена, удалите элементы, прежде чем вставлять новые!");
        } else if (findResume(r)) {
            System.out.println("Такое резюме уже есть в базе!");
        } else {
            storage[sizeOfArray] = r;
            sizeOfArray++;
        }
    }

    public Resume get(String uuid) {
        Resume result = null;
        for (int i = 0; i < sizeOfArray; i++) {
            if (storage[i] != null && storage[i].toString().equals(uuid)) {
                result = storage[i];
                break;
            }
        }
        return result;
    }

    public void delete(String uuid) {
        for (int i = 0; i < sizeOfArray; i++) {
            if (storage[i] != null && storage[i].toString().equals(uuid)) {
                storage[i] = storage[sizeOfArray - 1];
                storage[sizeOfArray - 1] = null;
                sizeOfArray--;
            }
        }
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