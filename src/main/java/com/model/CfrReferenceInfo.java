package com.model;

public class CfrReferenceInfo {
    private long wordCount;
    private long checksum;

    public CfrReferenceInfo(long wordCount, long checksum) {
        this.wordCount = wordCount;
        this.checksum = checksum;
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

    public void setChecksum(long checksum) {
        this.checksum = checksum;
    }
}
