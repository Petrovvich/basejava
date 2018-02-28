package com.petrovvich.webapp.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class OrganizationSection extends Section {

    private final List<Organization> organizationsList;

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

    public class Organization {

        private final Link site;
        private final String title;
        private final String description;
        private final LocalDate fromDate;
        private final LocalDate toDate;

        public Organization(Link site, String title, String description, LocalDate fromDate, LocalDate toDate) {
            this.site = site;
            this.title = title;
            this.description = description;
            this.fromDate = fromDate;
            this.toDate = toDate;
        }

        @Override
        public String toString() {
            return "Organization{" +
                    "site=" + site +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", fromDate=" + fromDate +
                    ", toDate=" + toDate +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Organization that = (Organization) o;
            return Objects.equals(site, that.site) &&
                    Objects.equals(title, that.title) &&
                    Objects.equals(description, that.description) &&
                    Objects.equals(fromDate, that.fromDate) &&
                    Objects.equals(toDate, that.toDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(site, title, description, fromDate, toDate);
        }
    }

    public class Link {

        private final String name;
        private final String url;

        public Link(String name, String url) {
            this.name = name;
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }

        @Override
        public String toString() {
            return "Link{" +
                    "name='" + name + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Link link = (Link) o;
            return Objects.equals(name, link.name) &&
                    Objects.equals(url, link.url);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, url);
        }
    }
}
