package com.myriadmobile.mediaextractor.mime;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.myriadmobile.mediaextractor.scheme.Type;
import com.myriadmobile.mediaextractor.scheme.UnsupportedSchemeException;
import com.myriadmobile.mediaextractor.scheme.UriUtils;

import java.util.EnumMap;

public class FusedMimeResolver {

    private final EnumMap<Type, MimeTypeResolver> mimeTypeResolvers;

    public FusedMimeResolver(Context context) {
        mimeTypeResolvers = new EnumMap<>(Type.class);
        mimeTypeResolvers.put(Type.CONTENT, new ContentMimeTypeResolver(context));
        mimeTypeResolvers.put(Type.FILE, new FileMimeTypeResolver());
    }

    @Nullable
    protected Type resolveType(Uri uri) {
        return UriUtils.getUriTypeChecked(uri);
    }

    @Nullable
    public String resolveMimeType(Uri uri) throws UnsupportedSchemeException {
        return mimeTypeResolvers.get(resolveType(uri)).resolveMimeType(uri);
    }
}
