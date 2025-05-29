package com.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CfrVersionInfo {
    @JsonProperty("content_versions")
    List<ContentVersion> contentVersions;

    Meta meta;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<ContentVersion> getContentVersions() {
        return contentVersions;
    }

    public void setContentVersions(final List<ContentVersion> contentVersions) {
        this.contentVersions = contentVersions;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Meta {
        @JsonProperty("latest_issue_date")
        Date latestIssueDate;

        public Date getLatestIssueDate() {
            return latestIssueDate;
        }

        public void setLatestIssueDate(Date latestIssueDate) {
            this.latestIssueDate = latestIssueDate;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ContentVersion {
        Date date;
        String name;

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
