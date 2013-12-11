package com.box.boxjavalibv2.requests;

import org.apache.http.HttpStatus;

import com.box.boxjavalibv2.interfaces.IBoxJSONParser;
import com.box.boxjavalibv2.requests.requestobjects.BoxFileUploadRequestObject;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.requests.DefaultBoxRequest;

/**
 * Request to upload files.
 */
public class UploadFileRequest extends DefaultBoxRequest {

    private static final String URI = "/files/content";

    /**
     * Constructor.
     * 
     * @param config
     *            config
     * @param parser
     *            json parser
     * @param parentId
     *            id of the parent folder
     * @param files
     *            a LinkedHashMap, each entry is for one file, key of the entry is the name of the file after uploaded to box, value of the entry is the local
     *            file to be uploaded
     * @param listener
     *            listener to monitor file upload progress
     * @throws BoxRestException
     *             exception
     */
    public UploadFileRequest(final IBoxConfig config, final IBoxJSONParser parser, final BoxFileUploadRequestObject requestObject) throws BoxRestException {
        super(config, parser, getUri(), RestMethod.POST, requestObject);
        this.setExpectedResponseCode(HttpStatus.SC_CREATED);
    }

    /**
     * Get uri.
     * 
     * @return uri.
     */
    public static String getUri() {
        return URI;
    }

    @Override
    public String getScheme() {
        return getConfig().getUploadUrlScheme();
    }

    @Override
    public String getAuthority() {
        return getConfig().getUploadUrlAuthority();
    }

    @Override
    public String getApiUrlPath() {
        return getConfig().getUploadUrlPath();
    }

    @Override
    public boolean isRepeatable() {
        // Some upload requests are not repeatable, because they read
        // the file to be uploaded from an InputStream.
        return false;
    }
}
