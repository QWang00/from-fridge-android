package com.recipes.from_fridge.ui.search.select_fridge_ingredient;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.recipes.from_fridge.databinding.SearchItemFridgeCheckboxBinding;
import com.recipes.from_fridge.model.SelectableFridgeIngredient;
import java.util.ArrayList;
import java.util.List;

public class FridgeIngredientCheckboxAdapter extends RecyclerView.Adapter<FridgeIngredientCheckboxAdapter.ViewHolder> {

    private final List<SelectableFridgeIngredient> items = new ArrayList<>();

    public FridgeIngredientCheckboxAdapter() {
    }

    public void setItems(List<SelectableFridgeIngredient> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        SearchItemFridgeCheckboxBinding binding = SearchItemFridgeCheckboxBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SelectableFridgeIngredient item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<SelectableFridgeIngredient> getItems() {
        return items;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final SearchItemFridgeCheckboxBinding binding;

        public ViewHolder(SearchItemFridgeCheckboxBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(SelectableFridgeIngredient item) {
            binding.setItem(item);
            binding.executePendingBindings();
            binding.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                item.setSelected(isChecked);
            });
        }
    }
}
