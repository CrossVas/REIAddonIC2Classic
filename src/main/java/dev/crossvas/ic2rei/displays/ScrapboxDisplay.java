package dev.crossvas.ic2rei.displays;

import dev.crossvas.ic2rei.recipes.ScrapboxRecipe;
import dev.crossvas.ic2rei.utils.CategoryIDs;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import java.util.Collections;
import java.util.List;

public class ScrapboxDisplay extends BasicDisplay {

    private ScrapboxRecipe RECIPE;

    public ScrapboxDisplay(ScrapboxRecipe recipe) {
        this(Collections.singletonList(EntryIngredients.of(recipe.getInput())), Collections.singletonList(EntryIngredients.of(recipe.getOutput())));
        this.RECIPE = recipe;
    }

    public ScrapboxDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CategoryIDs.SCRAPBOX;
    }

    public ScrapboxRecipe getRecipe() {
        return this.RECIPE;
    }
}
