package com.myriadmobile.mediaextractor.scheme;

import java.util.Arrays;

/**
 * Exception thrown when an attempt is made to use a Uri object
 * that contains either no scheme, or a scheme outside of the
 * supported {@link Type}
 */
public class UnsupportedSchemeException extends Exception {
    public UnsupportedSchemeException(Type... types) {
        super(String.format("Supported Uri schemes: %s", Arrays.toString(types)));
    }
}
