package com.petrovvich.storage;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        Resume result = null;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                if (storage[i].toString().equals(uuid)) {
                    result = storage[i];
                }
            }
        }
        return result;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                if (storage[i].toString().equals(uuid)) {
                    storage[i] = null;
                    break;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int count = 0;
        for (Resume r: storage) {
            if(r != null) {
                count++;
            }
        }
        Resume[] result = new Resume[count];
        count = 0;
        for (Resume r: storage) {
            if(r != null) {
                result[count] = r;
                count++;
            }
        }
        return result;
    }

    int size() {
        return storage.length;
    }


    public static void main(String[] args) {

    }
}