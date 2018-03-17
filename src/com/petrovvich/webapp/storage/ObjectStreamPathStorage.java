package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.exception.StorageException;
import com.petrovvich.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class ObjectStreamPathStorage extends AbstractStorage<Path> {

    private Path directory;

    protected ObjectStreamPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must be not null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or not writeable/readable");
        }
        this.directory = directory;
    }

    @Override
    protected Resume getResume(Path path) {
        try {
            return readData(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Can't get Path: ", path.getFileName().toString(), e);
        }
    }

    @Override
    protected boolean checkIndex(Path path) {
        return Files.isRegularFile(path);
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
            writeData(new BufferedOutputStream(Files.newOutputStream(path)), resume);
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
        return directory.getNameCount();
    }

    protected abstract void writeData (OutputStream os, Resume resume) throws IOException;

    protected abstract Resume readData (InputStream is) throws IOException;
}
