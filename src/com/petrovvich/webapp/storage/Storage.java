package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.model.Resume;

import java.util.List;

public interface Storage {

    void clear();

    void update(Resume resume);

    void save(Resume r);

    Resume get(String uuid);

    void delete(String uuid);

    List<Resume> getAllSorted();

    int size();
}
