package com.petrovvich.webapp.model;

import java.util.Objects;

public class Organization {

    private final Link site;
    private final String title;
    private final String description;
    private final Position[] position;

    public Organization(Link site, String title, String description, Position... position) {
        this.site = site;
        this.title = title;
        this.description = description;
        this.position = position;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "site=" + site +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(site, that.site) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(site, title, description);
    }
}
