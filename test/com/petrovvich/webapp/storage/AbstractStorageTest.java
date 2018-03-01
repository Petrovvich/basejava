package com.petrovvich.webapp.storage;

import com.petrovvich.webapp.exception.ExistStorageException;
import com.petrovvich.webapp.exception.NotExistStorageException;
import com.petrovvich.webapp.model.*;
import com.petrovvich.webapp.util.DateUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;


public abstract class AbstractStorageTest {

    protected Storage storage;

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final Resume resume1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume resume2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume resume3 = new Resume(UUID_3);
    private static final List<String> achievements = new ArrayList<>();
    private static final List<String> qualifications = new ArrayList<>();
    private static final List<Organization> experience = new ArrayList<>();
    private static final List<Organization> education = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(resume1);
        resume1.setFullname("Robert De Niro");
        storage.save(resume2);
        resume2.setFullname("Al Pachino");
        storage.save(resume3);
        resume3.setFullname("Mark Dacascos");
        achievements.add("achievement 1");
        achievements.add("achievement 2");
        achievements.add("achievement 3");
        achievements.add("achievement 3");
        achievements.add("achievement 3");
        qualifications.add("qualification 1");
        qualifications.add("qualification 2");
        qualifications.add("qualification 3");
        qualifications.add("qualification 4");
        qualifications.add("qualification 5");
        qualifications.add("qualification 6");
        experience.add(new Organization(new Link("site one", "http//siteone.com"),
                "site one",
                "site one", new Position("Manager", "Management", DateUtil.of(2011, Month.JANUARY),
                DateUtil.of(2013, Month.FEBRUARY))));
        experience.add(new Organization(new Link("site two", "http//sitetwo.com"),
                "site two",
                "site two", new Position("Manager", "Management", DateUtil.of(2013, Month.FEBRUARY),
                DateUtil.of(2015, Month.MAY))));
        education.add(new Organization(new Link("Massachusetts Institute of Technology: MIT", "http//http://web.mit.edu/"),
                "MIT",
                "Massachusetts Institute of Technology: MIT",
                new Position("Manager", "Management",  DateUtil.of(2008, Month.SEPTEMBER),
                        DateUtil.of(2011, Month.MAY))));
        resume1.setContact(Contacts.EMAIL);
        resume1.setContact(Contacts.FACEBOOK);
        resume1.setContact(Contacts.GITHUB);
        resume1.setContact(Contacts.LINKEDIN);
        resume1.setContact(Contacts.MOBILE_PHONE);
        resume1.setContact(Contacts.SKYPE);
        resume1.setContact(Contacts.TWITTER);
        resume1.setContact(Contacts.WORK_PHONE);
        resume1.setSection(SectionType.PERSONAL, new TextSection("Личные качества"));
        resume1.setSection(SectionType.OBJECTIVE, new TextSection("Позиция"));
        resume1.setSection(SectionType.ACHIEVEMENT, new ListSection(achievements));
        resume1.setSection(SectionType.QUALIFICATIONS, new ListSection(qualifications));
        resume1.setSection(SectionType.EXPERIENCE, new OrganizationSection(experience));
        resume1.setSection(SectionType.EDUCATION, new OrganizationSection(education));
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() throws Exception {
        assertSize(3);
        Resume resume = new Resume(UUID_1);
        storage.update(resume);
        assertSize(3);
        Assert.assertSame(storage.get(UUID_1), resume);
    }

    @Test
    public void save() throws Exception {
        assertSize(3);
        Resume testResume = new Resume("123456");
        storage.save(testResume);
        assertSize(4);
        Assert.assertSame(testResume, storage.get("123456"));
    }

    @Test
    public void get() throws Exception {
        Assert.assertSame(resume1, storage.get(resume1.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExistResume() throws Exception {
        storage.get("putin");
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistResume() throws Exception {
        storage.save(resume1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        assertSize(3);
        storage.delete("uuid1");
        assertSize(2);
        storage.get("uuid1");
    }

    @Test
    public void getAll() throws Exception {
        List<Resume> result = new ArrayList<>();
        Resume resume1 = new Resume(UUID_1);
        resume1.setFullname("Robert De Niro");
        result.add(resume1);
        Resume resume2 = new Resume(UUID_2);
        resume2.setFullname("Al Pachino");
        result.add(resume2);
        Resume resume3 = new Resume(UUID_3);
        resume3.setFullname("Mark Dacascos");
        result.add(resume3);
        result.sort(Comparator.comparing(Resume::getUuid));
        Assert.assertEquals(result, storage.getAllSorted());
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}