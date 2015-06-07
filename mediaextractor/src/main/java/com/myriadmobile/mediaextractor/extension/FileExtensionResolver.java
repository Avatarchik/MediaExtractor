package com.myriadmobile.mediaextractor.extension;

import android.net.Uri;
import android.webkit.MimeTypeMap;

import com.myriadmobile.mediaextractor.scheme.Type;
import com.myriadmobile.mediaextractor.scheme.UnsupportedSchemeException;
import com.myriadmobile.mediaextractor.scheme.UriUtils;

public class FileExtensionResolver implements ExtensionTypeResolver {

    @Override
    public void checkUri(Uri uri) throws UnsupportedSchemeException {
        if(UriUtils.getUriType(uri) != Type.FILE) {
            throw new UnsupportedSchemeException(Type.FILE);
        }
    }

    @Override
    public String resolveExtensionType(Uri uri) throws UnsupportedSchemeException {
        checkUri(uri);
        return MimeTypeMap.getFileExtensionFromUrl(uri.toString());
    }
}
