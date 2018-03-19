package com.petrovvich.webapp.storage.serialization;

import com.petrovvich.webapp.model.*;
import com.petrovvich.webapp.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlSerialization implements Serialization {

    private XmlParser xmlParser;

    public XmlSerialization() {
        xmlParser = new XmlParser(Resume.class, Organization.class, Link.class,
                OrganizationSection.class, TextSection.class, ListSection.class, Organization.Position.class);
    }

    @Override
    public void writeData(OutputStream os, Resume resume) throws IOException {
        try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            xmlParser.marshall(resume, w);
        }
    }

    @Override
    public Resume readData (InputStream is) throws IOException {
        try (Reader r = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshall(r);
        }
    }
}
