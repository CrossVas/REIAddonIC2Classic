package dev.crossvas.ic2rei.displays;

import dev.crossvas.ic2rei.recipes.DoubleInputMachineRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DoubleInputMachineDisplay extends BasicDisplay {

    private DoubleInputMachineRecipe RECIPE;

    private CategoryIdentifier<?> ID;

    public DoubleInputMachineDisplay(DoubleInputMachineRecipe recipe, CategoryIdentifier<?> id) {
        this(Arrays.asList(
                        EntryIngredients.ofIngredient(recipe.getInput()),
                        EntryIngredients.ofIngredient(recipe.getSecondInput())),
                        Collections.singletonList(EntryIngredients.ofItemStacks(recipe.getOutputs()))
        );
        this.RECIPE = recipe;
        this.ID = id;
    }

    public DoubleInputMachineDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return this.ID;
    }

    public DoubleInputMachineRecipe getRecipe() {
        return this.RECIPE;
    }
}
