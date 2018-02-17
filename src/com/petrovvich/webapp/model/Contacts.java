package com.petrovvich.webapp.model;

public enum Contacts {

    MOBILE_PHONE("Мобильный телефон"),
    WORK_PHONE("Рабочий телефон"),
    FACEBOOK("Профиль в Facebook"),
    LINKEDIN("Профиль в LinkedIn"),
    EMAIL("Почта"),
    TWITTER("Профиль в Twitter"),
    SKYPE("Skype"),
    GITHUB("Профиль на GitHub");

    private String title;

    Contacts(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
