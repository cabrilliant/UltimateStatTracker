package com.ultimatestattracker;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;

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

     // --- Gathering ---
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
     public static String FLAX_PICKED = "flaxPicked";
     public static String CABBAGE_PICKED = "cabbagePicked";

     // -- Farming --
     public static String WEEDS_RAKED = "weedsRaked";
     public static String SEEDS_PLANTED = "seedsPlanted";

     // -- Misc --
     public static String CRITTERS_PET = "crittersPet";

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

     public static final String[] ALL_KEYS = discoverStatKeys();

     private static String[] discoverStatKeys()
     {
         return Arrays.stream(StatKeys.class.getDeclaredFields())
                 .filter(f -> Modifier.isPublic(f.getModifiers()) && Modifier.isStatic(f.getModifiers()))
                 .filter(f -> f.getType() == String.class)
                 .sorted(Comparator.comparing(Field::getName))
                 .map(StatKeys::readStaticStringField)
                 .toArray(String[]::new);
     }

     private static String readStaticStringField(Field f)
     {
         try
         {
             return (String) f.get(null);
         }
         catch (IllegalAccessException e)
         {
             throw new ExceptionInInitializerError(e);
         }
     }
}