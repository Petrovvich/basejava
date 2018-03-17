package com.petrovvich.webapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.petrovvich.webapp.util.DateUtil.NOW;
import static com.petrovvich.webapp.util.DateUtil.of;

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

    public static class Position implements Serializable {

        private static final long serialVersionUID = 1L;

        private String name;
        private String description;
        private LocalDate fromDate;
        private LocalDate toDate;

        public Position(int startYear, Month startMonth, String name, String description) {
            this(of(startYear, startMonth), NOW, name, description);
        }

        public Position(int startYear, Month startMonth, int endYear, Month endMonth, String name, String description) {
            this(of(startYear, startMonth), of(endYear, endMonth), name, description);
        }

        public Position(LocalDate fromDate, LocalDate toDate, String name, String description) {
            this.fromDate = fromDate;
            this.toDate = toDate;
            this.name = name;
            this.description = description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return Objects.equals(name, position.name) &&
                    Objects.equals(description, position.description) &&
                    Objects.equals(fromDate, position.fromDate) &&
                    Objects.equals(toDate, position.toDate);
        }

        @Override
        public int hashCode() {

            return Objects.hash(name, description, fromDate, toDate);
        }

        @Override
        public String toString() {
            return "Position{" +
                    "name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", fromDate=" + fromDate +
                    ", toDate=" + toDate +
                    '}';
        }
    }
}
