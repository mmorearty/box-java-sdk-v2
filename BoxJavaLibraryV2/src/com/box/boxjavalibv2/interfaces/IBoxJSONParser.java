package com.box.boxjavalibv2.interfaces;

import java.io.InputStream;

import com.box.boxjavalibv2.exceptions.BoxJSONException;

public interface IBoxJSONParser {

    /**
     * Convert the object into String.
     * 
     * @param object
     * @return
     * @throws BoxJSONException
     */
    String convertToString(final Object object) throws BoxJSONException;

    /**
     * Convert InputStream to object.
     * 
     * @param inputStream
     * @param theClass
     * @return
     */
    <T> T parseIntoObject(final InputStream inputStream, final Class<T> theClass);

    /**
     * Convert the json string into object.s
     * 
     * @param jsonString
     * @param theClass
     * @return
     */
    <T> T parseIntoObject(final String jsonString, final Class<T> theClass);
}
