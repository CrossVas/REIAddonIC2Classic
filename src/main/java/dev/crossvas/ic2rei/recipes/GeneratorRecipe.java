package dev.crossvas.ic2rei.recipes;

import ic2.core.IC2;
import ic2.core.item.misc.FuelCanItem;
import ic2.core.platform.registries.IC2Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GeneratorRecipe {

    private final ItemStack INPUT;
    private final int ENERGY;

    public GeneratorRecipe(ItemStack input) {
        this.INPUT = input;
        if (input.getItem() instanceof FuelCanItem) {
            this.ENERGY = ((input.getOrCreateTag().getInt("max_fuel") / 500) * ForgeHooks.getBurnTime(input, RecipeType.SMELTING)) / 4;
        } else {
            this.ENERGY = ForgeHooks.getBurnTime(input, RecipeType.SMELTING) / 4;
        }

    }

    public ItemStack getInput() {
        return this.INPUT;
    }

    public int getEnergy() {
        return this.ENERGY;
    }

    public static List<ItemStack> getGeneratorFuel() {
        List<ItemStack> burnable = new ArrayList<>();
        ForgeRegistries.ITEMS.forEach(item -> {
            if (item != Items.AIR) {
                ItemStack stack = new ItemStack(item);
                if (IC2.RECIPES.get(false).getFuel(stack, false) > 0) {
                    burnable.add(stack);
                }
            }
        });
        burnable.add(getFull(IC2Items.FUEL_CAN.getDefaultInstance(), 10000));
        burnable.add(getFull(IC2Items.FUEL_CAN.getDefaultInstance(), 1000000));
        return burnable;
    }

    public static List<GeneratorRecipe> getGeneratorRecipes() {
        List<GeneratorRecipe> sorted = new ArrayList<>();
        getGeneratorFuel().forEach(fuel -> sorted.add(new GeneratorRecipe(fuel)));
        return sorted.stream().sorted(Comparator.comparing(GeneratorRecipe::getEnergy).reversed()).collect(Collectors.toList());
    }

    public static ItemStack getFull(ItemStack stack, int amount) {
        ItemStack newStack = stack.copy();
        CompoundTag nbt = newStack.getOrCreateTag();
        amount += Math.max(0, nbt.getInt("fuel"));
        nbt.putInt("fuel", amount);
        nbt.putInt("max_fuel", amount);
        return newStack;
    }
}
