package com.myriadmobile.mediaextractor.scheme;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class UriUtils {

    @NonNull
    public static Type getUriType(@Nullable Uri uri) throws UnsupportedSchemeException {
        Type mediaType = Type.valueFromScheme(uri != null ? uri.getScheme() : null);
        if(mediaType != null) {
            return mediaType;
        } else {
            throw new UnsupportedSchemeException(Type.values());
        }
    }

    @Nullable
    public static Type getUriTypeChecked(@Nullable Uri uri) {
        Type uriType = null;
        try {
            uriType = getUriType(uri);
        } catch (UnsupportedSchemeException e) {
            e.printStackTrace();
        }
        return uriType;
    }
}
