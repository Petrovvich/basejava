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
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                sizeOfArray++;
                break;
            }
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
        return getAll().length;
    }
}