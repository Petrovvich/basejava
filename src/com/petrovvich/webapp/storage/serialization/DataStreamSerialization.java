package com.petrovvich.webapp.storage.serialization;

import com.petrovvich.webapp.model.*;
import com.petrovvich.webapp.storage.AbstractStorage;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class DataStreamSerialization implements Serialization {

    private static final Logger LOGGER = Logger.getLogger(AbstractStorage.class.getName());

    @Override
    public void writeData(OutputStream os, Resume resume) throws IOException {
        try(DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullname());

            Map <Contacts, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<Contacts, String> entry : resume.getContacts().entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, Section> sectionMap = resume.getSections();
            dos.writeInt(sectionMap.size());
            for (Map.Entry<SectionType, Section> entry : resume.getSections().entrySet()) {
                SectionType sectionType = entry.getKey();
                Section section = entry.getValue();
                dos.writeUTF(sectionType.name());

                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) section).getdescription());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> listSection = ((ListSection)section).getElements();
                        dos.writeInt(listSection.size());
                        LOGGER.info("Writing listSize is: " + listSection.size());
                        for (String s : listSection) {
                            dos.writeUTF(s);
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizationList = ((OrganizationSection)section).getOrganizationsList();
                        dos.writeInt(organizationList.size());
                        for (Organization o : organizationList) {
                            List<Organization.Position> positions = new ArrayList<>();
                            dos.writeInt(positions.size());
                            if (positions.size() > 0) {
                                dos.writeUTF(o.getSite().getName());
                                dos.writeUTF(o.getSite().getUrl());
                                for (Organization.Position p : positions) {
                                    dos.writeUTF(p.getDescription() == null ? "" : p.getDescription());
                                    dos.writeUTF(p.getName());
                                    dos.writeUTF(String.valueOf(p.getFromDate()));
                                    dos.writeUTF(String.valueOf(p.getToDate()));
                                }
                            }
                        }
                        break;
                }
            }
        }
    }

    @Override
    public Resume readData(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                Contacts contacts = Contacts.valueOf(dis.readUTF());
                resume.setContact(contacts, dis.readUTF());
            }

            int sizeSections = dis.readInt();
            LOGGER.info("Reading sizeSections is: " + sizeSections);
            for (int i = 0; i < sizeSections; i++) {
            SectionType sectionType = SectionType.valueOf(dis.readUTF());
            LOGGER.info("Section types is: " + sectionType);
            switch(sectionType) {
                case PERSONAL:
                case OBJECTIVE:
                    resume.setSection(sectionType, new TextSection(dis.readUTF()));
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    int listSize = dis.readInt();
                    List<String> strings = new ArrayList<>(listSize);
                    for (int j = 0; j < listSize; j++) {
                        LOGGER.info("Reading listSize is: " + listSize);
                        strings.add(dis.readUTF());
                    }
                    resume.setSection(sectionType, new ListSection(strings));
                    break;
                case EXPERIENCE:
                case EDUCATION:
                    int organizationList = dis.readInt();
                    LOGGER.info("organizationList is: " + organizationList);
                    List<Organization> organizations = new ArrayList<>(organizationList);
                    if (organizationList > 0 ) {
                        for (int j = 0; j < organizationList; j++) {
                            int positionsList = dis.readInt();
                            LOGGER.info("positionsList is: " + positionsList);
                            if (positionsList > 0) {
                                String linkDesc = dis.readUTF();
                                String linkUrl = dis.readUTF();
                                List<Organization.Position> positions = new ArrayList<>();
                                for (int k = 0; k < positionsList; k++) {
                                    String description = dis.readUTF();
                                    String name = dis.readUTF();
                                    LocalDate fromDate = LocalDate.parse(dis.readUTF());
                                    LocalDate toDate = LocalDate.parse(dis.readUTF());
                                    positions.add(new Organization.Position(fromDate, toDate, description, name));
                                }
                                organizations.add(new Organization(new Link(linkDesc, linkUrl), positions));
                            }
                        }
                    }
                        resume.setSection(sectionType, new OrganizationSection(organizations));
                    break;
                }
            }
            return resume;
        }
    }
}
