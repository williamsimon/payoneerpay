package com.williamsimon.android.payoneerpay.utils;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.williamsimon.android.payoneerpay.R;
import com.williamsimon.android.payoneerpay.domain.ApplicableNetwork;
import java.net.URL;

import androidx.databinding.BindingAdapter;

public class BindingUtils {

    @BindingAdapter("imageApplicableNetwork")
    public static void setNetworkImage(ImageView imageView, ApplicableNetwork item) {
        if(item != null){
            URL imgUrl = item.getLinks().get("logo");
            String imgUrlStr = imgUrl.toString();
            Glide.with(imageView.getContext())
                    .load(imgUrlStr)
                    .apply(
                            new RequestOptions()
                                    .placeholder(R.drawable.loading_animation)
                                    .error(R.drawable.ic_broken_image))
                    .into(imageView);
        }
    }

    @BindingAdapter("apiStatus")
    public static void bindStatus(ImageView statusImageView, ApiStatus status) {
        if(status != null){
            switch (status){
                case LOADING:
                    statusImageView.setVisibility(View.VISIBLE);
                    statusImageView.setImageResource(R.drawable.loading_animation);
                    break;
                case ERROR:
                    statusImageView.setVisibility(View.VISIBLE);
                    statusImageView.setImageResource(R.drawable.ic_connection_error);
                    break;
                case DONE:
                    statusImageView.setVisibility(View.GONE);
                    break;
            }
        }
    }

}
