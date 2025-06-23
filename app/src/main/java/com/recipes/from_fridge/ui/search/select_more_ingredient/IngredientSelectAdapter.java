package com.recipes.from_fridge.ui.search.select_more_ingredient;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.recipes.from_fridge.databinding.SearchItemMoreIngredientBinding;
import com.recipes.from_fridge.model.Ingredient;
import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for displaying a list of ingredients for selection.
 * Used in SelectIngredientFragment to let users choose ingredients to match recipes.
 */
public class IngredientSelectAdapter extends RecyclerView.Adapter<IngredientSelectAdapter.ViewHolder> {

    public interface OnAddClickListener {
        void onAddClick(Ingredient ingredient);
    }

    private final List<Ingredient> ingredientList = new ArrayList<>();
    private final OnAddClickListener clickListener;

    public IngredientSelectAdapter(OnAddClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setIngredients(List<Ingredient> newList) {
        ingredientList.clear();
        ingredientList.addAll(newList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IngredientSelectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SearchItemMoreIngredientBinding binding = SearchItemMoreIngredientBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientSelectAdapter.ViewHolder holder, int position) {
        Ingredient ingredient = ingredientList.get(position);
        holder.bind(ingredient, clickListener);
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final SearchItemMoreIngredientBinding binding;

        public ViewHolder(SearchItemMoreIngredientBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Ingredient ingredient, OnAddClickListener listener) {
            binding.setIngredient(ingredient);
            binding.setClickListener(listener);
            binding.executePendingBindings();
        }
    }
}
