package com.myriadmobile.mediaextractor.mime;

import android.net.Uri;
import android.webkit.MimeTypeMap;

import com.myriadmobile.mediaextractor.scheme.Type;
import com.myriadmobile.mediaextractor.scheme.UnsupportedSchemeException;
import com.myriadmobile.mediaextractor.scheme.UriUtils;

public class FileMimeTypeResolver implements MimeTypeResolver {

    @Override
    public void checkUri(Uri uri) throws UnsupportedSchemeException {
        if(UriUtils.getUriType(uri) != Type.FILE) {
            throw new UnsupportedSchemeException(Type.FILE);
        }
    }

    @Override
    public String resolveMimeType(Uri uri) throws UnsupportedSchemeException {
        return MimeTypeMap.getFileExtensionFromUrl(uri.toString());
    }
}
