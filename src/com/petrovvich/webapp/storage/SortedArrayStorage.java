package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {


    protected void insertElement(Resume r, int index) {
        /**
         * Метод интересный, но нерабочий.
         * При вставке двухзначных и более значений сортировка ведется по первому символу и может получиться так, что
         * массив будет отсортирован в порядке [11, 17, 4, 5, 5556, 5557, 6]  или [11, 4, 5] если вставлять по порядку.
         * int indexToInsert = - index - 1;
         * System.arraycopy(storage, indexToInsert, storage, indexToInsert + 1, sizeOfArray - indexToInsert);
         * storage[indexToInsert] = r;
         */
        storage[sizeOfArray] = r;
        if (sizeOfArray> 0) {
            Arrays.sort(storage, 0, sizeOfArray + 1);
        }

    }

    @Override
    protected void deleteElement(int index) {
        int elementsToMove = sizeOfArray - index - 1;
        System.arraycopy(storage, index + 1, storage, index, elementsToMove);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, sizeOfArray, resume);
    }
}
