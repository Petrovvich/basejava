package com.petrovvich.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Position {

    private String name;
    private String description;
    private Organization organization;
    private LocalDate fromDate;
    private LocalDate toDate;

    public Position(String name, String description, Organization organization, LocalDate fromDate, LocalDate toDate) {
        this.name = name;
        this.description = description;
        this.organization = organization;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(name, position.name) &&
                Objects.equals(description, position.description) &&
                Objects.equals(organization, position.organization) &&
                Objects.equals(fromDate, position.fromDate) &&
                Objects.equals(toDate, position.toDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, description, organization, fromDate, toDate);
    }

    @Override
    public String toString() {
        return "Position{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", organization=" + organization +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
