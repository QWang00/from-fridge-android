package com.recipes.from_fridge.ui.fridge;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.recipes.from_fridge.databinding.ItemFridgeIngredientBinding;
import com.recipes.from_fridge.model.FridgeIngredient;
import com.recipes.from_fridge.model.Ingredient;
import java.util.ArrayList;
import java.util.List;

public class FridgeAdapter extends RecyclerView.Adapter<FridgeAdapter.FridgeViewHolder> {

    public enum ActionMode {
        ADD,
        REMOVE
    }

    public interface OnActionClickListener {
        void onActionClick(FridgeIngredient ingredient);
    }

    private List<FridgeIngredient> fridgeIngredients = new ArrayList<>();
    private final OnActionClickListener listener;
    private final ActionMode mode;

    public FridgeAdapter(OnActionClickListener listener, ActionMode mode) {
        this.listener = listener;
        this.mode = mode;
    }

    public void setFridgeIngredients(List<FridgeIngredient> newIngredients) {
        this.fridgeIngredients = newIngredients;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FridgeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemFridgeIngredientBinding binding = ItemFridgeIngredientBinding.inflate(inflater, parent, false);
        return new FridgeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FridgeViewHolder holder, int position) {
        FridgeIngredient fridgeIngredient = fridgeIngredients.get(position);
        holder.bind(fridgeIngredient, listener, mode);
    }

    @Override
    public int getItemCount() {
        return fridgeIngredients != null ? fridgeIngredients.size() : 0;
    }

    static class FridgeViewHolder extends RecyclerView.ViewHolder {

        private final ItemFridgeIngredientBinding binding;

        public FridgeViewHolder(@NonNull ItemFridgeIngredientBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(FridgeIngredient fridgeIngredient, OnActionClickListener listener, ActionMode mode) {
            binding.setIngredient(fridgeIngredient);
            binding.setClickListener(listener);
            binding.setMode(mode);
            binding.executePendingBindings();
        }
    }
}
