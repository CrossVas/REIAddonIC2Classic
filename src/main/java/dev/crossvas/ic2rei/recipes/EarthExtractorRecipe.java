package dev.crossvas.ic2rei.recipes;

import ic2.api.recipes.registries.IRareEarthRegistry;
import net.minecraft.world.item.ItemStack;

public class EarthExtractorRecipe {

    private final ItemStack INPUT;
    private final ItemStack OUTPUT;
    private final float POINTS;
    private final int TOTAL;

    public EarthExtractorRecipe(IRareEarthRegistry.RareEntry recipeEntry) {
        this.INPUT = recipeEntry.getInput();
        this.OUTPUT = recipeEntry.getOutput();
        this.POINTS = recipeEntry.getValue();
        this.TOTAL = (int) (1000 / recipeEntry.getValue());
    }

    public ItemStack getInput() {
        return this.INPUT;
    }

    public ItemStack getOutput() {
        return this.OUTPUT;
    }

    public float getPoints() {
        return this.POINTS;
    }

    public int getTotal() {
        return this.TOTAL;
    }
}
