package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.model.Resume;

public interface Storage {

    void clear();

    void update(Resume resume);

    void save(Resume r);

    Resume get(String uuid);

    void delete(String uuid);

    void getAll();

    int size();
}
