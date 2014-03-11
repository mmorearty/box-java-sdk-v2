package com.box.boxjavalibv2.requests.requestobjects;

import com.box.boxjavalibv2.jsonparsing.IBoxJSONParser;

public class BoxPagingRequestObject extends BoxDefaultRequestObject {

    public BoxPagingRequestObject(IBoxJSONParser parser) {
        super(parser);
    }

    /**
     * BoxPagingRequestObject for get a paged list.
     * 
     * @param limit
     *            the number of items to return. default is 100, max is 1000.
     * @param offset
     *            the item at which to begin the response, default is 0.
     * @return BoxFolderRequestObject
     */
    public static BoxPagingRequestObject pagingRequestObject(final int limit, final int offset, final IBoxJSONParser parser) {
        return (BoxPagingRequestObject) (new BoxPagingRequestObject(parser)).setPage(limit, offset);
    }
}
