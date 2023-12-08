package dev.crossvas.ic2rei.recipes;

import ic2.api.recipes.registries.IScrapBoxRegistry;
import ic2.core.platform.registries.IC2Items;
import net.minecraft.world.item.ItemStack;

public class ScrapboxRecipe {

    private final ItemStack INPUT;
    private final ItemStack OUTPUT;
    private final float CHANCE;

    public ScrapboxRecipe(IScrapBoxRegistry.IDrop drop) {
        this.INPUT = new ItemStack(IC2Items.SCRAPBOX);
        this.OUTPUT = drop.getDrop();
        this.CHANCE = drop.getChance();
    }

    public ItemStack getInput() {
        return this.INPUT;
    }

    public ItemStack getOutput() {
        return this.OUTPUT;
    }

    public float getChance() {
        return this.CHANCE;
    }
}
