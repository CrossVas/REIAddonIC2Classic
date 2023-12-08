package dev.crossvas.ic2rei.recipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public class IC2SpecialRecipe {

    private final List<Ingredient> INGREDIENTS;
    private final ItemStack OUTPUT;
    private final boolean HIDDEN;

    public IC2SpecialRecipe(List<Ingredient> ingredients, ItemStack output, boolean hidden) {
        this.INGREDIENTS = ingredients;
        this.OUTPUT = output;
        this.HIDDEN = hidden;
    }

    public List<Ingredient> getInputs() {
        return this.INGREDIENTS;
    }

    public ItemStack getOutput() {
        return this.OUTPUT;
    }

    public boolean isHidden() {
        return this.HIDDEN;
    }
}
