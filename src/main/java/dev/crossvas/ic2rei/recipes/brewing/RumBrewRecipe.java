package dev.crossvas.ic2rei.recipes.brewing;

import ic2.core.platform.registries.IC2Items;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;

public class RumBrewRecipe {

    private final Item INPUT;
    private final Item OUTPUT;
    private final int TIME;
    int COUNT;

    public RumBrewRecipe(Item input, Item output, int count) {
        this.INPUT = input;
        this.COUNT = count;
        this.OUTPUT = output;
        this.TIME = (int) (1200.0 * (double)Math.max(1, count) * Math.pow(0.95, Math.max(0, count - 1)));
    }

    public ItemStack getInput() {
        return new ItemStack(this.INPUT, this.COUNT);
    }

    public ItemStack getOutput() {
        return getRumOutput(this.OUTPUT, this.COUNT);
    }

    public int getTime() {
        return this.TIME;
    }

    public static List<RumBrewRecipe> getRumRecipes() {
        List<RumBrewRecipe> recipes = new ObjectArrayList<>();
        Item MUG = IC2Items.MUG_RUM;
        Item GLASS = IC2Items.GLASS_RUM;
        recipes.add(new RumBrewRecipe(Items.SUGAR_CANE, MUG, 1));
        recipes.add(new RumBrewRecipe(Items.SUGAR_CANE, MUG, 16));
        recipes.add(new RumBrewRecipe(Items.SUGAR_CANE, MUG, 32));
        recipes.add(new RumBrewRecipe(Items.SUGAR_CANE, GLASS, 1));
        recipes.add(new RumBrewRecipe(Items.SUGAR_CANE, GLASS, 16));
        recipes.add(new RumBrewRecipe(Items.SUGAR_CANE, GLASS, 32));
        return recipes;
    }

    private static ItemStack getRumOutput(Item item, int count) {
        ItemStack stack = new ItemStack(item, count);
        stack.getOrCreateTag().putByte("progress", (byte) 100);
        return stack;
    }
}
