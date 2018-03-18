package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.exception.StorageException;
import com.petrovvich.webapp.model.Resume;
import com.petrovvich.webapp.storage.serialization.SerializationStrategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ObjectStreamPathStorage extends AbstractStorage<Path> {

    private Path directory;
    private SerializationStrategy serializationStrategy;

    protected ObjectStreamPathStorage(String dir, SerializationStrategy serializationStrategy) {
        this.serializationStrategy = serializationStrategy;
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must be not null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or not writeable/readable");
        }
    }

    @Override
    protected Resume getResume(Path path) {
        try {
            return serializationStrategy.readData(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Can't get Path: ", path.getFileName().toString(), e);
        }
    }

    @Override
    protected boolean checkIndex(Path path) {
        return !Files.isRegularFile(path);
    }

    @Override
    protected Path getSearchIndex(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected void deleteResume(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Can't delete Path: ", path.getFileName().toString(), e);
        }
    }

    @Override
    protected void insertElement(Path path, Resume resume) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Can't save Path: ", path.getFileName().toString(), e);
        }
        updateElement(path, resume);
    }

    @Override
    protected void updateElement(Path path, Resume resume) {
        try {
            serializationStrategy.writeData(new BufferedOutputStream(Files.newOutputStream(path)), resume);
        } catch (IOException e) {
            throw new StorageException("Can't update Path: ", path.getFileName().toString(), e);
        }
    }

    @Override
    protected List<Resume> getListedResumes() {
        try {
            return Files.list(directory).map(this::getResume).collect(Collectors.toList());
        } catch (IOException e) {
            throw new StorageException("Can't get all elements for directory: ", directory.getFileName().toString(), e);
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::deleteResume);
        } catch (IOException e) {
            throw new StorageException("Can't read directory", null);
        }
    }

    @Override
    public int size() {
        try {
            return (int) Files.list(directory).count();
        } catch (IOException e) {
            throw new StorageException("Can't get count of elements for directory: ", directory.getFileName().toString(), e);
        }
    }
}
