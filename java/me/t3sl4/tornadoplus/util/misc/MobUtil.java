package me.t3sl4.tornadoplus.util.misc;

import org.bukkit.entity.EntityType;

public class MobUtil {
   private static MobUtil instance;
   // $FF: synthetic field
   private static int[] $SWITCH_TABLE$org$bukkit$entity$EntityType;

   private MobUtil() {
   }

   public boolean passive(EntityType entityType) {
      return VersionUtil.MINOR < 13 ? this.passive112(entityType) : this.passive113(entityType);
   }

   private boolean passive113(EntityType entityType) {
      switch($SWITCH_TABLE$org$bukkit$entity$EntityType()[entityType.ordinal()]) {
      case 41:
      case 46:
      case 47:
      case 48:
      case 49:
      case 51:
      case 53:
      case 55:
      case 56:
      case 57:
         return true;
      case 42:
      case 43:
      case 44:
      case 45:
      case 50:
      case 52:
      case 54:
      default:
         return false;
      }
   }

   private boolean passive112(EntityType entityType) {
      switch($SWITCH_TABLE$org$bukkit$entity$EntityType()[entityType.ordinal()]) {
      case 41:
      case 45:
      case 46:
      case 47:
      case 48:
      case 49:
      case 51:
      case 53:
      case 55:
      case 56:
      case 57:
         return true;
      case 42:
      case 43:
      case 44:
      case 50:
      case 52:
      case 54:
      default:
         return false;
      }
   }

   public static synchronized MobUtil getInstance() {
      if (instance == null) {
         instance = new MobUtil();
      }

      return instance;
   }

