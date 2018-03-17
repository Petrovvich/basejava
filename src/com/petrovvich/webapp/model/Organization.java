package com.petrovvich.webapp.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Link site;
    private final String title;
    private final String description;
    private List<Position> positions;

    public Organization(Link site, String title, String description, List<Position> position) {
        this.site = site;
        this.title = title;
        this.description = description;
        this.positions = position;
    }

    public void setPositions(List<Position> pos) {
        this.positions = pos;
    }

    public List<Position> getPositions() {
        return positions;
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
