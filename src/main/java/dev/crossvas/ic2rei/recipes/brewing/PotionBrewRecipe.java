package dev.crossvas.ic2rei.recipes.brewing;

import ic2.api.recipes.registries.IPotionBrewRegistry;
import ic2.core.IC2;
import ic2.core.block.misc.tiles.BarrelTileEntity;
import ic2.core.platform.recipes.misc.GlobalRecipes;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectLists;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *  Copy-paste of {@link ic2.jeiplugin.core.recipes.categories.brew.PotionBrewCategory.PotionBrew}
 *  © IC2Classic Dev
 * */

public class PotionBrewRecipe implements Comparable<PotionBrewRecipe> {

    private final ItemStack BASE_INPUT;
    private final ItemStack INGREDIENT;
    private final ItemStack CONTAINER;
    private final ItemStack OUTPUT;

    private final MobEffect EFFECT;
    private final int REDSTONE;
    private final int GLOWSTONE;
    private final int DURATION;

    public PotionBrewRecipe(MobEffect effect, ItemStack ingredient, ItemStack container, ItemStack input, ItemStack output, int redstone, int glowstone, int duration) {
        this.EFFECT = effect;
        this.BASE_INPUT = input;
        this.INGREDIENT = ingredient;
        this.CONTAINER = container;
        this.OUTPUT = output;
        this.REDSTONE = redstone;
        this.GLOWSTONE = glowstone;
        this.DURATION = duration;
    }

    public ItemStack getBaseInput() {
        return this.BASE_INPUT;
    }

    public ItemStack getIngredient() {
        return this.INGREDIENT;
    }

    public ItemStack getContainer() {
        return this.CONTAINER;
    }

    public ItemStack getOutput() {
        return this.OUTPUT;
    }

    public ItemStack getRedstoneStack() {
        return new ItemStack(Items.REDSTONE, this.REDSTONE);
    }

    public ItemStack getGlowstoneStack() {
        return new ItemStack(Items.GLOWSTONE_DUST, this.GLOWSTONE);
    }

    public int getDuration() {
        return this.DURATION;
    }

    @Override
    public int compareTo(@NotNull PotionBrewRecipe recipe) {
        int result = Integer.compare(Registry.MOB_EFFECT.getId(this.EFFECT), Registry.MOB_EFFECT.getId(recipe.EFFECT));
        if (result != 0) {
            return result;
        } else {
            result = Integer.compare(this.REDSTONE, recipe.REDSTONE);
            if (result != 0) {
                return result;
            } else {
                result = Integer.compare(this.GLOWSTONE, recipe.GLOWSTONE);
                return result != 0 ? result : Integer.compare(this.DURATION, recipe.DURATION);
            }
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List<PotionBrewRecipe> getPotionRecipeList(GlobalRecipes globalRecipes) {
        List<PotionBrewRecipe> recipes = new ObjectArrayList<>();
        IPotionBrewRegistry registry = globalRecipes.potions;
        Map<Item, IPotionBrewRegistry.PotionContainer> containers = registry.getContainers();
        Map<Item, Map<MobEffect, MobEffect>> effects = registry.getEffects();

        for(int time = 0; time <= 6; time += 6) {
            for(int glow = 0; glow < 3; ++glow) {
                for(int red = 0; red < 3; ++red) {
                    int redstone = red == 0 ? 0 : (red == 1 ? 10 : 20);
                    int glowStone = glow == 0 ? 0 : (red == 1 ? 10 : 20);
                    int solid = time > 0 && red > 0 ? (int) Mth.clamp((float)time * ((float)redstone / 10.0F), 0.0F, (float)(BarrelTileEntity.POTION_DURATIONS.length - 1)) : 0;
                    int alc = time > 0 && glow > 0 ? (int)Mth.clamp((float)time * 0.5F * ((float)glowStone / 10.0F), 0.0F, 2.0F) : 0;
                    solid = switch (alc) {
                        case 1 -> (int) ((float) solid * 0.5F);
                        case 2 -> (int) ((float) solid * 0.25F);
                        default -> solid;
                    };

                    for (Map.Entry<Item, Map<MobEffect, MobEffect>> itemMapEntry : effects.entrySet()) {
                        Iterator var14 = ((Map) ((Map.Entry<Item, Map<MobEffect, MobEffect>>) (Map.Entry) itemMapEntry).getValue()).entrySet().iterator();

                        while (var14.hasNext()) {
                            Map.Entry<MobEffect, MobEffect> subEntry = (Map.Entry) var14.next();
                            ItemStack prev = subEntry.getKey() == null ? new ItemStack(Items.NETHER_WART) : createPotions((MobEffect) subEntry.getKey(), 0, 20, new ItemStack(Items.POTION));
                            Iterator var17 = containers.entrySet().iterator();

                            while (var17.hasNext()) {
                                Map.Entry<Item, IPotionBrewRegistry.PotionContainer> containerEntry = (Map.Entry) var17.next();
                                IPotionBrewRegistry.PotionContainer value = (IPotionBrewRegistry.PotionContainer) containerEntry.getValue();
                                ItemStack output = createPotions((MobEffect) subEntry.getValue(), alc, (int) Math.max(20.0F, (float) BarrelTileEntity.POTION_DURATIONS[10 - solid] * 20.0F * value.getDurationEffectiveness()), new ItemStack(value.getOutput(), 1000 / value.getFluidUsage()));
                                recipes.add(new PotionBrewRecipe((MobEffect) subEntry.getValue(), new ItemStack((ItemLike) ((Map.Entry<Item, Map<MobEffect, MobEffect>>) (Map.Entry) itemMapEntry).getKey()), new ItemStack((ItemLike) containerEntry.getKey(), 1000 / value.getFluidUsage()), prev, output, redstone, glowStone, time));
                            }
                        }
                    }
                }
            }
        }

        recipes.sort((Comparator)null);
        return recipes;
    }

    public static ItemStack createPotions(MobEffect effect, int level, int time, ItemStack output) {
        List<MobEffectInstance> effects = ObjectLists.singleton(new MobEffectInstance(effect, time, level));
        PotionUtils.setCustomEffects(output, effects);
        output.getOrCreateTag().putInt("CustomPotionColor", PotionUtils.getColor(effects));
        String outputName = output.getItem().getDescriptionId();
        MutableComponent name = Component.translatable(outputName + ".effect." + ((GlobalRecipes) IC2.RECIPES.get(false)).potions.getName(effect));
        name.setStyle(name.getStyle().withItalic(false));
        output.setHoverName(name);
        return output;
    }
}
