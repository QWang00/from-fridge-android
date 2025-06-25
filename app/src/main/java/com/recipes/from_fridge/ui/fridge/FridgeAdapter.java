package com.recipes.from_fridge.ui.fridge;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.recipes.from_fridge.databinding.FridgeItemFridgeIngredientBinding;
import com.recipes.from_fridge.model.FridgeIngredient;
import java.util.ArrayList;
import java.util.List;

public class FridgeAdapter extends RecyclerView.Adapter<FridgeAdapter.FridgeViewHolder> {

    public interface OnDeleteClickListener {
        void onDeleteClick(FridgeIngredient ingredient);
    }

    private List<FridgeIngredient> fridgeIngredients = new ArrayList<>();
    private final OnDeleteClickListener listener;


    public FridgeAdapter(OnDeleteClickListener listener) {
        this.listener = listener;
    }

    public void setFridgeIngredients(List<FridgeIngredient> newIngredients) {
        this.fridgeIngredients = newIngredients;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FridgeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FridgeItemFridgeIngredientBinding binding = FridgeItemFridgeIngredientBinding.inflate(inflater, parent, false);
        return new FridgeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FridgeViewHolder holder, int position) {
        FridgeIngredient fridgeIngredient = fridgeIngredients.get(position);
        holder.bind(fridgeIngredient, listener);
    }

    @Override
    public int getItemCount() {
        return fridgeIngredients != null ? fridgeIngredients.size() : 0;
    }

    static class FridgeViewHolder extends RecyclerView.ViewHolder {

        private final FridgeItemFridgeIngredientBinding binding;

        public FridgeViewHolder(@NonNull FridgeItemFridgeIngredientBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(FridgeIngredient fridgeIngredient, OnDeleteClickListener  listener) {
            binding.setIngredient(fridgeIngredient);
            binding.setClickListener(listener);
            binding.executePendingBindings();
        }
    }

    public void removeItem(FridgeIngredient ingredient) {
        int index = fridgeIngredients.indexOf(ingredient);
        if (index >= 0) {
            fridgeIngredients.remove(index);
            notifyItemRemoved(index);
        }
    }

}
