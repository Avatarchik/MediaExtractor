package com.myriadmobile.mediaextractor.mime;

import android.net.Uri;

import com.myriadmobile.mediaextractor.scheme.UnsupportedSchemeException;

public interface MimeTypeResolver {
    void checkUri(Uri uri) throws UnsupportedSchemeException;
    String resolveMimeType(Uri uri) throws UnsupportedSchemeException;
}
