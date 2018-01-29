package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.model.Resume;
import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator RESUME_COMPARATOR = new Comparator<Resume>() {
        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    };

    protected void insertElement(Resume r, int index) {
        int indexToInsert = - index - 1;
        System.arraycopy(storage, indexToInsert, storage, indexToInsert + 1, sizeOfArray - indexToInsert);
        storage[indexToInsert] = r;
    }

    @Override
    protected void deleteElement(int index) {
        int elementsToMove = sizeOfArray - index - 1;
        System.arraycopy(storage, index + 1, storage, index, elementsToMove);
    }

    @Override
    protected Integer getSearchIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, sizeOfArray, resume, RESUME_COMPARATOR);
    }
}
