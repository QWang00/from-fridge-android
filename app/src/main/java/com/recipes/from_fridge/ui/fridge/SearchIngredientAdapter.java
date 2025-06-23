package com.recipes.from_fridge.ui.fridge;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.recipes.from_fridge.databinding.FridgeItemAddIngredientBinding;
import com.recipes.from_fridge.model.Ingredient;
import java.util.ArrayList;
import java.util.List;

public class SearchIngredientAdapter extends RecyclerView.Adapter<SearchIngredientAdapter.ViewHolder> {

    public interface OnAddClickListener {
        void onAddClick(Ingredient ingredient);
    }

    private List<Ingredient> ingredientList = new ArrayList<>();
    private final OnAddClickListener listener;

    public SearchIngredientAdapter(OnAddClickListener listener) {
        this.listener = listener;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredientList = ingredients;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FridgeItemAddIngredientBinding binding = FridgeItemAddIngredientBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ingredient ingredient = ingredientList.get(position);
        holder.bind(ingredient, listener);
    }

    @Override
    public int getItemCount() {
        return ingredientList != null ? ingredientList.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final FridgeItemAddIngredientBinding binding;

        public ViewHolder(FridgeItemAddIngredientBinding binding) {
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
