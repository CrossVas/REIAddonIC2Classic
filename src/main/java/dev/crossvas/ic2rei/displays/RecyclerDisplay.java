package dev.crossvas.ic2rei.displays;

import dev.crossvas.ic2rei.recipes.RecyclerRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RecyclerDisplay extends BasicDisplay {

    private RecyclerRecipe RECIPE;

    private CategoryIdentifier<?> ID;
    private boolean IS_SCRAP;

    public RecyclerDisplay(RecyclerRecipe recipe, CategoryIdentifier<?> id) {
        this(Collections.singletonList(EntryIngredients.ofIngredient(recipe.getInput())), Collections.singletonList(EntryIngredients.ofItemStacks(recipe.getOutputs())));
        this.RECIPE = recipe;
        this.ID = id;
    }

    public RecyclerDisplay(RecyclerRecipe recipe, CategoryIdentifier<?> id, boolean scrap) {
        this(Collections.singletonList(EntryIngredients.ofItemStacks(recipe.getScrapInputs())), Collections.singletonList(EntryIngredients.of(recipe.getScrapOutput())));
        this.ID = id;
        this.IS_SCRAP = scrap;
        this.RECIPE = recipe;
    }

    public RecyclerDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return this.ID;
    }

    public RecyclerRecipe getRecipe() {
        return this.RECIPE;
    }

    public boolean isScrapRecipe() {
        return this.IS_SCRAP;
    }
}
