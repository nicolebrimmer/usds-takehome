package com.model;

import java.util.Date;
import java.util.TreeMap;

public class CfrReferenceInfo {
    private long wordCount;
    private long checksum;
    private long ageInDays;
    private TreeMap<Date, String> versionHistory;

    public CfrReferenceInfo(final long wordCount, final long checksum, final long ageInDays, final TreeMap<Date, String> versionHistory) {
        this.wordCount = wordCount;
        this.checksum = checksum;
        this.ageInDays = ageInDays;
        this.versionHistory = versionHistory;
    }

    public long getWordCount() {
        return wordCount;
    }

    public void setWordCount(final long wordCount) {
        this.wordCount = wordCount;
    }

    public long getChecksum() {
        return checksum;
    }

    public void setChecksum(final long checksum) {
        this.checksum = checksum;
    }

    public long getAgeInDays() {
        return ageInDays;
    }

    public void setAgeInDays(final long ageInDays) {
        this.ageInDays = ageInDays;
    }

    public TreeMap<Date, String> getVersionHistory() {
        return versionHistory;
    }

    public void setVersionHistory(final TreeMap<Date, String> versionHistory) {
        this.versionHistory = versionHistory;
    }
}
