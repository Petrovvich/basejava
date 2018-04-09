package com.petrovvich.webapp.storage.serialization;

import com.petrovvich.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerialization implements Serialization {

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
                //for Text Sections
                if (sectionType == SectionType.PERSONAL || sectionType == SectionType.OBJECTIVE) {
                    dos.writeUTF(((TextSection) section).getdescription());
                }
                //for List Sections
                if (sectionType == SectionType.ACHIEVEMENT || sectionType == SectionType.QUALIFICATIONS) {
                    List<String> listSection = ((ListSection)section).getElements();
                    dos.writeInt(listSection.size());
                    for (String s : listSection) {
                        dos.writeUTF(s);
                    }
                }
                //for Organization sections
                if (sectionType == SectionType.EXPERIENCE || sectionType == SectionType.EDUCATION) {
                    List<Organization> organizationList = ((OrganizationSection)section).getOrganizationsList();
                    dos.writeInt(organizationList.size());
                    for (Organization o : organizationList) {
                        List<Organization.Position> positions = new ArrayList<>();
                        dos.writeInt(positions.size());
                        for (Organization.Position p : positions) {
                            dos.writeUTF(p.getDescription());
                            dos.writeUTF(p.getName());
                            dos.writeUTF(String.valueOf(p.getFromDate()));
                            dos.writeUTF(String.valueOf(p.getToDate()));
                        }
                    }
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
            for (int i = 0; i < sizeSections; i++) {
            SectionType sectionType = SectionType.valueOf(dis.readUTF());
            //for Text Sections
            if (sectionType == SectionType.PERSONAL || sectionType == SectionType.OBJECTIVE) {
                resume.setSection(sectionType, new TextSection(dis.readUTF()));
            }
            //for List Sections
            if (sectionType == SectionType.ACHIEVEMENT || sectionType == SectionType.QUALIFICATIONS) {
                int listSize = dis.readInt();
                for (int j = 0; j < listSize; j++) {
                    resume.setSection(sectionType, new ListSection(dis.readUTF()));
                }
            }
            //for Organization sections
            if (sectionType == SectionType.EXPERIENCE || sectionType == SectionType.EDUCATION) {
                int organizationList = dis.readInt();
                List<Organization> organizations = new ArrayList<>();
                for (int j = 0; j < organizationList; j++) {
                    int positionsList = dis.readInt();
                    List<Organization.Position> positions = new ArrayList<>();
                    for (int k = 0; k < positionsList; k++) {
                        String description = dis.readUTF();
                        String name = dis.readUTF();
                        LocalDate fromDate = LocalDate.parse(dis.readUTF());
                        LocalDate toDate = LocalDate.parse(dis.readUTF());
                        positions.add(new Organization.Position(fromDate, toDate, description, name);
                    }
                    organizations.add(new Organization(positions));
                }
                resume.setSection(sectionType, new OrganizationSection(organizations));
            }
            }
            return resume;
        }
    }
}
