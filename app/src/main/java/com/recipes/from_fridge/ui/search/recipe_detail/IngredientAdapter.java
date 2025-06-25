package com.recipes.from_fridge.ui.search.recipe_detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.recipes.from_fridge.R;
import com.recipes.from_fridge.model.IngredientDetail;
import java.util.ArrayList;
import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {

    private final List<IngredientDetail> ingredientList = new ArrayList<>();

    public void setItems(List<IngredientDetail> newList) {
        ingredientList.clear();
        ingredientList.addAll(newList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_item_ingredient_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IngredientDetail ingredient = ingredientList.get(position);

        holder.name.setText(ingredient.getName());
        holder.quantity.setText("Quantity: " + ingredient.getQuantity());
        holder.preparation.setText("Prep: " + ingredient.getPreparation());
        holder.checkMark.setVisibility(ingredient.isOwned() ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView checkMark, name, quantity, preparation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkMark = itemView.findViewById(R.id.checkMark);
            name = itemView.findViewById(R.id.textName);
            quantity = itemView.findViewById(R.id.textQuantity);
            preparation = itemView.findViewById(R.id.textPreparation);
        }
    }
}

