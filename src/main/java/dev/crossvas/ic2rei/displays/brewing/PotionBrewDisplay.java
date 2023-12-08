package dev.crossvas.ic2rei.displays.brewing;

import dev.crossvas.ic2rei.recipes.brewing.PotionBrewRecipe;
import dev.crossvas.ic2rei.utils.CategoryIDs;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PotionBrewDisplay extends BasicDisplay {

    private PotionBrewRecipe RECIPE;

    public PotionBrewDisplay(PotionBrewRecipe recipe) {
        this(Arrays.asList(
                        EntryIngredients.of(recipe.getBaseInput()),
                        EntryIngredients.of(recipe.getIngredient()),
                        EntryIngredients.of(recipe.getRedstoneStack()),
                        EntryIngredients.of(recipe.getGlowstoneStack())),
                Collections.singletonList(EntryIngredients.of(recipe.getOutput())));
        this.RECIPE = recipe;
    }

    public PotionBrewDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CategoryIDs.POTION_BREWING;
    }

    public PotionBrewRecipe getRecipe() {
        return this.RECIPE;
    }
}
