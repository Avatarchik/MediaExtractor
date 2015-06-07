package com.myriadmobile.mediaextractor.scheme;

import android.net.Uri;
import android.support.annotation.Nullable;

public class UriUtils {

    public static Type getUriType(@Nullable Uri uri) throws UnsupportedSchemeException {
        Type mediaType = Type.valueFromScheme(uri != null ? uri.getScheme() : null);
        if(mediaType != null) {
            return mediaType;
        } else {
            throw new UnsupportedSchemeException(Type.values());
        }
    }

}
