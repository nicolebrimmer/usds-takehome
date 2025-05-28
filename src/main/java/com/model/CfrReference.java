package com.model;

public class CfrReference {

    String title = "";
    String chapter = "";
    String subtitle = "";
    String part = "";
    String subchapter = "";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(final String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getSubchapter() {
        return subchapter;
    }

    public void setSubchapter(String subchapter) {
        this.subchapter = subchapter;
    }

    @Override
    public String toString() {
        return String.format("Title: %s\nChapter: %s\nSubtitle: %s\nPart: %s\nSubchapter: %s", title, chapter, subtitle, part, subchapter);
    }
}
