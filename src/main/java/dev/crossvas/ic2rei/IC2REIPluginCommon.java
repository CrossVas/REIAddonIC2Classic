package dev.crossvas.ic2rei;

import ic2.core.platform.registries.IC2Items;
import me.shedaniel.rei.api.common.entry.comparison.EntryComparator;
import me.shedaniel.rei.api.common.entry.comparison.ItemComparatorRegistry;
import me.shedaniel.rei.api.common.plugins.REIServerPlugin;
import me.shedaniel.rei.forge.REIPluginCommon;
import me.shedaniel.rei.impl.Internals;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;

@SuppressWarnings("UnstableApiUsage")
@REIPluginCommon
public class IC2REIPluginCommon implements REIServerPlugin {

    @Override
    public void registerItemComparators(ItemComparatorRegistry registry) {
        registry.register(subTag("display", "color"), IC2Items.QUANTUM_SUIT_HELMET,
                IC2Items.QUANTUM_SUIT_CHESTPLATE,
                IC2Items.QUANTUM_SUIT_LEGGINGS,
                IC2Items.QUANTUM_SUIT_BOOTS);
    }

    public static EntryComparator<ItemStack> subTag(String tag, String subTag) {
        EntryComparator<Tag> hasher = Internals.getNbtHasher(new String[]{tag});
        return (comparisonContext, stack) -> {
            CompoundTag stackTag = stack.getOrCreateTag().getCompound(tag);
            return !stackTag.contains(subTag) ? 0 : hasher.hash(comparisonContext, stackTag);
        };
    }
}
