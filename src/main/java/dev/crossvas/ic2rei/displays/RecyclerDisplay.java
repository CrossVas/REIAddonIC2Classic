package dev.crossvas.ic2rei.displays;

import dev.crossvas.ic2rei.recipes.RecyclerRecipe;
import dev.crossvas.ic2rei.utils.CategoryIDs;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import java.util.Collections;
import java.util.List;

public class RecyclerDisplay extends BasicDisplay {

    private RecyclerRecipe RECIPE;

    public RecyclerDisplay(RecyclerRecipe recipe) {
        this(Collections.singletonList(EntryIngredients.ofIngredient(recipe.getInput())), Collections.singletonList(EntryIngredients.ofItemStacks(recipe.getOutputs())));
        this.RECIPE = recipe;
    }

    public RecyclerDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CategoryIDs.RECYCLER;
    }

    public RecyclerRecipe getRecipe() {
        return this.RECIPE;
    }
}
