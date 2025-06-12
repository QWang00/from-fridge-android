package com.recipes.from_fridge.util;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class BindingAdapters {

    @BindingAdapter("adapter")
    public static void setRecyclerViewAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        if (recyclerView.getAdapter() != adapter) {
            recyclerView.setAdapter(adapter);
        }
    }
}