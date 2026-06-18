package com.ultimatestattracker;

import net.runelite.client.plugins.itemstats.stats.Stat;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StatKeys {
     // --- Items ---
     public static String EXAMINE = "itemsExamined";
     public static String ITEMS_DROPPED = "itemsDropped";
     public static String VIALS_SMASHED = "vialsSmashed";

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

     // --- Gathering ---
     public static String FLAX_PICKED = "flaxPicked";
     public static String CABBAGE_PICKED = "cabbagePicked";

     // -- Farming --
     public static String WEEDS_RAKED = "weedsRaked";
     public static String SEEDS_PLANTED = "seedsPlanted";

     // -- Misc --
     public static String CRITTERS_PET = "crittersPet";
     public static String THE_GUNS = "theGunsHiCount";

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
     public static String FOOD_COOKED = "foodCooked";
     public static String FOOD_BURNED = "foodBurned";
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


     //note, cant use reflection here as runelite does not allow it
     public static final StatKey[] ALL_KEYS = {
             // --- Items ---
             new StatKey(EXAMINE, StatCategory.ITEMS, "Things Examined"),
             new StatKey(ITEMS_DROPPED, StatCategory.ITEMS, "Items Dropped"),
             new StatKey(VIALS_SMASHED, StatCategory.ITEMS, "Vials Smashed"),

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

             // --- Gathering ---
             new StatKey(FLAX_PICKED, StatCategory.GATHERING, "Flax Picked"),
             new StatKey(CABBAGE_PICKED, StatCategory.GATHERING, "Cabbage Picked"),

             // --- Farming ---
             new StatKey(WEEDS_RAKED, StatCategory.FARMING, "Weeds Raked"),
             new StatKey(SEEDS_PLANTED, StatCategory.FARMING, "Seeds Planted"),

             // --- Misc ---
             new StatKey(CRITTERS_PET, StatCategory.MISC, "Critters Pet"),
             new StatKey(THE_GUNS, StatCategory.MISC, "Highest 'The Guns' Count"),

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
             new StatKey(FOOD_COOKED, StatCategory.FOOD_AND_HEALING, "Food Cooked"),
             new StatKey(FOOD_BURNED, StatCategory.FOOD_AND_HEALING, "Food Burned"),
             new StatKey(POTION_SIPS_DRANK, StatCategory.FOOD_AND_HEALING, "Potion Sips"),
             new StatKey(DIVINE_DAMAGE, StatCategory.FOOD_AND_HEALING, "Divine Pot Damage"),

             new StatKey(SHRIMP_COOKED, StatCategory.FOOD_AND_HEALING, "Shrimp Cooked"),
             new StatKey(ANCHOVIES_COOKED, StatCategory.FOOD_AND_HEALING, "Anchovies Cooked"),
             new StatKey(TROUT_COOKED, StatCategory.FOOD_AND_HEALING, "Trout Cooked"),
             new StatKey(SALMON_COOKED, StatCategory.FOOD_AND_HEALING, "Salmon Cooked"),
             new StatKey(LOBSTER_COOKED, StatCategory.FOOD_AND_HEALING, "Lobsters Cooked"),
             new StatKey(SWORDFISH_COOKED, StatCategory.FOOD_AND_HEALING, "Swordfish Cooked"),
             new StatKey(SHARK_COOKED, StatCategory.FOOD_AND_HEALING, "Sharks Cooked"),
             new StatKey(MONKFISH_COOKED, StatCategory.FOOD_AND_HEALING, "Monkfish Cooked"),
             new StatKey(KARAMBWAN_COOKED, StatCategory.FOOD_AND_HEALING, "Karambwans Cooked"),
             new StatKey(ANGLERFISH_COOKED, StatCategory.FOOD_AND_HEALING, "Anglerfish Cooked"),

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
             new StatKey(DEATHS, StatCategory.COMBAT, "Deaths")
     };

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