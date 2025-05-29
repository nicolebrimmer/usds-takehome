package com.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Agency {

    String name;
    String slug;

    @JsonProperty("cfr_references")
    List<CfrReference> cfrReferences = new ArrayList<CfrReference>();

    List<Agency> children = new ArrayList<Agency>();

    @JsonIgnore
    long wordCount = -1;

    @JsonIgnore
    Long checksum = null;

    /* Sum of the age of all current regulations associated with this agency, weighted by the
    * regulation's word count. */
    @JsonIgnore
    long weightedAgeOfRegulations = 0;

    @JsonIgnore
    TreeMap<Date, String> versionHistory = new TreeMap<Date, String>();

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(final String slug) {
        this.slug = slug;
    }

    public List<CfrReference> getCfrReferences() {
        return cfrReferences;
    }

    public void setCfrReferences(final List<CfrReference> cfrReferences) {
        this.cfrReferences = cfrReferences;
    }

    public List<Agency> getChildren() {
        return children;
    }

    public void setChildren(final List<Agency> children) {
        this.children = children;
    }

    public long getWordCount() {
        return this.wordCount;
    }

    public void setWordCount(final long wordCount) {
        this.wordCount = wordCount;
    }

    public Long getChecksum() {
        return this.checksum;
    }

    public void setChecksum(final Long checksum) {
        this.checksum = checksum;
    }

    public long getWeightedAgeOfRegulations() {
        return weightedAgeOfRegulations;
    }

    public void setWeightedAgeOfRegulations(long weightedAgeOfRegulations) {
        this.weightedAgeOfRegulations = weightedAgeOfRegulations;
    }

    public TreeMap<Date, String> getVersionHistory() {
        return versionHistory;
    }

    public void setVersionHistory(TreeMap<Date, String> versionHistory) {
        this.versionHistory = versionHistory;
    }

    @Override
    public String toString() {
        StringBuilder cfrReferencesStr = new StringBuilder();
        for (CfrReference cfrReference: cfrReferences) {
            cfrReferencesStr.append(String.format("\t%s", cfrReference.toString()));
        }

        StringBuilder childrenStr = new StringBuilder();
        for (Agency child: children) {
            childrenStr.append(String.format("\t%s", child.toString()));
        }

        return String.format("Name: %s\nSlug: %s\nCFR Reference: %s\nWord Count: %d\nChecksum: %d\nChildren: %s", name, slug, cfrReferencesStr, wordCount, checksum, childrenStr);
    }
}
