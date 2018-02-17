package com.petrovvich.webapp.model;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Resume {

    // Unique identifier
    private final String uuid;
    private String fullname;

    private final Map<Contacts, String> contacts = new EnumMap<>(Contacts.class);

    private Personal personal;
    private Objective objective;
    private Achievement achievements;
    private Qualification qualifications;
    private Experience experience;
    private Education education;

    public Resume(String uuid) {
        this.uuid = uuid;
    }

    public Resume() {
        this(UUID.randomUUID().toString());
    }

    public String getUuid() {
        return uuid;
    }

    public String getContact(Contacts contactType) {
        return contacts.get(contactType);
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