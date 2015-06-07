package com.myriadmobile.mediaextractor.mime;

import android.content.Context;
import android.net.Uri;

import com.myriadmobile.mediaextractor.scheme.Type;
import com.myriadmobile.mediaextractor.scheme.UnsupportedSchemeException;
import com.myriadmobile.mediaextractor.scheme.UriUtils;

public class ContentMimeTypeResolver implements MimeTypeResolver {

    private Context context;

    public ContentMimeTypeResolver(Context context) {
        this.context = context;
    }

    @Override
    public void checkUri(Uri uri) throws UnsupportedSchemeException {
        if(UriUtils.getUriType(uri) != Type.CONTENT) {
            throw new UnsupportedSchemeException(Type.CONTENT);
        }
    }

    @Override
    public String resolveMimeType(Uri uri) throws UnsupportedSchemeException {
        checkUri(uri);
        return context.getContentResolver().getType(uri);
    }
}
