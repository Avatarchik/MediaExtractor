package com.myriadmobile.mediaextractor.extension;

import android.content.Context;
import android.net.Uri;
import android.webkit.MimeTypeMap;

import com.myriadmobile.mediaextractor.mime.ContentMimeTypeResolver;
import com.myriadmobile.mediaextractor.scheme.Type;
import com.myriadmobile.mediaextractor.scheme.UnsupportedSchemeException;
import com.myriadmobile.mediaextractor.scheme.UriUtils;

public class ContentExtensionResolver implements ExtensionTypeResolver {

    private final Context context;
    private final ContentMimeTypeResolver mimeTypeResolver;

    public ContentExtensionResolver(Context context) {
        this.context = context;
        this.mimeTypeResolver = new ContentMimeTypeResolver(context);
    }

    @Override
    public void checkUri(Uri uri) throws UnsupportedSchemeException {
        if(UriUtils.getUriType(uri) != Type.CONTENT) {
            throw new UnsupportedSchemeException(Type.CONTENT);
        }
    }

    @Override
    public String resolveExtensionType(Uri uri) throws UnsupportedSchemeException {
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeTypeResolver.resolveMimeType(uri));
    }
}
