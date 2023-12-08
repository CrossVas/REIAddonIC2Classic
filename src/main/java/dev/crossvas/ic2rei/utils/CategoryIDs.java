package dev.crossvas.ic2rei.utils;

import dev.crossvas.ic2rei.displays.*;
import dev.crossvas.ic2rei.displays.brewing.BeerBrewDisplay;
import dev.crossvas.ic2rei.displays.brewing.PotionBrewDisplay;
import dev.crossvas.ic2rei.displays.brewing.RumBrewDisplay;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;

public class CategoryIDs {

    public static final CategoryIdentifier<BaseMachineDisplay> MACERATOR = CategoryIdentifier.of("ic2", "plugins/macerator");
    public static final CategoryIdentifier<BaseMachineDisplay> EXTRACTOR = CategoryIdentifier.of("ic2", "plugins/extractor");
    public static final CategoryIdentifier<BaseMachineDisplay> COMPRESSOR = CategoryIdentifier.of("ic2", "plugins/compressor");
    public static final CategoryIdentifier<BaseMachineDisplay> SAWMILL = CategoryIdentifier.of("ic2", "plugins/sawmill");
    public static final CategoryIdentifier<ElectrolyzerDisplay> ELECTROLYZER = CategoryIdentifier.of("ic2", "plugins/electrolyzer");
    public static final CategoryIdentifier<RecyclerDisplay> RECYCLER = CategoryIdentifier.of("ic2", "plugins/recycler");
    public static final CategoryIdentifier<MassFabricatorDisplay> MASS_FABRICATOR = CategoryIdentifier.of("ic2", "plugins/mass_fabricator");
    public static final CategoryIdentifier<PlasmafierDisplay> PLASMAFIER = CategoryIdentifier.of("ic2", "plugins/plasmafier");
    public static final CategoryIdentifier<DoubleInputMachineDisplay> ALLOY_SMELTER = CategoryIdentifier.of("ic2", "plugins/alloy_smelter");
    public static final CategoryIdentifier<CannerDisplay.CannerRepairDisplay> CANNER_REPAIR = CategoryIdentifier.of("ic2", "plugins/canner_repair");
    public static final CategoryIdentifier<CannerDisplay.CannerRefuelDisplay> CANNER_REFUEL = CategoryIdentifier.of("ic2", "plugins/canner_refuel");
    public static final CategoryIdentifier<CannerDisplay.CannerRefillDisplay> CANNER_REFILL = CategoryIdentifier.of("ic2", "plugins/canner_refill");
    public static final CategoryIdentifier<ScrapboxDisplay> SCRAPBOX = CategoryIdentifier.of("ic2", "plugins/scrapbox");
    public static final CategoryIdentifier<GeneratorDisplay> GENERATOR = CategoryIdentifier.of("ic2", "plugins/generator");
    public static final CategoryIdentifier<FluidGeneratorsDisplay.FluidGeneratorDisplay> GEOTHERMAL_GENERATOR = CategoryIdentifier.of("ic2", "plugins/geothermal_generator");
    public static final CategoryIdentifier<FluidGeneratorsDisplay.FluidGeneratorDisplay> FLUID_GENERATOR = CategoryIdentifier.of("ic2", "plugins/fluid_generator");
    public static final CategoryIdentifier<EarthExtractorDisplay> EARTH_EXTRACTOR = CategoryIdentifier.of("ic2", "plugins/earth_extractor");
    public static final CategoryIdentifier<RefineryDisplay> REFINERY = CategoryIdentifier.of("ic2", "plugins/refinery");
    public static final CategoryIdentifier<EnricherDisplay> ENRICHER = CategoryIdentifier.of("ic2", "plugins/enricher");
    public static final CategoryIdentifier<WoodGassifierDisplay> WOOD_GASSIFIER = CategoryIdentifier.of("ic2", "plugins/woodgassifier");
    public static final CategoryIdentifier<RumBrewDisplay> RUM_BREWING = CategoryIdentifier.of("ic2", "plugins/rum_brewing");
    public static final CategoryIdentifier<BeerBrewDisplay> BEER_BREWING = CategoryIdentifier.of("ic2", "plugins/beer_brewing");
    public static final CategoryIdentifier<PotionBrewDisplay> POTION_BREWING = CategoryIdentifier.of("ic2", "plugins/potion_brewing");
    public static final CategoryIdentifier<NuclearReactorDisplay> NUCLEAR_REACTOR = CategoryIdentifier.of("ic2", "plugins/nuclear_reactor");
}
