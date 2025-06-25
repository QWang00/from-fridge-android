package com.recipes.from_fridge.ui.search.recipe_detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.recipes.from_fridge.R;
import java.util.ArrayList;
import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.RecipeDetailViewHolder> {

    private final List<String> steps = new ArrayList<>();

    public void setItems(List<String> newSteps) {
        steps.clear();
        steps.addAll(newSteps);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipeDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_item_recipe_step, parent, false);
        return new RecipeDetailViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecipeDetailViewHolder holder, int position) {
        String stepText = steps.get(position);
        holder.textView.setText("Step " + (position + 1) + ": " + stepText);
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    static class RecipeDetailViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public RecipeDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textStep);
        }
    }
}
