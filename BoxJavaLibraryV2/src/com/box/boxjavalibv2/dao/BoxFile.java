package com.box.boxjavalibv2.dao;

import java.util.Date;
import java.util.Map;

import com.box.boxjavalibv2.interfaces.IBoxParcelWrapper;
import com.box.boxjavalibv2.utils.ISO8601DateParser;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Box File object
 */
public class BoxFile extends BoxItem {

    public final static String FIELD_SHA1 = "sha1";
    public final static String FIELD_VERSION_NUMBER = "version_number";
    public final static String FIELD_COMMENT_COUNT = "comment_count";

    /**
     * Constructor.
     */
    public BoxFile() {
        setType(BoxResourceType.FILE.toString());
    }

    /**
     * Copy constructor, this does deep copy for all the fields.
     * 
     * @param obj
     */
    public BoxFile(BoxFile obj) {
        super(obj);
    }

    /**
     * Instantiate the object from a map. Each entry in the map reflects to a field.
     * 
     * @param map
     */
    public BoxFile(Map<String, Object> map) {
        super(map);
    }

    /**
     * Get sha1 of the file.
     * 
     * @return sha1 of the file.
     */
    @JsonProperty(FIELD_SHA1)
    public String getSha1() {
        return (String) getValue(FIELD_SHA1);
    }

    /**
     * Setter. This is only used by {@see <a href="http://jackson.codehaus.org">Jackson JSON processer</a>}
     * 
     * @param sha1
     *            sha1
     */
    @JsonProperty(FIELD_SHA1)
    private void setSha1(String sha1) {
        put(FIELD_SHA1, sha1);
    }

    /**
     * Get version number of the file.
     * 
     * @return version number of the file.
     */
    @JsonProperty(FIELD_VERSION_NUMBER)
    public String getVersionNumber() {
        return (String) getValue(FIELD_VERSION_NUMBER);
    }

    /**
     * Setter. This is only used by {@see <a href="http://jackson.codehaus.org">Jackson JSON processer</a>}
     * 
     * @param versionNumber
     *            version number
     */
    @JsonProperty(FIELD_VERSION_NUMBER)
    private void setVersionNumber(String versionNumber) {
        put(FIELD_VERSION_NUMBER, versionNumber);
    }

    /**
     * Get comment count of the file.
     * 
     * @return comment count of the file.
     */
    @JsonProperty(FIELD_COMMENT_COUNT)
    public Integer getCommentCount() {
        return (Integer) getValue(FIELD_COMMENT_COUNT);
    }

    /**
     * Setter. This is only used by {@see <a href="http://jackson.codehaus.org">Jackson JSON processer</a>}
     * 
     * @param commentCount
     *            comment count
     */
    @JsonProperty(FIELD_COMMENT_COUNT)
    private void setCommentCount(Integer commentCount) {
        put(FIELD_COMMENT_COUNT, commentCount);
    }

    public BoxFile(IBoxParcelWrapper in) {
        super(in);
    }
}
