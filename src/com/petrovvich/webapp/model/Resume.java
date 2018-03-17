package com.petrovvich.webapp.model;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Resume implements Serializable {

    private static final long serialVersionUID = 1L;
    // Unique identifier
    private final String uuid;
    private String fullname;
    private final Map<Contacts, String> contacts = new EnumMap<>(Contacts.class);
    private final Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

    public Resume(String uuid) {
        this.uuid = uuid;
    }

    public Resume() {
        this(UUID.randomUUID().toString());
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullname = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getContact(Contacts contactType) {
        return contacts.get(contactType);
    }

    public void setContact (Contacts contact, String description) {
        contacts.put(contact, contact.getTitle());
    }

    public Section getSection(SectionType sectionType) {
        return sections.get(sectionType);
    }

    public void setSection (SectionType sectionType, Section section) {
        sections.put(sectionType, section);
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