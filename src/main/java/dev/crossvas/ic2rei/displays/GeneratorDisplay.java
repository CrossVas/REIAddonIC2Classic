package dev.crossvas.ic2rei.displays;

import dev.crossvas.ic2rei.recipes.GeneratorRecipe;
import dev.crossvas.ic2rei.utils.CategoryIDs;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import java.util.Collections;
import java.util.List;

public class GeneratorDisplay extends BasicDisplay {

    private GeneratorRecipe RECIPE;

    public GeneratorDisplay(GeneratorRecipe recipe) {
        this(Collections.singletonList(EntryIngredients.of(recipe.getInput())), List.of());
        this.RECIPE = recipe;
    }

    public GeneratorDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CategoryIDs.GENERATOR;
    }

    public GeneratorRecipe getRecipe() {
        return this.RECIPE;
    }
}