   // $FF: synthetic method
   static int[] $SWITCH_TABLE$org$bukkit$entity$EntityType() {
      int[] var10000 = $SWITCH_TABLE$org$bukkit$entity$EntityType;
      if (var10000 != null) {
         return var10000;
      } else {
         int[] var0 = new int[EntityType.values().length];

         try {
            var0[EntityType.ARMOR_STAND.ordinal()] = 17;
         } catch (NoSuchFieldError var66) {
         }

         try {
            var0[EntityType.ARROW.ordinal()] = 5;
         } catch (NoSuchFieldError var65) {
         }

         try {
            var0[EntityType.BAT.ordinal()] = 41;
         } catch (NoSuchFieldError var64) {
         }

         try {
            var0[EntityType.BLAZE.ordinal()] = 37;
         } catch (NoSuchFieldError var63) {
         }

         try {
            var0[EntityType.BOAT.ordinal()] = 19;
         } catch (NoSuchFieldError var62) {
         }

         try {
            var0[EntityType.CAVE_SPIDER.ordinal()] = 35;
         } catch (NoSuchFieldError var61) {
         }

         try {
            var0[EntityType.CHICKEN.ordinal()] = 48;
         } catch (NoSuchFieldError var60) {
         }

         try {
            var0[EntityType.COMPLEX_PART.ordinal()] = 65;
         } catch (NoSuchFieldError var59) {
         }

         try {
            var0[EntityType.COW.ordinal()] = 47;
         } catch (NoSuchFieldError var58) {
         }

         try {
            var0[EntityType.CREEPER.ordinal()] = 26;
         } catch (NoSuchFieldError var57) {
         }

         try {
            var0[EntityType.DROPPED_ITEM.ordinal()] = 1;
         } catch (NoSuchFieldError var56) {
         }

         try {
            var0[EntityType.EGG.ordinal()] = 60;
         } catch (NoSuchFieldError var55) {
         }

         try {
            var0[EntityType.ENDERMAN.ordinal()] = 34;
         } catch (NoSuchFieldError var54) {
         }

         try {
            var0[EntityType.ENDERMITE.ordinal()] = 43;
         } catch (NoSuchFieldError var53) {
         }

         try {
            var0[EntityType.ENDER_CRYSTAL.ordinal()] = 58;
         } catch (NoSuchFieldError var52) {
         }

         try {
            var0[EntityType.ENDER_DRAGON.ordinal()] = 39;
         } catch (NoSuchFieldError var51) {
         }

         try {
            var0[EntityType.ENDER_PEARL.ordinal()] = 9;
         } catch (NoSuchFieldError var50) {
         }

         try {
            var0[EntityType.ENDER_SIGNAL.ordinal()] = 10;
         } catch (NoSuchFieldError var49) {
         }

         try {
            var0[EntityType.EXPERIENCE_ORB.ordinal()] = 2;
         } catch (NoSuchFieldError var48) {
         }

         try {
            var0[EntityType.FALLING_BLOCK.ordinal()] = 15;
         } catch (NoSuchFieldError var47) {
         }

         try {
            var0[EntityType.FIREBALL.ordinal()] = 7;
         } catch (NoSuchFieldError var46) {
         }

         try {
            var0[EntityType.FIREWORK.ordinal()] = 16;
         } catch (NoSuchFieldError var45) {
         }

         try {
            var0[EntityType.FISHING_HOOK.ordinal()] = 61;
         } catch (NoSuchFieldError var44) {
         }

         try {
            var0[EntityType.GHAST.ordinal()] = 32;
         } catch (NoSuchFieldError var43) {
         }

         try {
            var0[EntityType.GIANT.ordinal()] = 29;
         } catch (NoSuchFieldError var42) {
         }

         try {
            var0[EntityType.GUARDIAN.ordinal()] = 44;
         } catch (NoSuchFieldError var41) {
         }

         try {
            var0[EntityType.HORSE.ordinal()] = 55;
         } catch (NoSuchFieldError var40) {
         }

         try {
            var0[EntityType.IRON_GOLEM.ordinal()] = 54;
         } catch (NoSuchFieldError var39) {
         }

         try {
            var0[EntityType.ITEM_FRAME.ordinal()] = 12;
         } catch (NoSuchFieldError var38) {
         }

         try {
            var0[EntityType.LEASH_HITCH.ordinal()] = 3;
         } catch (NoSuchFieldError var37) {
         }

         try {
            var0[EntityType.LIGHTNING.ordinal()] = 62;
         } catch (NoSuchFieldError var36) {
         }

         try {
            var0[EntityType.MAGMA_CUBE.ordinal()] = 38;
         } catch (NoSuchFieldError var35) {
         }

         try {
            var0[EntityType.MINECART.ordinal()] = 20;
         } catch (NoSuchFieldError var34) {
         }

         try {
            var0[EntityType.MINECART_CHEST.ordinal()] = 21;
         } catch (NoSuchFieldError var33) {
         }

         try {
            var0[EntityType.MINECART_COMMAND.ordinal()] = 18;
         } catch (NoSuchFieldError var32) {
         }

         try {
            var0[EntityType.MINECART_FURNACE.ordinal()] = 22;
         } catch (NoSuchFieldError var31) {
         }

         try {
            var0[EntityType.MINECART_HOPPER.ordinal()] = 24;
         } catch (NoSuchFieldError var30) {
         }

         try {
            var0[EntityType.MINECART_MOB_SPAWNER.ordinal()] = 25;
         } catch (NoSuchFieldError var29) {
         }

         try {
            var0[EntityType.MINECART_TNT.ordinal()] = 23;
         } catch (NoSuchFieldError var28) {
         }

         try {
            var0[EntityType.MUSHROOM_COW.ordinal()] = 51;
         } catch (NoSuchFieldError var27) {
         }

         try {
            var0[EntityType.OCELOT.ordinal()] = 53;
         } catch (NoSuchFieldError var26) {
         }

         try {
            var0[EntityType.PAINTING.ordinal()] = 4;
         } catch (NoSuchFieldError var25) {
         }

         try {
            var0[EntityType.PIG.ordinal()] = 45;
         } catch (NoSuchFieldError var24) {
         }

         try {
            var0[EntityType.PIG_ZOMBIE.ordinal()] = 33;
         } catch (NoSuchFieldError var23) {
         }

         try {
            var0[EntityType.PLAYER.ordinal()] = 64;
         } catch (NoSuchFieldError var22) {
         }

         try {
            var0[EntityType.PRIMED_TNT.ordinal()] = 14;
         } catch (NoSuchFieldError var21) {
         }

         try {
            var0[EntityType.RABBIT.ordinal()] = 56;
         } catch (NoSuchFieldError var20) {
         }

         try {
            var0[EntityType.SHEEP.ordinal()] = 46;
         } catch (NoSuchFieldError var19) {
         }

         try {
            var0[EntityType.SILVERFISH.ordinal()] = 36;
         } catch (NoSuchFieldError var18) {
         }

         try {
            var0[EntityType.SKELETON.ordinal()] = 27;
         } catch (NoSuchFieldError var17) {
         }

         try {
            var0[EntityType.SLIME.ordinal()] = 31;
         } catch (NoSuchFieldError var16) {
         }

         try {
            var0[EntityType.SMALL_FIREBALL.ordinal()] = 8;
         } catch (NoSuchFieldError var15) {
         }

         try {
            var0[EntityType.SNOWBALL.ordinal()] = 6;
         } catch (NoSuchFieldError var14) {
         }

         try {
            var0[EntityType.SNOWMAN.ordinal()] = 52;
         } catch (NoSuchFieldError var13) {
         }

         try {
            var0[EntityType.SPIDER.ordinal()] = 28;
         } catch (NoSuchFieldError var12) {
         }

         try {
            var0[EntityType.SPLASH_POTION.ordinal()] = 59;
         } catch (NoSuchFieldError var11) {
         }

         try {
            var0[EntityType.SQUID.ordinal()] = 49;
         } catch (NoSuchFieldError var10) {
         }

         try {
            var0[EntityType.THROWN_EXP_BOTTLE.ordinal()] = 11;
         } catch (NoSuchFieldError var9) {
         }

         try {
            var0[EntityType.UNKNOWN.ordinal()] = 66;
         } catch (NoSuchFieldError var8) {
         }

         try {
            var0[EntityType.VILLAGER.ordinal()] = 57;
         } catch (NoSuchFieldError var7) {
         }

         try {
            var0[EntityType.WEATHER.ordinal()] = 63;
         } catch (NoSuchFieldError var6) {
         }

         try {
            var0[EntityType.WITCH.ordinal()] = 42;
         } catch (NoSuchFieldError var5) {
         }

         try {
            var0[EntityType.WITHER.ordinal()] = 40;
         } catch (NoSuchFieldError var4) {
         }

         try {
            var0[EntityType.WITHER_SKULL.ordinal()] = 13;
         } catch (NoSuchFieldError var3) {
         }

         try {
            var0[EntityType.WOLF.ordinal()] = 50;
         } catch (NoSuchFieldError var2) {
         }

         try {
            var0[EntityType.ZOMBIE.ordinal()] = 30;
         } catch (NoSuchFieldError var1) {
         }

         $SWITCH_TABLE$org$bukkit$entity$EntityType = var0;
         return var0;
      }
   }
}
