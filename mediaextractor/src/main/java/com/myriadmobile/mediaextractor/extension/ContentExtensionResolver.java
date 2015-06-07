package com.myriadmobile.mediaextractor.extension;

import android.net.Uri;

import com.myriadmobile.mediaextractor.scheme.UnsupportedSchemeException;

public class ContentExtensionResolver implements ExtensionTypeResolver {
    @Override
    public void checkUri(Uri uri) throws UnsupportedSchemeException {

    }

    @Override
    public String resolveExtensionType(Uri uri) throws UnsupportedSchemeException {
        return null;
    }
}
