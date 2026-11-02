package com.ultimatestattracker;

import net.runelite.api.Skill;
import net.runelite.client.plugins.itemstats.stats.Stat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StatKeys {
     // --- Items ---
     public static String EXAMINE = "itemsExamined";
     public static String ITEMS_DROPPED = "itemsDropped";
     public static String ITEMS_EQUIPPED = "itemsEquipped";
     public static String VIALS_SMASHED = "vialsSmashed";
     public static String HERBS_PLACED_IN_HERB_SACK = "herbsPlacedInHerbSack";
     public static String GUAM_LEAF_PLACED = "guamLeafPlaced";
     public static String MARRENTILL_PLACED = "marrentillPlaced";
     public static String TARROMIN_PLACED = "tarrominPlaced";
     public static String HARRALANDER_PLACED = "harralanderPlaced";
     public static String RANARR_WEED_PLACED = "ranarrWeedPlaced";
     public static String TOADFLAX_PLACED = "toadflaxPlaced";
     public static String IRIT_LEAF_PLACED = "iritLeafPlaced";
     public static String AVANTOE_PLACED = "avantoePlaced";
     public static String KWUARM_PLACED = "kwuarmPlaced";
     public static String HUASCA_PLACED = "huascaPlaced";
     public static String SNAPDRAGON_PLACED = "snapdragonPlaced";
     public static String CADANTINE_PLACED = "cadantinePlaced";
     public static String LANTADYME_PLACED = "lantadymePlaced";
     public static String DWARF_WEED_PLACED = "dwarfWeedPlaced";
     public static String TORSTOL_PLACED = "torstolPlaced";

     // --- Shops ---
     public static String SHOP_GP_SPENT = "shopGpSpent";
     public static String SHOP_GP_GAINED = "shopGpGained";

     // --- Movement ---
     public static String TILES_WALKED = "tilesWalked";
     public static String TILES_RAN = "tilesRan";
     public static String SPIRIT_TREE_TELES = "spiritTreeTeles";
     public static String FAIRY_RING_TELES = "fairyRingTeles";
     public static String VARROCK_TELEPORT = "varrockTeleport";
     public static String LUMBRIDGE_TELEPORT = "lumbridgeTeleport";
     public static String FALADOR_TELEPORT = "faladorTeleport";
     public static String CAMELOT_TELEPORT = "camelotTeleport";
     public static String ARDOUGNE_TELEPORT = "ardougneTeleport";
     public static String WATCHTOWER_TELEPORT = "watchtowerTeleport";
     public static String TROLLHEIM_TELEPORT = "trollheimTeleport";
     public static String APE_ATOLL_TELEPORT = "apeAtollTeleport";
     public static String KOUREND_TELEPORT = "kourendTeleport";
     public static String GRAND_EXCHANGE_TELEPORT = "grandExchangeTeleport";
     public static String SEERS_VILLAGE_TELEPORT = "seersVillageTeleport";
     public static String FORTIS_TELEPORT = "fortisTeleport";
     public static String YANILLE_TELEPORT = "yanilleTeleport";
     public static String HOUSE_TELEPORT = "houseTeleport";
     public static String TOTAL_TELEPORTS = "totalTeleports";
     public static String DOORS_OPENED = "doorsOpened";
     public static String DOORS_CLOSED = "doorsClosed";
     public static String LADDERS_STAIRS_CLIMBED = "laddersStairsClimbed";

     // --- Fishing ---
     public static String FISH_CAUGHT = "fishCaught";
     public static String SHRIMP_CAUGHT = "shrimpCaught";
     public static String ANCHOVIES_CAUGHT = "anchoviesCaught";
     public static String SARDINE_CAUGHT = "sardineCaught";
     public static String HERRING_CAUGHT = "herringCaught";
     public static String TROUT_CAUGHT = "troutCaught";
     public static String SALMON_CAUGHT = "salmonCaught";
     public static String PIKE_CAUGHT = "pikeCaught";
     public static String COD_CAUGHT = "codCaught";
     public static String BASS_CAUGHT = "bassCaught";
     public static String TUNA_CAUGHT = "tunaCaught";
     public static String LOBSTER_CAUGHT = "lobsterCaught";
     public static String SWORDFISH_CAUGHT = "swordfishCaught";
     public static String MONKFISH_CAUGHT = "monkfishCaught";
     public static String SHARK_CAUGHT = "sharkCaught";
     public static String SEA_TURTLE_CAUGHT = "seaTurtleCaught";
     public static String MANTA_RAY_CAUGHT = "mantaRayCaught";
     public static String ANGLERFISH_CAUGHT = "anglerfishCaught";
     public static String KARAMBWAN_CAUGHT = "karambwanCaught";
     public static String DARK_CRAB_CAUGHT = "darkCrabCaught";
     public static String MINNOW_CAUGHT = "minnowCaught";
     public static String SACRED_EEL_CAUGHT = "sacredEelCaught";
     public static String LEAPING_TROUT_CAUGHT = "leapingTroutCaught";
     public static String LEAPING_SALMON_CAUGHT = "leapingSalmonCaught";
     public static String LEAPING_STURGEON_CAUGHT = "leapingSturgeonCaught";
     public static String HARPOON_FISH_CAUGHT = "harpoonFishCaught";
     public static String SPIRIT_POOL_STABS = "spiritPoolStabs";
     public static String RADAS_BLESSING_PROCS = "radasBlessingProcs";
     public static String DRAGON_HARPOON_SPECS = "dragonHarpoonSpecs";

     // --- Sailing ---
     public static String WIND_MOTES_RELEASED = "windMotesReleased";
     public static String SAILS_TRIMMED = "sailsTrimmed";
     public static String TRAWLING_FISH_CAUGHT = "trawlingFishCaught";
     public static String HADDOCK_CAUGHT = "haddockCaught";
     public static String GIANT_KRILL_CAUGHT = "giantKrillCaught";
     public static String YELLOWFIN_CAUGHT = "yellowfinCaught";
     public static String HALIBUT_CAUGHT = "halibutCaught";
     public static String BLUEFIN_CAUGHT = "bluefinCaught";
     public static String MARLIN_CAUGHT = "marlinCaught";
     public static String CREW_WORK_WATCHED = "crewWorkWatched";
     public static String SHOALS_BAITED = "shoalsBaited";
     public static String BOATS_BOARDED = "boatsBoarded";
     public static String SALVAGE_DREDGED = "salvageDredged";
     public static String SALVAGE_SORTED = "salvageSorted";
     public static String SMALL_SALVAGE_SORTED = "smallSalvageSorted";
     public static String FISHY_SALVAGE_SORTED = "fishySalvageSorted";
     public static String BARRACUDA_SALVAGE_SORTED = "barracudaSalvageSorted";
     public static String LARGE_SALVAGE_SORTED = "largeSalvageSorted";
     public static String PLUNDERED_SALVAGE_SORTED = "plunderedSalvageSorted";
     public static String MARTIAL_SALVAGE_SORTED = "martialSalvageSorted";
     public static String FREMENNIK_SALVAGE_SORTED = "fremennikSalvageSorted";
     public static String OPULENT_SALVAGE_SORTED = "opulentSalvageSorted";
     // --- Mining ---
     public static String ROCKS_MINED = "rocksMined";
     public static String CLAY_MINED = "clayMined";
     public static String COPPER_ORE_MINED = "copperOreMined";
     public static String TIN_ORE_MINED = "tinOreMined";
     public static String LIMESTONE_MINED = "limestoneMined";
     public static String IRON_ORE_MINED = "ironOreMined";
     public static String SILVER_ORE_MINED = "silverOreMined";
     public static String COAL_MINED = "coalMined";
     public static String GOLD_ORE_MINED = "goldOreMined";
     public static String MITHRIL_ORE_MINED = "mithrilOreMined";
     public static String ADAMANTITE_ORE_MINED = "adamantiteOreMined";
     public static String RUNITE_ORE_MINED = "runiteOreMined";
     public static String GRANITE_MINED = "graniteMined";
     public static String SANDSTONE_MINED = "sandstoneMined";
     public static String PAY_DIRT_MINED = "payDirtMined";
     public static String AMETHYST_MINED = "amethystMined";
     public static String PURE_ESSENCE_MINED = "pureEssenceMined";
     // --- Woodcutting ---
     public static String LOGS_CHOPPED = "logsChopped";
     public static String NORMAL_LOGS_CHOPPED = "normalLogsChopped";
     public static String OAK_LOGS_CHOPPED = "oakLogsChopped";
     public static String WILLOW_LOGS_CHOPPED = "willowLogsChopped";
     public static String TEAK_LOGS_CHOPPED = "teakLogsChopped";
     public static String MAPLE_LOGS_CHOPPED = "mapleLogsChopped";
     public static String MAHOGANY_LOGS_CHOPPED = "mahoganyLogsChopped";
     public static String YEW_LOGS_CHOPPED = "yewLogsChopped";
     public static String MAGIC_LOGS_CHOPPED = "magicLogsChopped";
     public static String REDWOOD_LOGS_CHOPPED = "redwoodLogsChopped";
     public static String LETVEKS_SHOOED = "letveksShooed";
     public static String BLOODWOOD_SAP_BUCKETS_FILLED = "bloodwoodSapBucketsFilled";
     public static String BIRD_NESTS_DROPPED = "birdNestsDropped";

     // --- Gathering ---
     public static String FLAX_PICKED = "flaxPicked";
     public static String CABBAGE_PICKED = "cabbagePicked";

     // -- Farming --
     public static String WEEDS_RAKED = "weedsRaked";
     public static String SEEDS_PLANTED = "seedsPlanted";

     // -- Misc --
     public static String CRITTERS_PET = "crittersPet";
     public static String THE_GUNS = "theGunsHiCount";
     public static String CLUE_SCROLLS_MISSED = "clueScrollsMissed";
     public static String BEGINNER_CLUE_SCROLLS_MISSED = "beginnerClueScrollsMissed";
     public static String EASY_CLUE_SCROLLS_MISSED = "easyClueScrollsMissed";
     public static String MEDIUM_CLUE_SCROLLS_MISSED = "mediumClueScrollsMissed";
     public static String HARD_CLUE_SCROLLS_MISSED = "hardClueScrollsMissed";
     public static String EXPERT_CLUE_SCROLLS_MISSED = "expertClueScrollsMissed";
     public static String MASTER_CLUE_SCROLLS_MISSED = "masterClueScrollsMissed";

     // --- Lamps ---
     //Totals across every skill. The per skill keys are generated in lampsUsedKey/lampXpKey
     //from the Skill enum so that all 24 skills stay in sync without a hand written list.
     public static String LAMPS_USED = "lampsUsed";
     public static String LAMP_XP = "lampXp";

     // --- Prayer ---
     public static String BONES_BURIED = "bonesBuried";
     public static String ASHES_SCATTERED = "ashesScattered";
     // --- Firemaking ---
     public static String LOGS_BURNED = "logsBurned";

     // --- Food & Healing ---
     public static String FOOD_EATEN = "foodEaten";
     public static String HP_REGEN = "hpRegen";
     public static String BEER_DRANK = "beerDrank";
     public static String CABBAGE_EATEN = "cabbageEaten";
     public static String TROUT_EATEN = "troutEaten";
     public static String SHRIMP_EATEN = "shrimpEaten";
     public static String ANCHOVIES_EATEN = "anchoviesEaten";
     public static String SARDINE_EATEN = "sardineEaten";
     public static String HERRING_EATEN = "herringEaten";
     public static String PIKE_EATEN = "pikeEaten";
     public static String COD_EATEN = "codEaten";
     public static String SALMON_EATEN = "salmonEaten";
     public static String TUNA_EATEN = "tunaEaten";
     public static String BASS_EATEN = "bassEaten";
     public static String LOBSTER_EATEN = "lobsterEaten";
     public static String SWORDFISH_EATEN = "swordfishEaten";
     public static String MONKFISH_EATEN = "monkfishEaten";
     public static String SHARK_EATEN = "sharkEaten";
     public static String MANTA_RAY_EATEN = "mantaRayEaten";
     public static String ANGLERFISH_EATEN = "anglerfishEaten";
     public static String KARAMBWAN_EATEN = "karambwanEaten";
     public static String DARK_CRAB_EATEN = "darkCrabEaten";
     public static String SEA_TURTLE_EATEN = "seaTurtleEaten";
     public static String CAKE_SLICES_EATEN = "cakeSlicesEaten";
     public static String FOOD_COOKED = "foodCooked";
     public static String FOOD_BURNED = "foodBurned";
     public static String PINEAPPLE_RINGS_CUT = "pineappleRingsCut";
     public static String POTION_SIPS_DRANK = "potionSips";
     public static String DIVINE_DAMAGE = "divineDamage";
     public static String SHRIMP_COOKED = "shrimpCooked";
     public static String ANCHOVIES_COOKED = "anchoviesCooked";
     public static String TROUT_COOKED = "troutCooked";
     public static String SALMON_COOKED = "salmonCooked";
     public static String LOBSTER_COOKED = "lobsterCooked";
     public static String SWORDFISH_COOKED = "swordfishCooked";
     public static String SHARK_COOKED = "sharkCooked";
     public static String MONKFISH_COOKED = "monkfishCooked";
     public static String KARAMBWAN_COOKED = "karambwanCooked";
     public static String ANGLERFISH_COOKED = "anglerfishCooked";
     public static String SHRIMP_BURNED = "shrimpBurned";
     public static String ANCHOVIES_BURNED = "anchoviesBurned";
     public static String TROUT_BURNED = "troutBurned";
     public static String SALMON_BURNED = "salmonBurned";
     public static String LOBSTER_BURNED = "lobsterBurned";
     public static String SWORDFISH_BURNED = "swordfishBurned";
     public static String SHARK_BURNED = "sharkBurned";
     public static String MONKFISH_BURNED = "monkfishBurned";
     public static String KARAMBWAN_BURNED = "karambwanBurned";
     public static String ANGLERFISH_BURNED = "anglerfishBurned";

     // --- Deep Sea Trawling fish ---
     public static String HADDOCK_COOKED = "haddockCooked";
     public static String GIANT_KRILL_COOKED = "giantKrillCooked";
     public static String YELLOWFIN_COOKED = "yellowfinCooked";
     public static String HALIBUT_COOKED = "halibutCooked";
     public static String BLUEFIN_COOKED = "bluefinCooked";
     public static String MARLIN_COOKED = "marlinCooked";
     public static String SEA_TURTLE_COOKED = "seaTurtleCooked";
     public static String DASHING_KEBBIT_COOKED = "dashingKebbitCooked";
     public static String SUNLIGHT_ANTELOPE_COOKED = "sunlightAntelopeCooked";
     public static String MOONLIGHT_ANTELOPE_COOKED = "moonlightAntelopeCooked";
     public static String PYRE_FOX_COOKED = "pyreFoxCooked";
     public static String HADDOCK_BURNED = "haddockBurned";
     public static String GIANT_KRILL_BURNED = "giantKrillBurned";
     public static String YELLOWFIN_BURNED = "yellowfinBurned";
     public static String HALIBUT_BURNED = "halibutBurned";
     public static String BLUEFIN_BURNED = "bluefinBurned";
     public static String MARLIN_BURNED = "marlinBurned";
     public static String SEA_TURTLE_BURNED = "seaTurtleBurned";
     public static String DASHING_KEBBIT_BURNED = "dashingKebbitBurned";
     public static String SUNLIGHT_ANTELOPE_BURNED = "sunlightAntelopeBurned";
     public static String MOONLIGHT_ANTELOPE_BURNED = "moonlightAntelopeBurned";
     public static String PYRE_FOX_BURNED = "pyreFoxBurned";
     public static String HADDOCK_EATEN = "haddockEaten";
     public static String GIANT_KRILL_EATEN = "giantKrillEaten";
     public static String YELLOWFIN_EATEN = "yellowfinEaten";
     public static String HALIBUT_EATEN = "halibutEaten";
     public static String BLUEFIN_EATEN = "bluefinEaten";
     public static String MARLIN_EATEN = "marlinEaten";
     public static String DASHING_KEBBIT_EATEN = "dashingKebbitEaten";
     public static String SUNLIGHT_ANTELOPE_EATEN = "sunlightAntelopeEaten";
     public static String MOONLIGHT_ANTELOPE_EATEN = "moonlightAntelopeEaten";
     public static String PYRE_FOX_EATEN = "pyreFoxEaten";

     // --- Thieving ---
     public static String PICK_POCKETS = "pickPockets";
     public static String FAILED_PICK_POCKETS = "failedPickPockets";
     public static String STALLS_THIEVED = "stallsThieved";

     // --- Agility ---
     public static String ROOF_TOP_AGILITY = "rooftopAgilityLaps";
     public static String NORMAL_AGILITY = "normalAgilityLaps";

     // --- Herblore ---
     public static String UNFINISHED_POTIONS_MADE = "unfinishedPotionsMade";
     public static String POTIONS_MADE = "potionsMade";
     public static String HERBS_CLEANED = "herbsCleaned";

     // --  Runecraft --
     public static String RUNES_CRAFTED = "runesCrafted";

     // -- Crafting --
     public static String GEMS_CUT = "gemsCut";
     public static String GLASS_BLOWN = "glassBlown";
     public static String HIDES_TANNED = "hidesTanned";
     public static String GREEN_DHIDE_TANNED = "greenDhideTanned";
     public static String BLUE_DHIDE_TANNED = "blueDhideTanned";
     public static String RED_DHIDE_TANNED = "redDhideTanned";
     public static String BLACK_DHIDE_TANNED = "blackDhideTanned";
     public static String COWHIDE_TANNED = "cowhideTanned";

     //-- Fletching --
     public static String BOWS_FLECTHED = "bowsFletched";
     public static String BOWS_STRUNG = "bowsStrung";
     public static String DARTS_FLECTHED = "dartsFletched";

     //-- Hunter --
     public static String CREATURES_TRAPPED = "creaturesTrapped";
     public static String IMPLINGS_CAUGHT = "implingsCaught";

     //-- Smithing --
     public static String BARS_SMELTED = "barsSmelted";
     public static String ITEMS_SMITHED = "itemsSmithed";

     // -- Combat --
     public static String DAMAGE_DONE = "damageDone";
     public static String DAMAGE_RECEIVED = "damageReceived";
     public static String BIGGEST_HITSPLAT = "biggestHitsplat";
     public static String ATTACKS_BLOCKED = "attacksBlocked";
     public static String ATTACKS_MISSED = "attacksMissed";
     public static String DEATHS = "timesDied";
     public static String LESSER_GHOSTS_SUMMONED = "lesserGhostsSummoned";
     public static String LESSER_SKELETONS_SUMMONED = "lesserSkeletonsSummoned";
     public static String LESSER_ZOMBIES_SUMMONED = "lesserZombiesSummoned";
     public static String SUPERIOR_GHOSTS_SUMMONED = "superiorGhostsSummoned";
     public static String SUPERIOR_SKELETONS_SUMMONED = "superiorSkeletonsSummoned";
     public static String SUPERIOR_ZOMBIES_SUMMONED = "superiorZombiesSummoned";
     public static String GREATER_GHOSTS_SUMMONED = "greaterGhostsSummoned";
     public static String GREATER_SKELETONS_SUMMONED = "greaterSkeletonsSummoned";
     public static String GREATER_ZOMBIES_SUMMONED = "greaterZombiesSummoned";


     //note, cant use reflection here as runelite does not allow it
     public static final StatKey[] ALL_KEYS = withLampKeys(new StatKey[]{
             // --- Items ---
             new StatKey(EXAMINE, StatCategory.ITEMS, "Things Examined"),
             new StatKey(ITEMS_DROPPED, StatCategory.ITEMS, "Items Dropped"),
             new StatKey(ITEMS_EQUIPPED, StatCategory.ITEMS, "Items Equipped"),
             new StatKey(VIALS_SMASHED, StatCategory.ITEMS, "Vials Smashed"),
             new StatKey(HERBS_PLACED_IN_HERB_SACK, StatCategory.ITEMS, "Herbs Looted in Herb Sack"),
             new StatKey(GUAM_LEAF_PLACED, StatCategory.ITEMS, "Guam Leaf Looted in Herb Sack"),
             new StatKey(MARRENTILL_PLACED, StatCategory.ITEMS, "Marrentill Looted in Herb Sack"),
             new StatKey(TARROMIN_PLACED, StatCategory.ITEMS, "Tarromin Looted in Herb Sack"),
             new StatKey(HARRALANDER_PLACED, StatCategory.ITEMS, "Harralander Looted in Herb Sack"),
             new StatKey(RANARR_WEED_PLACED, StatCategory.ITEMS, "Ranarr Weed Looted in Herb Sack"),
             new StatKey(TOADFLAX_PLACED, StatCategory.ITEMS, "Toadflax Looted in Herb Sack"),
             new StatKey(IRIT_LEAF_PLACED, StatCategory.ITEMS, "Irit Leaf Looted in Herb Sack"),
             new StatKey(AVANTOE_PLACED, StatCategory.ITEMS, "Avantoe Looted in Herb Sack"),
             new StatKey(KWUARM_PLACED, StatCategory.ITEMS, "Kwuarm Looted in Herb Sack"),
             new StatKey(HUASCA_PLACED, StatCategory.ITEMS, "Huasca Looted in Herb Sack"),
             new StatKey(SNAPDRAGON_PLACED, StatCategory.ITEMS, "Snapdragon Looted in Herb Sack"),
             new StatKey(CADANTINE_PLACED, StatCategory.ITEMS, "Cadantine Looted in Herb Sack"),
             new StatKey(LANTADYME_PLACED, StatCategory.ITEMS, "Lantadyme Looted in Herb Sack"),
             new StatKey(DWARF_WEED_PLACED, StatCategory.ITEMS, "Dwarf Weed Looted in Herb Sack"),
             new StatKey(TORSTOL_PLACED, StatCategory.ITEMS, "Torstol Looted in Herb Sack"),

             // --- Shops ---
             new StatKey(SHOP_GP_SPENT, StatCategory.SHOPS, "Shop GP Spent"),
             new StatKey(SHOP_GP_GAINED, StatCategory.SHOPS, "Shop GP Gained"),

             // --- Movement / Transportation ---
             new StatKey(TILES_WALKED, StatCategory.MOVEMENT, "Tiles Walked"),
             new StatKey(TILES_RAN, StatCategory.MOVEMENT, "Tiles Ran"),
             new StatKey(SPIRIT_TREE_TELES, StatCategory.MOVEMENT, "Spirit Tree Teleports"),
             new StatKey(FAIRY_RING_TELES, StatCategory.MOVEMENT, "Fairy Ring Teleports"),
             new StatKey(VARROCK_TELEPORT, StatCategory.MOVEMENT, "Varrock Teleports"),
             new StatKey(LUMBRIDGE_TELEPORT, StatCategory.MOVEMENT, "Lumbridge Teleports"),
             new StatKey(FALADOR_TELEPORT, StatCategory.MOVEMENT, "Falador Teleports"),
             new StatKey(CAMELOT_TELEPORT, StatCategory.MOVEMENT, "Camelot Teleports"),
             new StatKey(ARDOUGNE_TELEPORT, StatCategory.MOVEMENT, "Ardougne Teleports"),
             new StatKey(WATCHTOWER_TELEPORT, StatCategory.MOVEMENT, "Watchtower Teleports"),
             new StatKey(TROLLHEIM_TELEPORT, StatCategory.MOVEMENT, "Trollheim Teleports"),
             new StatKey(APE_ATOLL_TELEPORT, StatCategory.MOVEMENT, "Ape Atoll Teleports"),
             new StatKey(KOUREND_TELEPORT, StatCategory.MOVEMENT, "Kourend Teleports"),
             new StatKey(GRAND_EXCHANGE_TELEPORT, StatCategory.MOVEMENT, "Grand Exchange Teleports"),
             new StatKey(SEERS_VILLAGE_TELEPORT, StatCategory.MOVEMENT, "Seers Village Teleports"),
             new StatKey(FORTIS_TELEPORT, StatCategory.MOVEMENT, "Fortis Teleports"),
             new StatKey(HOUSE_TELEPORT, StatCategory.MOVEMENT, "House Teleports"),
             new StatKey(TOTAL_TELEPORTS, StatCategory.MOVEMENT, "Total Teleports"),
             new StatKey(DOORS_OPENED, StatCategory.MOVEMENT, "Doors & Gates Opened"),
             new StatKey(DOORS_CLOSED, StatCategory.MOVEMENT, "Doors & Gates Closed"),
             new StatKey(LADDERS_STAIRS_CLIMBED, StatCategory.MOVEMENT, "Ladders & Stairs Climbed"),

             // --- Fishing ---
             new StatKey(FISH_CAUGHT, StatCategory.FISHING, "Fish Caught"),
             new StatKey(SHRIMP_CAUGHT, StatCategory.FISHING, "Shrimp Caught"),
             new StatKey(ANCHOVIES_CAUGHT, StatCategory.FISHING, "Anchovies Caught"),
             new StatKey(SARDINE_CAUGHT, StatCategory.FISHING, "Sardines Caught"),
             new StatKey(HERRING_CAUGHT, StatCategory.FISHING, "Herring Caught"),
             new StatKey(TROUT_CAUGHT, StatCategory.FISHING, "Trout Caught"),
             new StatKey(SALMON_CAUGHT, StatCategory.FISHING, "Salmon Caught"),
             new StatKey(PIKE_CAUGHT, StatCategory.FISHING, "Pike Caught"),
             new StatKey(COD_CAUGHT, StatCategory.FISHING, "Cod Caught"),
             new StatKey(BASS_CAUGHT, StatCategory.FISHING, "Bass Caught"),
             new StatKey(TUNA_CAUGHT, StatCategory.FISHING, "Tuna Caught"),
             new StatKey(LOBSTER_CAUGHT, StatCategory.FISHING, "Lobsters Caught"),
             new StatKey(SWORDFISH_CAUGHT, StatCategory.FISHING, "Swordfish Caught"),
             new StatKey(MONKFISH_CAUGHT, StatCategory.FISHING, "Monkfish Caught"),
             new StatKey(SHARK_CAUGHT, StatCategory.FISHING, "Sharks Caught"),
             new StatKey(SEA_TURTLE_CAUGHT, StatCategory.FISHING, "Sea Turtles Caught"),
             new StatKey(MANTA_RAY_CAUGHT, StatCategory.FISHING, "Manta Rays Caught"),
             new StatKey(ANGLERFISH_CAUGHT, StatCategory.FISHING, "Anglerfish Caught"),
             new StatKey(KARAMBWAN_CAUGHT, StatCategory.FISHING, "Karambwans Caught"),
             new StatKey(DARK_CRAB_CAUGHT, StatCategory.FISHING, "Dark Crabs Caught"),
             new StatKey(MINNOW_CAUGHT, StatCategory.FISHING, "Minnows Caught"),
             new StatKey(SACRED_EEL_CAUGHT, StatCategory.FISHING, "Sacred Eels Caught"),
             new StatKey(LEAPING_TROUT_CAUGHT, StatCategory.FISHING, "Leaping Trout Caught"),
             new StatKey(LEAPING_SALMON_CAUGHT, StatCategory.FISHING, "Leaping Salmon Caught"),
             new StatKey(LEAPING_STURGEON_CAUGHT, StatCategory.FISHING, "Leaping Sturgeon Caught"),
             new StatKey(HARPOON_FISH_CAUGHT, StatCategory.FISHING, "Harpoonfish Caught"),
             new StatKey(SPIRIT_POOL_STABS, StatCategory.FISHING, "Spirit Pool Stabs"),
             new StatKey(RADAS_BLESSING_PROCS, StatCategory.FISHING, "Rada's Blessing Procs"),
             new StatKey(DRAGON_HARPOON_SPECS, StatCategory.FISHING, "Dragon Harpoon Specs"),

             // --- Sailing ---
             new StatKey(WIND_MOTES_RELEASED, StatCategory.SAILING, "Wind Motes Released"),
             new StatKey(SAILS_TRIMMED, StatCategory.SAILING, "Sails Trimmed"),
             new StatKey(TRAWLING_FISH_CAUGHT, StatCategory.SAILING, "Trawling Fish Caught"),
             new StatKey(HADDOCK_CAUGHT, StatCategory.SAILING, "Haddock Caught"),
             new StatKey(GIANT_KRILL_CAUGHT, StatCategory.SAILING, "Giant Krill Caught"),
             new StatKey(YELLOWFIN_CAUGHT, StatCategory.SAILING, "Yellowfin Caught"),
             new StatKey(HALIBUT_CAUGHT, StatCategory.SAILING, "Halibut Caught"),
             new StatKey(BLUEFIN_CAUGHT, StatCategory.SAILING, "Bluefin Caught"),
             new StatKey(MARLIN_CAUGHT, StatCategory.SAILING, "Marlin Caught"),
             new StatKey(CREW_WORK_WATCHED, StatCategory.SAILING, "Times Watched Crew Work"),
             new StatKey(SHOALS_BAITED, StatCategory.SAILING, "Shoals Baited"),
             new StatKey(BOATS_BOARDED, StatCategory.SAILING, "Boats Boarded"),
             new StatKey(SALVAGE_DREDGED, StatCategory.SAILING, "Salvage Dredged"),
             new StatKey(SALVAGE_SORTED, StatCategory.SAILING, "Salvage Sorted"),
             new StatKey(SMALL_SALVAGE_SORTED, StatCategory.SAILING, "Small Salvage Sorted"),
             new StatKey(FISHY_SALVAGE_SORTED, StatCategory.SAILING, "Fishy Salvage Sorted"),
             new StatKey(BARRACUDA_SALVAGE_SORTED, StatCategory.SAILING, "Barracuda Salvage Sorted"),
             new StatKey(LARGE_SALVAGE_SORTED, StatCategory.SAILING, "Large Salvage Sorted"),
             new StatKey(PLUNDERED_SALVAGE_SORTED, StatCategory.SAILING, "Plundered Salvage Sorted"),
             new StatKey(MARTIAL_SALVAGE_SORTED, StatCategory.SAILING, "Martial Salvage Sorted"),
             new StatKey(FREMENNIK_SALVAGE_SORTED, StatCategory.SAILING, "Fremennik Salvage Sorted"),
             new StatKey(OPULENT_SALVAGE_SORTED, StatCategory.SAILING, "Opulent Salvage Sorted"),

             // --- Mining ---
             new StatKey(ROCKS_MINED, StatCategory.MINING, "Rocks Mined"),
             new StatKey(CLAY_MINED, StatCategory.MINING, "Clay Mined"),
             new StatKey(COPPER_ORE_MINED, StatCategory.MINING, "Copper Ore Mined"),
             new StatKey(TIN_ORE_MINED, StatCategory.MINING, "Tin Ore Mined"),
             new StatKey(LIMESTONE_MINED, StatCategory.MINING, "Limestone Mined"),
             new StatKey(IRON_ORE_MINED, StatCategory.MINING, "Iron Ore Mined"),
             new StatKey(SILVER_ORE_MINED, StatCategory.MINING, "Silver Ore Mined"),
             new StatKey(COAL_MINED, StatCategory.MINING, "Coal Mined"),
             new StatKey(GOLD_ORE_MINED, StatCategory.MINING, "Gold Ore Mined"),
             new StatKey(MITHRIL_ORE_MINED, StatCategory.MINING, "Mithril Ore Mined"),
             new StatKey(ADAMANTITE_ORE_MINED, StatCategory.MINING, "Adamantite Ore Mined"),
             new StatKey(RUNITE_ORE_MINED, StatCategory.MINING, "Runite Ore Mined"),
             new StatKey(GRANITE_MINED, StatCategory.MINING, "Granite Mined"),
             new StatKey(SANDSTONE_MINED, StatCategory.MINING, "Sandstone Mined"),
             new StatKey(PAY_DIRT_MINED, StatCategory.MINING, "Pay-dirt Mined"),
             new StatKey(AMETHYST_MINED, StatCategory.MINING, "Amethyst Mined"),
             new StatKey(PURE_ESSENCE_MINED, StatCategory.MINING, "Pure Essence Mined"),

             // --- Woodcutting ---
             new StatKey(LOGS_CHOPPED, StatCategory.WOODCUTTING, "Logs Chopped"),
             new StatKey(NORMAL_LOGS_CHOPPED, StatCategory.WOODCUTTING, "Normal Logs Chopped"),
             new StatKey(OAK_LOGS_CHOPPED, StatCategory.WOODCUTTING, "Oak Logs Chopped"),
             new StatKey(WILLOW_LOGS_CHOPPED, StatCategory.WOODCUTTING, "Willow Logs Chopped"),
             new StatKey(TEAK_LOGS_CHOPPED, StatCategory.WOODCUTTING, "Teak Logs Chopped"),
             new StatKey(MAPLE_LOGS_CHOPPED, StatCategory.WOODCUTTING, "Maple Logs Chopped"),
             new StatKey(MAHOGANY_LOGS_CHOPPED, StatCategory.WOODCUTTING, "Mahogany Logs Chopped"),
             new StatKey(YEW_LOGS_CHOPPED, StatCategory.WOODCUTTING, "Yew Logs Chopped"),
             new StatKey(MAGIC_LOGS_CHOPPED, StatCategory.WOODCUTTING, "Magic Logs Chopped"),
             new StatKey(REDWOOD_LOGS_CHOPPED, StatCategory.WOODCUTTING, "Redwood Logs Chopped"),
             new StatKey(LETVEKS_SHOOED, StatCategory.WOODCUTTING, "Letveks Shooed"),
             new StatKey(BLOODWOOD_SAP_BUCKETS_FILLED, StatCategory.WOODCUTTING, "Bloodwood Sap Buckets Filled"),
             new StatKey(BIRD_NESTS_DROPPED, StatCategory.WOODCUTTING, "Bird Nests Received"),

             // --- Gathering ---
             new StatKey(FLAX_PICKED, StatCategory.GATHERING, "Flax Picked"),
             new StatKey(CABBAGE_PICKED, StatCategory.GATHERING, "Cabbage Picked"),

             // --- Farming ---
             new StatKey(WEEDS_RAKED, StatCategory.FARMING, "Weeds Raked"),
             new StatKey(SEEDS_PLANTED, StatCategory.FARMING, "Seeds Planted"),

             // --- Misc ---
             new StatKey(CRITTERS_PET, StatCategory.MISC, "Critters Pet"),
             new StatKey(THE_GUNS, StatCategory.MISC, "Highest 'The Guns' Count"),
             new StatKey(CLUE_SCROLLS_MISSED, StatCategory.MISC, "Clue Scrolls Missed"),
             new StatKey(BEGINNER_CLUE_SCROLLS_MISSED, StatCategory.MISC, "Beginner Clue Scrolls Missed"),
             new StatKey(EASY_CLUE_SCROLLS_MISSED, StatCategory.MISC, "Easy Clue Scrolls Missed"),
             new StatKey(MEDIUM_CLUE_SCROLLS_MISSED, StatCategory.MISC, "Medium Clue Scrolls Missed"),
             new StatKey(HARD_CLUE_SCROLLS_MISSED, StatCategory.MISC, "Hard Clue Scrolls Missed"),
             new StatKey(EXPERT_CLUE_SCROLLS_MISSED, StatCategory.MISC, "Expert Clue Scrolls Missed"),
             new StatKey(MASTER_CLUE_SCROLLS_MISSED, StatCategory.MISC, "Master Clue Scrolls Missed"),

             // --- Prayer ---
             new StatKey(BONES_BURIED, StatCategory.PRAYER, "Bones Buried"),
             new StatKey(ASHES_SCATTERED, StatCategory.PRAYER, "Ashes Scattered"),

             // --- Firemaking ---
             new StatKey(LOGS_BURNED, StatCategory.FIREMAKING, "Logs Burned"),

             // --- Food & Healing ---
             new StatKey(FOOD_EATEN, StatCategory.FOOD_AND_HEALING, "Food Eaten"),
             new StatKey(HP_REGEN, StatCategory.FOOD_AND_HEALING, "HP Regen"),
             new StatKey(BEER_DRANK, StatCategory.FOOD_AND_HEALING, "Beer Drunk"),
             new StatKey(CABBAGE_EATEN, StatCategory.FOOD_AND_HEALING, "Cabbage Eaten"),
             new StatKey(TROUT_EATEN, StatCategory.FOOD_AND_HEALING, "Trout Eaten"),
             new StatKey(SHRIMP_EATEN, StatCategory.FOOD_AND_HEALING, "Shrimp Eaten"),
             new StatKey(ANCHOVIES_EATEN, StatCategory.FOOD_AND_HEALING, "Anchovies Eaten"),
             new StatKey(SARDINE_EATEN, StatCategory.FOOD_AND_HEALING, "Sardines Eaten"),
             new StatKey(HERRING_EATEN, StatCategory.FOOD_AND_HEALING, "Herring Eaten"),
             new StatKey(PIKE_EATEN, StatCategory.FOOD_AND_HEALING, "Pike Eaten"),
             new StatKey(COD_EATEN, StatCategory.FOOD_AND_HEALING, "Cod Eaten"),
             new StatKey(SALMON_EATEN, StatCategory.FOOD_AND_HEALING, "Salmon Eaten"),
             new StatKey(TUNA_EATEN, StatCategory.FOOD_AND_HEALING, "Tuna Eaten"),
             new StatKey(BASS_EATEN, StatCategory.FOOD_AND_HEALING, "Bass Eaten"),
             new StatKey(LOBSTER_EATEN, StatCategory.FOOD_AND_HEALING, "Lobsters Eaten"),
             new StatKey(SWORDFISH_EATEN, StatCategory.FOOD_AND_HEALING, "Swordfish Eaten"),
             new StatKey(MONKFISH_EATEN, StatCategory.FOOD_AND_HEALING, "Monkfish Eaten"),
             new StatKey(SHARK_EATEN, StatCategory.FOOD_AND_HEALING, "Sharks Eaten"),
             new StatKey(MANTA_RAY_EATEN, StatCategory.FOOD_AND_HEALING, "Manta Rays Eaten"),
             new StatKey(ANGLERFISH_EATEN, StatCategory.FOOD_AND_HEALING, "Anglerfish Eaten"),
             new StatKey(KARAMBWAN_EATEN, StatCategory.FOOD_AND_HEALING, "Karambwans Eaten"),
             new StatKey(DARK_CRAB_EATEN, StatCategory.FOOD_AND_HEALING, "Dark Crabs Eaten"),
             new StatKey(SEA_TURTLE_EATEN, StatCategory.FOOD_AND_HEALING, "Sea Turtles Eaten"),
             new StatKey(HADDOCK_EATEN, StatCategory.FOOD_AND_HEALING, "Haddock Eaten"),
             new StatKey(GIANT_KRILL_EATEN, StatCategory.FOOD_AND_HEALING, "Giant Krill Eaten"),
             new StatKey(YELLOWFIN_EATEN, StatCategory.FOOD_AND_HEALING, "Yellowfin Eaten"),
             new StatKey(HALIBUT_EATEN, StatCategory.FOOD_AND_HEALING, "Halibut Eaten"),
             new StatKey(BLUEFIN_EATEN, StatCategory.FOOD_AND_HEALING, "Bluefin Eaten"),
             new StatKey(MARLIN_EATEN, StatCategory.FOOD_AND_HEALING, "Marlin Eaten"),
             new StatKey(DASHING_KEBBIT_EATEN, StatCategory.FOOD_AND_HEALING, "Dashing Kebbit Eaten"),
             new StatKey(SUNLIGHT_ANTELOPE_EATEN, StatCategory.FOOD_AND_HEALING, "Sunlight Antelope Eaten"),
             new StatKey(MOONLIGHT_ANTELOPE_EATEN, StatCategory.FOOD_AND_HEALING, "Moonlight Antelope Eaten"),
             new StatKey(PYRE_FOX_EATEN, StatCategory.FOOD_AND_HEALING, "Pyre Fox Eaten"),
             new StatKey(CAKE_SLICES_EATEN, StatCategory.FOOD_AND_HEALING, "Cake Slices Eaten"),
             new StatKey(FOOD_COOKED, StatCategory.COOKING, "Food Cooked"),
             new StatKey(FOOD_BURNED, StatCategory.COOKING, "Food Burned"),
             new StatKey(PINEAPPLE_RINGS_CUT, StatCategory.FOOD_AND_HEALING, "Pineapples Cut Into Rings"),
             new StatKey(POTION_SIPS_DRANK, StatCategory.FOOD_AND_HEALING, "Potion Sips"),
             new StatKey(DIVINE_DAMAGE, StatCategory.FOOD_AND_HEALING, "Divine Pot Damage"),

             new StatKey(SHRIMP_COOKED, StatCategory.COOKING, "Shrimp Cooked"),
             new StatKey(ANCHOVIES_COOKED, StatCategory.COOKING, "Anchovies Cooked"),
             new StatKey(TROUT_COOKED, StatCategory.COOKING, "Trout Cooked"),
             new StatKey(SALMON_COOKED, StatCategory.COOKING, "Salmon Cooked"),
             new StatKey(LOBSTER_COOKED, StatCategory.COOKING, "Lobsters Cooked"),
             new StatKey(SWORDFISH_COOKED, StatCategory.COOKING, "Swordfish Cooked"),
             new StatKey(SHARK_COOKED, StatCategory.COOKING, "Sharks Cooked"),
             new StatKey(MONKFISH_COOKED, StatCategory.COOKING, "Monkfish Cooked"),
             new StatKey(KARAMBWAN_COOKED, StatCategory.COOKING, "Karambwans Cooked"),
             new StatKey(ANGLERFISH_COOKED, StatCategory.COOKING, "Anglerfish Cooked"),
             new StatKey(HADDOCK_COOKED, StatCategory.COOKING, "Haddock Cooked"),
             new StatKey(GIANT_KRILL_COOKED, StatCategory.COOKING, "Giant Krill Cooked"),
             new StatKey(YELLOWFIN_COOKED, StatCategory.COOKING, "Yellowfin Cooked"),
             new StatKey(HALIBUT_COOKED, StatCategory.COOKING, "Halibut Cooked"),
             new StatKey(BLUEFIN_COOKED, StatCategory.COOKING, "Bluefin Cooked"),
             new StatKey(MARLIN_COOKED, StatCategory.COOKING, "Marlin Cooked"),
             new StatKey(SEA_TURTLE_COOKED, StatCategory.COOKING, "Sea Turtles Cooked"),
             new StatKey(DASHING_KEBBIT_COOKED, StatCategory.COOKING, "Dashing Kebbit Cooked"),
             new StatKey(SUNLIGHT_ANTELOPE_COOKED, StatCategory.COOKING, "Sunlight Antelope Cooked"),
             new StatKey(MOONLIGHT_ANTELOPE_COOKED, StatCategory.COOKING, "Moonlight Antelope Cooked"),
             new StatKey(PYRE_FOX_COOKED, StatCategory.COOKING, "Pyre Fox Cooked"),

             new StatKey(SHRIMP_BURNED, StatCategory.COOKING, "Shrimp Burned"),
             new StatKey(ANCHOVIES_BURNED, StatCategory.COOKING, "Anchovies Burned"),
             new StatKey(TROUT_BURNED, StatCategory.COOKING, "Trout Burned"),
             new StatKey(SALMON_BURNED, StatCategory.COOKING, "Salmon Burned"),
             new StatKey(LOBSTER_BURNED, StatCategory.COOKING, "Lobsters Burned"),
             new StatKey(SWORDFISH_BURNED, StatCategory.COOKING, "Swordfish Burned"),
             new StatKey(SHARK_BURNED, StatCategory.COOKING, "Sharks Burned"),
             new StatKey(MONKFISH_BURNED, StatCategory.COOKING, "Monkfish Burned"),
             new StatKey(KARAMBWAN_BURNED, StatCategory.COOKING, "Karambwans Burned"),
             new StatKey(ANGLERFISH_BURNED, StatCategory.COOKING, "Anglerfish Burned"),
             new StatKey(HADDOCK_BURNED, StatCategory.COOKING, "Haddock Burned"),
             new StatKey(GIANT_KRILL_BURNED, StatCategory.COOKING, "Giant Krill Burned"),
             new StatKey(YELLOWFIN_BURNED, StatCategory.COOKING, "Yellowfin Burned"),
             new StatKey(HALIBUT_BURNED, StatCategory.COOKING, "Halibut Burned"),
             new StatKey(BLUEFIN_BURNED, StatCategory.COOKING, "Bluefin Burned"),
             new StatKey(MARLIN_BURNED, StatCategory.COOKING, "Marlin Burned"),
             new StatKey(SEA_TURTLE_BURNED, StatCategory.COOKING, "Sea Turtles Burned"),
             new StatKey(DASHING_KEBBIT_BURNED, StatCategory.COOKING, "Dashing Kebbit Burned"),
             new StatKey(SUNLIGHT_ANTELOPE_BURNED, StatCategory.COOKING, "Sunlight Antelope Burned"),
             new StatKey(MOONLIGHT_ANTELOPE_BURNED, StatCategory.COOKING, "Moonlight Antelope Burned"),
             new StatKey(PYRE_FOX_BURNED, StatCategory.COOKING, "Pyre Fox Burned"),

             // --- Thieving ---
             new StatKey(PICK_POCKETS, StatCategory.THIEVING, "Pickpockets"),
             new StatKey(FAILED_PICK_POCKETS, StatCategory.THIEVING, "Failed Pickpockets"),
             new StatKey(STALLS_THIEVED, StatCategory.THIEVING, "Stalls Thieved"),

             // --- Agility ---
             new StatKey(ROOF_TOP_AGILITY, StatCategory.AGILITY, "Rooftop Laps"),
             new StatKey(NORMAL_AGILITY, StatCategory.AGILITY, "Agility Laps"),

             // --- Herblore ---
             new StatKey(UNFINISHED_POTIONS_MADE, StatCategory.HERBLORE, "Unfinished Potions"),
             new StatKey(POTIONS_MADE, StatCategory.HERBLORE, "Potions Made"),
             new StatKey(HERBS_CLEANED, StatCategory.HERBLORE, "Herbs Cleaned"),

             // --- Runecraft ---
             new StatKey(RUNES_CRAFTED, StatCategory.RUNECRAFT, "Runes Crafted"),

             // --- Crafting ---
             new StatKey(GEMS_CUT, StatCategory.CRAFTING, "Gems Cut"),
             new StatKey(GLASS_BLOWN, StatCategory.CRAFTING, "Glass Blown"),
             new StatKey(HIDES_TANNED, StatCategory.CRAFTING, "Hides Tanned"),
             new StatKey(GREEN_DHIDE_TANNED, StatCategory.CRAFTING, "Green D'hide Tanned"),
             new StatKey(BLUE_DHIDE_TANNED, StatCategory.CRAFTING, "Blue D'hide Tanned"),
             new StatKey(RED_DHIDE_TANNED, StatCategory.CRAFTING, "Red D'hide Tanned"),
             new StatKey(BLACK_DHIDE_TANNED, StatCategory.CRAFTING, "Black D'hide Tanned"),
             new StatKey(COWHIDE_TANNED, StatCategory.CRAFTING, "Cowhide Tanned"),

             // --- Fletching ---
             new StatKey(BOWS_FLECTHED, StatCategory.FLETCHING, "Bows Fletched"),
             new StatKey(BOWS_STRUNG, StatCategory.FLETCHING, "Bows Strung"),
             new StatKey(DARTS_FLECTHED, StatCategory.FLETCHING, "Darts Fletched"),

             // --- Hunter ---
             new StatKey(CREATURES_TRAPPED, StatCategory.HUNTER, "Creatures Trapped"),
             new StatKey(IMPLINGS_CAUGHT, StatCategory.HUNTER, "Implings Caught"),

             // --- Smithing ---
             new StatKey(BARS_SMELTED, StatCategory.SMITHING, "Bars Smelted"),
             new StatKey(ITEMS_SMITHED, StatCategory.SMITHING, "Items Smithed"),

             // --- Combat ---
             new StatKey(DAMAGE_DONE, StatCategory.COMBAT, "Damage Done"),
             new StatKey(DAMAGE_RECEIVED, StatCategory.COMBAT, "Damage Received"),
             new StatKey(BIGGEST_HITSPLAT, StatCategory.COMBAT, "Biggest Hit"),
             new StatKey(ATTACKS_BLOCKED, StatCategory.COMBAT, "Attacks Blocked"),
             new StatKey(ATTACKS_MISSED, StatCategory.COMBAT, "Attacks Missed"),
             new StatKey(DEATHS, StatCategory.COMBAT, "Deaths"),
             new StatKey(LESSER_GHOSTS_SUMMONED, StatCategory.COMBAT, "Lesser Ghosts Summoned"),
             new StatKey(LESSER_SKELETONS_SUMMONED, StatCategory.COMBAT, "Lesser Skeletons Summoned"),
             new StatKey(LESSER_ZOMBIES_SUMMONED, StatCategory.COMBAT, "Lesser Zombies Summoned"),
             new StatKey(SUPERIOR_GHOSTS_SUMMONED, StatCategory.COMBAT, "Superior Ghosts Summoned"),
             new StatKey(SUPERIOR_SKELETONS_SUMMONED, StatCategory.COMBAT, "Superior Skeletons Summoned"),
             new StatKey(SUPERIOR_ZOMBIES_SUMMONED, StatCategory.COMBAT, "Superior Zombies Summoned"),
             new StatKey(GREATER_GHOSTS_SUMMONED, StatCategory.COMBAT, "Greater Ghosts Summoned"),
             new StatKey(GREATER_SKELETONS_SUMMONED, StatCategory.COMBAT, "Greater Skeletons Summoned"),
             new StatKey(GREATER_ZOMBIES_SUMMONED, StatCategory.COMBAT, "Greater Zombies Summoned"),
     });

     public static String lampsUsedKey(Skill skill){
            return skill.name().toLowerCase() + "LampsUsed";
     }

     public static String lampXpKey(Skill skill){
            return skill.name().toLowerCase() + "LampXp";
     }

     /**
      * Appends the lamp stats for every skill to the hand written stat list above.
      */
     private static StatKey[] withLampKeys(StatKey[] base){
            List<StatKey> keys = new ArrayList<>(Arrays.asList(base));
            keys.add(new StatKey(LAMPS_USED, StatCategory.LAMPS, "Lamps Used"));
            keys.add(new StatKey(LAMP_XP, StatCategory.LAMPS, "XP Lamped"));
            for (Skill skill : Skill.values()){
                 //OVERALL is a pseudo skill in the enum, a lamp can never be used on it
                 if (skill == Skill.OVERALL){
                        continue;
                 }
                 keys.add(new StatKey(lampsUsedKey(skill), StatCategory.LAMPS, skill.getName() + " Lamps Used"));
                 keys.add(new StatKey(lampXpKey(skill), StatCategory.LAMPS, skill.getName() + " XP Lamped"));
            }
            return keys.toArray(new StatKey[0]);
     }

     public static final Map<String, StatCategory> KEY_TO_CATEGORY = Arrays.stream(ALL_KEYS)
             .collect(java.util.stream.Collectors.toMap(
                     StatKey::getValue,
                     StatKey::getCategory
             ));

     private static StatCategory getCategory(String key){
            for (StatKey statKey : ALL_KEYS){
                 if (statKey.getValue().equals(key)){
                        return statKey.getCategory();
                 }
            }
            return null;
     }


     /**
      * Stats that rely on game-tick (or shop widget) tracking and are not updated while performance mode is on.
      */
     public static final Set<String> PERFORMANCE_MODE_DISABLED_KEYS = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
             TILES_WALKED,
             TILES_RAN,
             SHOP_GP_SPENT,
             SHOP_GP_GAINED,
             LOGS_BURNED,
             WEEDS_RAKED,
             RUNES_CRAFTED,
             HP_REGEN,
             CRITTERS_PET,
             THE_GUNS,
             SPIRIT_TREE_TELES,
             FAIRY_RING_TELES
     )));
}