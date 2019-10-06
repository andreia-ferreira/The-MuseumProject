package com.penguin.thebooklore.utils.binding;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.penguin.thebooklore.R;

public class ImageViewBinding {

    @BindingAdapter("android:insertFromUrl")
    public static void setImageUrl(ImageView view, String url) {
//        RequestOptions opts = new RequestOptions()
//                .override(500, 500);

        Glide.with(view.getContext())
                .asBitmap()
                .load(url)
                .thumbnail(0.3f)
                .into(view);
    }

}
