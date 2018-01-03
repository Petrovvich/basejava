package com.petrovvich.storage;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int sizeOfArray = 0;

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
        sizeOfArray = 0;
    }

    void save(Resume r) {
        if (sizeOfArray == storage.length) {
            System.out.println("База резюме заполнена, удалите элементы, прежде чем вставлять новые");
        } else {
            storage[sizeOfArray] = r;
            sizeOfArray++;
        }
    }

    Resume get(String uuid) {
        Resume result = null;
        for (int i = 0; i < sizeOfArray; i++) {
            if (storage[i] != null && storage[i].toString().equals(uuid)) {
                result = storage[i];
                break;
            }
        }
        return result;
    }

    void delete(String uuid) {
        for (int i = 0; i < sizeOfArray; i++) {
            if (storage[i] != null && storage[i].toString().equals(uuid)) {
                storage[i] = null;
                sizeOfArray--;
            }
            if (storage[i] == null){
                Resume temp = storage[i];
                storage[i]=storage[i+1];
                storage[i+1] = temp;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, sizeOfArray);
    }

    int size() {
        return sizeOfArray;
    }
}