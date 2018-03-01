package com.petrovvich.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

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
