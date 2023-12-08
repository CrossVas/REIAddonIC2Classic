package dev.crossvas.ic2rei.displays;

import dev.crossvas.ic2rei.recipes.WoodGassifierRecipe;
import dev.crossvas.ic2rei.utils.CategoryIDs;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WoodGassifierDisplay extends BasicDisplay {

    private WoodGassifierRecipe RECIPE;

    public WoodGassifierDisplay(WoodGassifierRecipe recipe) {
        this(Collections.singletonList(EntryIngredients.ofItemStacks(recipe.getInputs())),
                Arrays.asList(EntryIngredients.of(recipe.getOutput()),
                        EntryIngredients.of(recipe.getFluidOutput())));
        this.RECIPE = recipe;
    }

    public WoodGassifierDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CategoryIDs.WOOD_GASSIFIER;
    }

    public WoodGassifierRecipe getRecipe() {
        return this.RECIPE;
    }
}
