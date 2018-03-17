package com.petrovvich.webapp.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Link site;
    private List<Position> positions;

    public Organization(String name, String url, Position...positions) {
        this(new Link(name, url), Arrays.asList(positions));
    }

    public Organization(Link site, List<Position> position) {
        this.site = site;
        this.positions = position;
    }

    public void setPositions(List<Position> pos) {
        this.positions = pos;
    }

    public List<Position> getPositions() {
        return positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(site, that.site) &&
                Objects.equals(positions, that.positions);
    }

    @Override
    public int hashCode() {

        return Objects.hash(site, positions);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "site=" + site +
                ", positions=" + positions +
                '}';
    }
}
