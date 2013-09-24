package com.box.boxjavalibv2.requests.requestobjects;

import java.util.Date;

import com.box.boxjavalibv2.dao.BoxFile;
import com.box.boxjavalibv2.dao.BoxFolder;
import com.box.boxjavalibv2.dao.BoxItem;
import com.box.boxjavalibv2.jsonentities.MapJSONStringEntity;

import org.apache.commons.lang.time.DateFormatUtils;

public class BoxItemRequestObject extends BoxDefaultRequestObject {

    protected BoxItemRequestObject() {
    }

    public static BoxItemRequestObject deleteSharedLinkRequestObject() {
        return (new BoxItemRequestObject()).setSharedLink(null);
    }

    public static BoxItemRequestObject createSharedLinkRequestObject(BoxSharedLinkRequestObject sharedLink) {
        return (new BoxItemRequestObject()).setSharedLink(sharedLink);
    }

    /**
     * Set shared link. You can set this to null in a update file/folder info request in order to delete shared link in the file object.
     * 
     * @param sharedLink
     * @return
     */
    public BoxItemRequestObject setSharedLink(BoxSharedLinkRequestObject sharedLink) {
        put(BoxFile.FIELD_SHARED_LINK, sharedLink != null ? sharedLink.getJSONEntity() : null);
        return this;
    }

    /**
     * Set parent folder of the file.
     * 
     * @param parentId
     *            id of parent
     * @return
     */
    public BoxItemRequestObject setParent(String parentId) {
        MapJSONStringEntity entity = new MapJSONStringEntity();
        entity.put(BoxFolder.FIELD_ID, parentId);
        put(BoxItem.FIELD_PARENT, entity);
        return this;
    }

    /**
     * Set etag. This can be set when making a delete file/folder request. If this is set and if-match fails, the deletion api call will fail.
     * 
     * @param etag
     *            etag
     * @return BoxFileRequestObject
     */
    public BoxItemRequestObject setIfMatch(String etag) {
        addHeader("If-Match", etag);
        return this;
    }

    /**
     * Set name of the file.
     * 
     * @param name
     *            name
     * @return
     */
    public BoxItemRequestObject setName(String name) {
        put(BoxFile.FIELD_NAME, name);
        return this;
    }

    /**
     * Set description of the file
     * 
     * @param description
     *            description
     * @return
     */
    public BoxItemRequestObject setDescription(String description) {
        put(BoxFile.FIELD_DESCRIPTION, description);
        return this;
    }

    /**
     * Set the time the item was last modified
     * 
     * @param date
     *            date
     * @return
     */
    public BoxItemRequestObject setContentModifiedAt(String date) {
        put(BoxItem.FIELD_CONTENT_MODIFIED_AT, date);
        return this;
    }

    /**
     * Set the time the item was last modified
     * 
     * @param date
     *            date
     * @return
     */
    public BoxItemRequestObject setContentModifiedAt(Date date) {
        String isoDate = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(date);
        put(BoxItem.FIELD_CONTENT_MODIFIED_AT, isoDate);
        return this;
    }

    /**
     * Set the time the item was created
     * 
     * @param date
     *            date
     * @return
     */
    public BoxItemRequestObject setContentCreatedAt(String date) {
        put(BoxItem.FIELD_CONTENT_CREATED_AT, date);
        return this;
    }

    /**
     * Set the time the item was created
     * 
     * @param date
     *            date
     * @return
     */
    public BoxItemRequestObject setContentCreatedAt(Date date) {
        String isoDate = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(date);
        put(BoxItem.FIELD_CONTENT_CREATED_AT, isoDate);
        return this;
    }
}
