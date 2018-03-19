package com.petrovvich.webapp.model;

import com.petrovvich.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.petrovvich.webapp.util.DateUtil.NOW;
import static com.petrovvich.webapp.util.DateUtil.of;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    private Link site;
    private List<Position> positions;

    public Organization() {}

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
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Position implements Serializable {

        private static final long serialVersionUID = 1L;

        private String name;
        private String description;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate fromDate;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate toDate;

        public Position() {}

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

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public LocalDate getFromDate() {
            return fromDate;
        }

        public LocalDate getToDate() {
            return toDate;
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
