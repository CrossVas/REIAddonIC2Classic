package dev.crossvas.ic2rei.displays;

import dev.crossvas.ic2rei.recipes.EarthExtractorRecipe;
import dev.crossvas.ic2rei.utils.CategoryIDs;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import java.util.Collections;
import java.util.List;

public class EarthExtractorDisplay extends BasicDisplay {

    private EarthExtractorRecipe RECIPE;

    public EarthExtractorDisplay(EarthExtractorRecipe recipe) {
        this(Collections.singletonList(EntryIngredients.of(recipe.getInput())),
                Collections.singletonList(EntryIngredients.of(recipe.getOutput())));
        this.RECIPE = recipe;
    }

    public EarthExtractorDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CategoryIDs.EARTH_EXTRACTOR;
    }

    public EarthExtractorRecipe getRecipe() {
        return this.RECIPE;
    }
}
