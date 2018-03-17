package com.petrovvich.webapp.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OrganizationSection extends Section {

    private final List<Organization> organizationsList;

    public OrganizationSection(Organization... organizations) {
        this(Arrays.asList(organizations));
    }

    public OrganizationSection(List<Organization> organizationsList) {
        this.organizationsList = organizationsList;
    }

    public List<Organization> getOrganizationsList() {
        return organizationsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return Objects.equals(organizationsList, that.organizationsList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(organizationsList);
    }

    @Override
    public String toString() {
        return "OrganizationSection{" +
                "organizationsList=" + organizationsList +
                '}';
    }
}
