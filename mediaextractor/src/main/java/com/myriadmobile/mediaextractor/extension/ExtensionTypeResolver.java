package com.myriadmobile.mediaextractor.extension;

import android.net.Uri;

import com.myriadmobile.mediaextractor.scheme.UnsupportedSchemeException;

public interface ExtensionTypeResolver {
    void checkUri(Uri uri) throws UnsupportedSchemeException;
    String resolveExtensionType(Uri uri) throws UnsupportedSchemeException;
}
