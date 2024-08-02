package dev.crossvas.ic2rei;

import ic2.core.platform.registries.IC2Items;
import me.shedaniel.rei.api.common.entry.comparison.ItemComparatorRegistry;
import me.shedaniel.rei.api.common.plugins.REIServerPlugin;
import me.shedaniel.rei.forge.REIPluginCommon;

@REIPluginCommon
public class IC2REIPluginCommon implements REIServerPlugin {

    @Override
    public void registerItemComparators(ItemComparatorRegistry registry) {
        registry.registerNbt(IC2Items.QUANTUM_SUIT_HELMET,
                IC2Items.QUANTUM_SUIT_CHESTPLATE,
                IC2Items.QUANTUM_SUIT_LEGGINGS,
                IC2Items.QUANTUM_SUIT_BOOTS);
    }
}
