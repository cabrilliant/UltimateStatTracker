package com.ultimatestattracker;

import org.lwjgl.system.linux.Stat;

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
          new StatKey(EXAMINE, StatCategory.ITEMS),
          new StatKey(ITEMS_DROPPED, StatCategory.ITEMS),
          new StatKey(VIALS_SMASHED, StatCategory.ITEMS),

          // --- Shops ---
          new StatKey(SHOP_GP_SPENT, StatCategory.SHOPS),
          new StatKey(SHOP_GP_GAINED, StatCategory.SHOPS),

          // --- Movement / Transporation ---
          new StatKey(TILES_WALKED, StatCategory.MOVEMENT),
          new StatKey(TILES_RAN, StatCategory.MOVEMENT),
             new StatKey(SPIRIT_TREE_TELES, StatCategory.MOVEMENT),
             new StatKey(FAIRY_RING_TELES,StatCategory.MOVEMENT),

          // --- Fishing ---
          new StatKey(FISH_CAUGHT, StatCategory.FISHING),
          new StatKey(SHRIMP_CAUGHT, StatCategory.FISHING),
          new StatKey(ANCHOVIES_CAUGHT, StatCategory.FISHING),
          new StatKey(SARDINE_CAUGHT, StatCategory.FISHING),
          new StatKey(HERRING_CAUGHT, StatCategory.FISHING),
          new StatKey(TROUT_CAUGHT, StatCategory.FISHING),
          new StatKey(SALMON_CAUGHT, StatCategory.FISHING),
          new StatKey(PIKE_CAUGHT, StatCategory.FISHING),
          new StatKey(COD_CAUGHT, StatCategory.FISHING),
          new StatKey(BASS_CAUGHT, StatCategory.FISHING),
          new StatKey(TUNA_CAUGHT, StatCategory.FISHING),
          new StatKey(LOBSTER_CAUGHT, StatCategory.FISHING),
          new StatKey(SWORDFISH_CAUGHT, StatCategory.FISHING),
          new StatKey(MONKFISH_CAUGHT, StatCategory.FISHING),
          new StatKey(SHARK_CAUGHT, StatCategory.FISHING),
          new StatKey(SEA_TURTLE_CAUGHT, StatCategory.FISHING),
          new StatKey(MANTA_RAY_CAUGHT, StatCategory.FISHING),
          new StatKey(ANGLERFISH_CAUGHT, StatCategory.FISHING),
          new StatKey(KARAMBWAN_CAUGHT, StatCategory.FISHING),
          new StatKey(DARK_CRAB_CAUGHT, StatCategory.FISHING),
          new StatKey(MINNOW_CAUGHT, StatCategory.FISHING),
          new StatKey(SACRED_EEL_CAUGHT, StatCategory.FISHING),
          new StatKey(LEAPING_TROUT_CAUGHT, StatCategory.FISHING),
          new StatKey(LEAPING_SALMON_CAUGHT, StatCategory.FISHING),
          new StatKey(LEAPING_STURGEON_CAUGHT, StatCategory.FISHING),
             new StatKey(HARPOON_FISH_CAUGHT, StatCategory.FISHING),
             new StatKey(SPIRIT_POOL_STABS, StatCategory.FISHING),

          // --- Mining ---
          new StatKey(ROCKS_MINED, StatCategory.MINING),
          new StatKey(CLAY_MINED, StatCategory.MINING),
          new StatKey(COPPER_ORE_MINED, StatCategory.MINING),
          new StatKey(TIN_ORE_MINED, StatCategory.MINING),
          new StatKey(LIMESTONE_MINED, StatCategory.MINING),
          new StatKey(IRON_ORE_MINED, StatCategory.MINING),
          new StatKey(SILVER_ORE_MINED, StatCategory.MINING),
          new StatKey(COAL_MINED, StatCategory.MINING),
          new StatKey(GOLD_ORE_MINED, StatCategory.MINING),
          new StatKey(MITHRIL_ORE_MINED, StatCategory.MINING),
          new StatKey(ADAMANTITE_ORE_MINED, StatCategory.MINING),
          new StatKey(RUNITE_ORE_MINED, StatCategory.MINING),
          new StatKey(GRANITE_MINED, StatCategory.MINING),
          new StatKey(SANDSTONE_MINED, StatCategory.MINING),
          new StatKey(PAY_DIRT_MINED, StatCategory.MINING),
          new StatKey(AMETHYST_MINED, StatCategory.MINING),
          new StatKey(PURE_ESSENCE_MINED, StatCategory.MINING),

          // --- Woodcutting ---
          new StatKey(LOGS_CHOPPED, StatCategory.WOODCUTTING),
          new StatKey(NORMAL_LOGS_CHOPPED, StatCategory.WOODCUTTING),
          new StatKey(OAK_LOGS_CHOPPED, StatCategory.WOODCUTTING),
          new StatKey(WILLOW_LOGS_CHOPPED, StatCategory.WOODCUTTING),
          new StatKey(TEAK_LOGS_CHOPPED, StatCategory.WOODCUTTING),
          new StatKey(MAPLE_LOGS_CHOPPED, StatCategory.WOODCUTTING),
          new StatKey(MAHOGANY_LOGS_CHOPPED, StatCategory.WOODCUTTING),
          new StatKey(YEW_LOGS_CHOPPED, StatCategory.WOODCUTTING),
          new StatKey(MAGIC_LOGS_CHOPPED, StatCategory.WOODCUTTING),
          new StatKey(REDWOOD_LOGS_CHOPPED, StatCategory.WOODCUTTING),

          // --- Gathering ---
          new StatKey(FLAX_PICKED, StatCategory.GATHERING),
          new StatKey(CABBAGE_PICKED, StatCategory.GATHERING),

          // --- Farming ---
          new StatKey(WEEDS_RAKED, StatCategory.FARMING),
          new StatKey(SEEDS_PLANTED, StatCategory.FARMING),

          // --- Misc ---
          new StatKey(CRITTERS_PET, StatCategory.MISC),
          new StatKey(THE_GUNS,StatCategory.MISC),

          // --- Prayer ---
          new StatKey(BONES_BURIED, StatCategory.PRAYER),
          new StatKey(ASHES_SCATTERED, StatCategory.PRAYER),

          // --- Firemaking ---
          new StatKey(LOGS_BURNED, StatCategory.FIREMAKING),

          // --- Food & Healing ---
          new StatKey(FOOD_EATEN, StatCategory.FOOD_AND_HEALING),
          new StatKey(HP_REGEN, StatCategory.FOOD_AND_HEALING),
          new StatKey(BEER_DRANK, StatCategory.FOOD_AND_HEALING),
          new StatKey(CABBAGE_EATEN, StatCategory.FOOD_AND_HEALING),
          new StatKey(TROUT_EATEN, StatCategory.FOOD_AND_HEALING),
          new StatKey(FOOD_COOKED, StatCategory.FOOD_AND_HEALING),
          new StatKey(FOOD_BURNED, StatCategory.FOOD_AND_HEALING),
          new StatKey(POTION_SIPS_DRANK, StatCategory.FOOD_AND_HEALING),
          new StatKey(DIVINE_DAMAGE, StatCategory.FOOD_AND_HEALING),
             new StatKey(SHRIMP_COOKED, StatCategory.FOOD_AND_HEALING),
             new StatKey(ANCHOVIES_COOKED, StatCategory.FOOD_AND_HEALING),
             new StatKey(TROUT_COOKED, StatCategory.FOOD_AND_HEALING),
             new StatKey(SALMON_COOKED, StatCategory.FOOD_AND_HEALING),
             new StatKey(LOBSTER_COOKED, StatCategory.FOOD_AND_HEALING),
             new StatKey(SWORDFISH_COOKED, StatCategory.FOOD_AND_HEALING),
             new StatKey(SHARK_COOKED, StatCategory.FOOD_AND_HEALING),
             new StatKey(MONKFISH_COOKED, StatCategory.FOOD_AND_HEALING),
             new StatKey(KARAMBWAN_COOKED, StatCategory.FOOD_AND_HEALING),
             new StatKey(ANGLERFISH_COOKED, StatCategory.FOOD_AND_HEALING),

          // --- Thieving ---
          new StatKey(PICK_POCKETS, StatCategory.THIEVING),
          new StatKey(FAILED_PICK_POCKETS, StatCategory.THIEVING),
          new StatKey(STALLS_THIEVED, StatCategory.THIEVING),

          // --- Agility ---
          new StatKey(ROOF_TOP_AGILITY, StatCategory.AGILITY),
          new StatKey(NORMAL_AGILITY, StatCategory.AGILITY),

          // --- Herblore ---
          new StatKey(UNFINISHED_POTIONS_MADE, StatCategory.HERBLORE),
          new StatKey(POTIONS_MADE, StatCategory.HERBLORE),
          new StatKey(HERBS_CLEANED, StatCategory.HERBLORE),

          // --- Runecraft ---
          new StatKey(RUNES_CRAFTED, StatCategory.RUNECRAFT),

          // --- Crafting ---
          new StatKey(GEMS_CUT, StatCategory.CRAFTING),
          new StatKey(GLASS_BLOWN, StatCategory.CRAFTING),
          new StatKey(HIDES_TANNED, StatCategory.CRAFTING),
          new StatKey(GREEN_DHIDE_TANNED, StatCategory.CRAFTING),
          new StatKey(BLUE_DHIDE_TANNED, StatCategory.CRAFTING),
          new StatKey(RED_DHIDE_TANNED, StatCategory.CRAFTING),
          new StatKey(BLACK_DHIDE_TANNED, StatCategory.CRAFTING),
          new StatKey(COWHIDE_TANNED, StatCategory.CRAFTING),

          // --- Fletching ---
          new StatKey(BOWS_FLECTHED, StatCategory.FLETCHING),
          new StatKey(BOWS_STRUNG, StatCategory.FLETCHING),
          new StatKey(DARTS_FLECTHED, StatCategory.FLETCHING),

          // --- Hunter ---
          new StatKey(CREATURES_TRAPPED, StatCategory.HUNTER),
          new StatKey(IMPLINGS_CAUGHT, StatCategory.HUNTER),

          // --- Smithing ---
          new StatKey(BARS_SMELTED, StatCategory.SMITHING),
          new StatKey(ITEMS_SMITHED, StatCategory.SMITHING),

          // --- Combat ---
          new StatKey(DAMAGE_DONE, StatCategory.COMBAT),
          new StatKey(DAMAGE_RECEIVED, StatCategory.COMBAT),
          new StatKey(BIGGEST_HITSPLAT, StatCategory.COMBAT),
          new StatKey(ATTACKS_BLOCKED, StatCategory.COMBAT),
          new StatKey(ATTACKS_MISSED, StatCategory.COMBAT),
          new StatKey(DEATHS,StatCategory.COMBAT)
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
             SPIRIT_TREE_TELES
     )));
}