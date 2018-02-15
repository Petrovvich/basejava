package com.petrovvich.webapp.model;

import java.util.Objects;
import java.util.UUID;

public class Resume {

    // Unique identifier
    private final String uuid;
    private String fullname;

    public Resume(String uuid) {
        this.uuid = uuid;
    }

    public Resume() {
        this(UUID.randomUUID().toString());
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", fullname='" + fullname + '\'' +
                '}';
    }

    public String getFullname() {
        return fullname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullname, resume.fullname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uuid, fullname);
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}