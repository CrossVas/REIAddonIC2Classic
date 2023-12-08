package dev.crossvas.ic2rei.recipes;

import ic2.api.recipes.RecipeRegistry;
import ic2.api.recipes.ingridients.inputs.IInput;
import ic2.api.recipes.ingridients.inputs.ItemInput;
import ic2.api.recipes.registries.ICannerRecipeRegistry;
import ic2.core.platform.recipes.misc.GlobalRecipes;
import ic2.core.platform.registries.IC2Items;
import net.minecraft.util.Mth;
import net.minecraft.util.Tuple;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CannerRecipe {

    private final ItemStack container;
    private final Object recipe;

    public CannerRecipe(ItemStack container, Object recipe) {
        this.container = container;
        this.recipe = recipe;
    }

    public ItemStack getContainer() {
        return this.container;
    }

    public ICannerRecipeRegistry.FuelValue getFuels() {
        return (ICannerRecipeRegistry.FuelValue)this.recipe;
    }

    @SuppressWarnings("unchecked")
    public Tuple<IInput, Integer> getRepair() {
        return (Tuple<IInput, Integer>) this.recipe;
    }

    @SuppressWarnings("unchecked")
    public Tuple<IInput, ItemStack> getFilling() {
        return (Tuple<IInput, ItemStack>) this.recipe;
    }

    // Refill
    public static List<CannerRecipe> getRefillRecipes(GlobalRecipes recipe) {
        List<CannerRecipe> cannerRecipes = new ArrayList<>();
        ICannerRecipeRegistry registry = recipe.canner;

        Set<Map.Entry<ItemStack, List<Tuple<IInput, ItemStack>>>> fillableSet = registry.getFillables().entrySet();
        for (Map.Entry<ItemStack, List<Tuple<IInput, ItemStack>>> entry : fillableSet) {
            List<Tuple<IInput, ItemStack>> entryValues = entry.getValue();
            for (Tuple<IInput, ItemStack> entryValue : entryValues) {
                cannerRecipes.add(new CannerRecipe(entry.getKey(), entryValue));
            }
        }

        ForgeRegistries.ITEMS.forEach(item -> {
            if (item.isEdible()) {
                FoodProperties foodProperties = item.getFoodProperties(new ItemStack(item), null);
                if (foodProperties != null) {
                    int saturation = Math.max(1, Mth.ceil(foodProperties.getNutrition() / 2.0));
                    if (saturation > 0) {
                        ItemStack input = new ItemStack(IC2Items.TIN_CAN, saturation);
                        Item foodResult = RecipeRegistry.CAN_EFFECTS.getItemForFood(new ItemStack(item));
                        ItemStack output;
                        if (foodResult == null) {
                            output = new ItemStack(IC2Items.TIN_CAN_FILLED, saturation);
                        } else {
                            output = new ItemStack(foodResult, saturation);
                        }
                        cannerRecipes.add(new CannerRecipe(input, new Tuple<>(new ItemInput(item), output)));
                    }
                }
            }
        });

        return cannerRecipes;
    }

    // Repair
    public static List<CannerRecipe> getRepairRecipes(GlobalRecipes recipe) {
        List<CannerRecipe> cannerRecipes = new ArrayList<>();
        ICannerRecipeRegistry registry = recipe.canner;

        cannerRecipes.add(new CannerRecipe(new ItemStack(IC2Items.ELECTRIC_SPRAYER), new Tuple<>(new ItemInput(IC2Items.CF_PELLET), 26)));
        cannerRecipes.add(new CannerRecipe(new ItemStack(IC2Items.CF_PACK), new Tuple<>(new ItemInput(IC2Items.CF_PELLET), 26)));

        Set<Map.Entry<Item, List<Tuple<IInput, Integer>>>> repairSet = registry.getRepairItems().entrySet();
        for (Map.Entry<Item, List<Tuple<IInput, Integer>>> entry : repairSet) {
            ItemStack stack = new ItemStack(entry.getKey());
            List<Tuple<IInput, Integer>> entryValues = entry.getValue();
            for (Tuple<IInput, Integer> entryValue : entryValues) {
                cannerRecipes.add(new CannerRecipe(stack, entryValue));
            }
        }

        return cannerRecipes;
    }

    // Refuel
    public static List<CannerRecipe> getFuelingRecipes(GlobalRecipes recipe) {
        List<CannerRecipe> cannerRecipes = new ArrayList<>();
        ICannerRecipeRegistry registry = recipe.canner;

        List<ICannerRecipeRegistry.FuelValue> fuels = registry.getFuels();
        for (ICannerRecipeRegistry.FuelValue fuel : fuels) {
            cannerRecipes.add(new CannerRecipe(new ItemStack(IC2Items.FUEL_CAN), fuel));
        }
        return cannerRecipes;
    }
}
