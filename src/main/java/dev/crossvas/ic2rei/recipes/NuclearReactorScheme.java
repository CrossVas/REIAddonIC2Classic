package dev.crossvas.ic2rei.recipes;

import ic2.core.block.machines.logic.planner.encoder.ReactorSetup;
import ic2.core.block.machines.logic.planner.newLogic.SimulationResult;
import ic2.core.item.base.IC2Item;
import ic2.core.item.reactor.base.ReactorComponentBase;
import ic2.core.platform.player.ReactorSubmissions;
import ic2.core.utils.collection.CollectionUtils;
import ic2.core.utils.helpers.Formatters;
import ic2.core.utils.tooltips.ILangHelper;
import it.unimi.dsi.fastutil.objects.AbstractObject2DoubleMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.time.DurationFormatUtils;

import java.util.List;

public class NuclearReactorScheme implements ILangHelper {

    public static final String[] EU_SUFFIX = new String[]{"EU", "k EU", "m EU", "b EU"};
    public static final String[] BUCKET_SUFFIX = new String[]{"mB", "B", "kB", "MB"};
    public final String name;
    public final String data;
    public final ReactorSetup setup;
    public final List<Component> text = CollectionUtils.createList();

    public NuclearReactorScheme(String name, ReactorSetup setup) {
        this.name = name;
        this.data = setup.setup;
        this.setup = setup;
        this.createText();
    }

    private void createText() {
        SimulationResult result = this.setup.simulation;
        this.text.add(this.translate("jei.ic2.reactor.by", this.name));
        this.text.add(this.translate("jei.ic2.reactor.stats", this.bool(result != null ? result.isStable : this.setup.isStable), this.bool(this.setup.breeder)));
        this.text.add(this.translate("jei.ic2.reactor.efficiency", Formatters.XP_FORMAT.format((double)this.setup.efficiency), Formatters.XP_FORMAT.format((double)this.setup.totalEfficiency)));
        this.text.add(this.translate("jei.ic2.reactor.heat", Formatters.EU_FORMAT.format((long)this.setup.startingHeat)));
        MutableComponent effective;
        if (this.setup.isSteam) {
            this.text.add(this.translate("jei.ic2.reactor.steam", this.getValue(this.setup.totalOutput, true), Formatters.XP_FORMAT.format((double)this.setup.output)));
            if (result != null) {
                effective = this.translate("jei.ic2.reactor.steam.effective", this.getValue((long)((float)this.setup.totalOutput * result.effectiveProduction), true), Formatters.XP_FORMAT.format((double)(this.setup.output * result.effectiveProduction)));
                this.text.add(effective.setStyle(Style.EMPTY.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, this.translate("jei.ic2.reactor.effective")))));
            }
        } else {
            this.text.add(this.translate("jei.ic2.reactor.output", this.getValue(this.setup.totalOutput, false), Formatters.EU_FORMAT.format((double)this.setup.output)));
            if (result != null) {
                effective = this.translate("jei.ic2.reactor.output.effective", this.getValue((long)((float)this.setup.totalOutput * result.effectiveProduction), false), Formatters.EU_FORMAT.format((double)(this.setup.output * result.effectiveProduction)));
                this.text.add(effective.setStyle(Style.EMPTY.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, this.translate("jei.ic2.reactor.effective")))));
            }
        }

        if (result != null) {
            this.text.add(this.translate("Gen/Cool Time: %s/%s", DurationFormatUtils.formatDuration((long)(result.ticksSimulated * result.tickRate) * 50L, "HH:mm:ss"), DurationFormatUtils.formatDuration((long)result.ticksCooled * 50L, "HH:mm:ss")));
        }

    }

    private String getValue(long value, boolean bucket) {
        Object2DoubleMap.Entry<String> entry = this.getSuffics(value, bucket);
        String var10000 = Formatters.EU_FORMAT.format((double)this.setup.totalOutput / entry.getDoubleValue());
        return var10000 + (String)entry.getKey();
    }

    private Object2DoubleMap.Entry<String> getSuffics(long input, boolean bucket) {
        double value = 1.0;

        int index;
        for(index = 0; input > 1000L && index + 1 < BUCKET_SUFFIX.length; input /= 1000L) {
            ++index;
            value *= 1000.0;
        }

        return new AbstractObject2DoubleMap.BasicEntry(bucket ? BUCKET_SUFFIX[index] : EU_SUFFIX[index], value);
    }

    public boolean isValid() {
        return this.setup != null;
    }

    public List<ItemStack> getRectorComponents() {
        List<ItemStack> components = new ObjectArrayList<>();
        ForgeRegistries.ITEMS.forEach(item -> {
            if (item instanceof IC2Item ic2Item) {
                if (ic2Item instanceof ReactorComponentBase reactorComponent) {
                    components.add(new ItemStack(reactorComponent));
                }
            }
        });
        return components;
    }

    public static List<NuclearReactorScheme> getReactorSchemes() {
        List<NuclearReactorScheme> schemes = new ObjectArrayList<>();
        ReactorSubmissions.INSTANCE.getSubmissions((T) -> {
            NuclearReactorScheme recipe = new NuclearReactorScheme(T.getKey(), T.getValue());
            if (recipe.isValid()) {
                schemes.add(recipe);
            }

        });
        return schemes;
    }
}
