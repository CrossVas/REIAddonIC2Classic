package dev.crossvas.ic2rei.displays;

import dev.crossvas.ic2rei.recipes.BaseMachineRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import java.util.Collections;
import java.util.List;

public class BaseMachineDisplay extends BasicDisplay {

    private BaseMachineRecipe RECIPE;

    private CategoryIdentifier<?> ID;

    public BaseMachineDisplay(BaseMachineRecipe recipe, CategoryIdentifier<?> id) {
        this(Collections.singletonList(EntryIngredients.ofIngredient(recipe.getInput())), Collections.singletonList(EntryIngredients.ofItemStacks(recipe.getOutputs())));
        this.RECIPE = recipe;
        this.ID = id;
    }

    public BaseMachineDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return this.ID;
    }

    public BaseMachineRecipe getRecipe() {
        return this.RECIPE;
    }
}
