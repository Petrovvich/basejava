package com.petrovvich.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uuid;
    private String fullname;
    private final Map<Contacts, String> contacts = new EnumMap<>(Contacts.class);
    private final Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

    public Resume() {}

    public Resume(String fullname) {
        this(UUID.randomUUID().toString(), fullname);
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



    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullname, resume.fullname) &&
                Objects.equals(contacts, resume.contacts) &&
                Objects.equals(sections, resume.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullname, contacts, sections);
    }
}