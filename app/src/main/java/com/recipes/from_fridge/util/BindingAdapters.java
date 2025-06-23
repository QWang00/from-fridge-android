package com.recipes.from_fridge.util;

import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.recipes.from_fridge.R;

public class BindingAdapters {

    @BindingAdapter("adapter")
    public static void setRecyclerViewAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        if (recyclerView.getAdapter() != adapter) {
            recyclerView.setAdapter(adapter);
        }
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_placeholder_ingredient)
                .error(R.drawable.ic_placeholder_ingredient)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view);
    }
}