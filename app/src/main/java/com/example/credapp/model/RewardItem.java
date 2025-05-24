package com.example.credapp.model;

public class RewardItem {
    private String title;
    private String value;
    private String subtitle;
    private boolean hasSubtitle;

    public RewardItem(String title, String value) {
        this.title = title;
        this.value = value;
        this.hasSubtitle = false;
    }

    public RewardItem(String title, String value, String subtitle) {
        this.title = title;
        this.value = value;
        this.subtitle = subtitle;
        this.hasSubtitle = true;
    }

    public String getTitle() {
        return title;
    }

    public String getValue() {
        return value;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public boolean hasSubtitle() {
        return hasSubtitle;
    }
}