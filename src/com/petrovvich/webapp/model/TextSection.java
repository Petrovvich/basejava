package com.petrovvich.webapp.model;

import java.util.Objects;

public class TextSection extends Section {

    private final String descriptiom;

    public TextSection(String descriptiom) {
        this.descriptiom = descriptiom;
    }

    public String getDescriptiom() {
        return descriptiom;
    }

    @Override
    public String toString() {
        return "TextSection{" +
                "descriptiom='" + descriptiom + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextSection that = (TextSection) o;
        return Objects.equals(descriptiom, that.descriptiom);
    }

    @Override
    public int hashCode() {

        return Objects.hash(descriptiom);
    }
}
