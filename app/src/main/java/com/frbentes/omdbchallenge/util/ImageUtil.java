package com.frbentes.omdbchallenge.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.ByteArrayOutputStream;

/**
 * Created by frbentes on 15/01/17.
 */
public class ImageUtil {

    public static void loadImage(Context context, final String imagePath, final int placeholder,
                          final ImageView imageView, final OnLoadImageListener listener) {
        Glide.with(context)
                .load(imagePath)
                .asBitmap()
                .placeholder(placeholder)
                .error(placeholder)
                .listener(new RequestListener<String, Bitmap>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<Bitmap> target,
                                               boolean isFirstResource) {
                        if (listener != null) {
                            listener.onImageLoadFail();
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, String model,
                                                   Target<Bitmap> target, boolean isFromMemoryCache,
                                                   boolean isFirstResource) {
                        if (listener != null) {
                            listener.onImageLoadSuccess(resource);
                        }
                        return false;
                    }
                })
                .into(imageView);
    }

    public static byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    public static Bitmap convertBytesToBitmap(byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public interface OnLoadImageListener {
        void onImageLoadSuccess(Bitmap resource);
        void onImageLoadFail();
    }

}
