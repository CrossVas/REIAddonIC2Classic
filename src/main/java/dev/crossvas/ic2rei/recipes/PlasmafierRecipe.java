package dev.crossvas.ic2rei.recipes;

import ic2.core.platform.registries.IC2Items;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PlasmafierRecipe {

    private final ItemStack INPUT;
    private final ItemStack OUTPUT;
    private final int TICKS;
    private final int EU_TICK;

    public PlasmafierRecipe(ItemStack input, ItemStack output, int ticks, int euPerTick) {
        this.INPUT = input;
        this.OUTPUT = output;
        this.TICKS = ticks;
        this.EU_TICK = euPerTick;
    }

    public ItemStack getInput() {
        return this.INPUT;
    }

    public ItemStack getOutput() {
        return this.OUTPUT;
    }

    public int getTicks() {
        return this.TICKS;
    }

    public int getEnergyPerTick() {
        return this.EU_TICK;
    }

    public static List<PlasmafierRecipe> getPlasmafierRecipes() {
        List<PlasmafierRecipe> recipes = new ArrayList<>();
        recipes.add(new PlasmafierRecipe(new ItemStack(IC2Items.UUMATTER, 10), new ItemStack(IC2Items.CELL_PLASMA), 2500, 2048));
        return recipes;
    }
}
