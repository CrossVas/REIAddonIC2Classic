package dev.crossvas.ic2rei.recipes;

import ic2.core.IC2;
import ic2.core.platform.registries.IC2Items;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MassFabricatorRecipe {

    private final ItemStack INPUT;
    private final int AMPLIFIER;

    public MassFabricatorRecipe(ItemStack stack, int amp) {
        this.INPUT = stack;
        this.AMPLIFIER = amp;
    }

    public ItemStack getInput() {
        return this.INPUT;
    }

    public ItemStack getOutput() {
        return new ItemStack(IC2Items.UUMATTER);
    }

    public int getAmplifier() {
        return this.AMPLIFIER;
    }

    public static List<MassFabricatorRecipe> getMassFabRecipes() {
        List<MassFabricatorRecipe> recipes = new ArrayList<>();
        recipes.add(new MassFabricatorRecipe(ItemStack.EMPTY, 0));
        IC2.RECIPES.get(false).massFabricator.getAllEntries().forEach(entry -> recipes.add(
                new MassFabricatorRecipe(entry.getInputs()[0].getComponents().get(0), entry.getOutput().getMetadata().getInt("Amplifier"))));
        return recipes;
    }
}
