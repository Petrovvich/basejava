package com.petrovvich.storage;

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
        if (sizeOfArray == 100000) {
            System.out.println("База резюме заполнена, удалите элементы, прежде чем вставлять новые");
        } else {
            storage[sizeOfArray] = r;
            sizeOfArray++;
        }
    }

    Resume get(String uuid) {
        Resume result = null;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && storage[i].toString().equals(uuid)) {
                result = storage[i];
            }
        }
        return result;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && storage[i].toString().equals(uuid)) {
                storage[i] = null;
                sizeOfArray--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] result = new Resume[sizeOfArray];
        sizeOfArray = 0;
        for (Resume r: storage) {
            if(r != null) {
                result[sizeOfArray] = r;
                sizeOfArray++;
            }
        }
        return result;
    }

    int size() {
        return sizeOfArray;
    }
}