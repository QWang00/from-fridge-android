package com.recipes.from_fridge.ui.search.search_result;


import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.recipes.from_fridge.databinding.SearchItemRecipeBinding;
import com.recipes.from_fridge.model.RecipePreview;
import java.util.ArrayList;
import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {

    private final List<RecipePreview> recipeList = new ArrayList<>();
    private final OnRecipeClickListener clickListener;

    public SearchResultAdapter(List<RecipePreview> recipeList, OnRecipeClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setItems(List<RecipePreview> newList) {
        recipeList.clear();
        recipeList.addAll(newList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        SearchItemRecipeBinding binding = SearchItemRecipeBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecipePreview recipe = recipeList.get(position);
        holder.bind(recipe, clickListener);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final SearchItemRecipeBinding binding;

        public ViewHolder(SearchItemRecipeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(RecipePreview recipe, OnRecipeClickListener clickListener) {
            binding.setRecipe(recipe);
            binding.setClickListener(clickListener);
            binding.executePendingBindings();
        }
    }

    public interface OnRecipeClickListener {
        void onRecipeClick(RecipePreview recipe);
    }
}
