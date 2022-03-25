package me.joshuak.easydev.playerheads;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.UUID;

public class HeadCreator {

    public static ItemStack getHeadFromUUID(UUID id) {
        ItemStack item = getPlayerHeadType();

        return itemWithId(item, id);
    }
    
    private static ItemStack itemWithId(ItemStack item, UUID id) {
        notNull(item, "item");
        notNull(id, "id");

        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
        Objects.requireNonNull(skullMeta).setOwningPlayer(Bukkit.getOfflinePlayer(id));
        item.setItemMeta(skullMeta);

        return item;
    }

    public static ItemStack nonExistentSkull(String displayname, String id) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) head.getItemMeta();

        meta.setDisplayName(displayname);

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", id));
        Field field;
        try {
            field = meta.getClass().getDeclaredField("profile");
            field.setAccessible(true);
            field.set(meta, profile);
        } catch(NoSuchFieldException | IllegalAccessException x ) {
            x.printStackTrace();
            return null;
        }

        head.setItemMeta(meta);

        return head;
    }

    public static ItemStack getTexturedItem(ItemStack itemStack, String texture) {
        ItemStack item = getPlayerHeadType();
        return getBase64Item(itemStack, texture);
    }

    private static ItemStack getBase64Item(ItemStack item, String base64) {
        notNull(item, "item");
        notNull(base64, "base64");

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", base64));
        Field field;
        try {
            field = item.getItemMeta().getClass().getDeclaredField("profile");
            field.setAccessible(true);
            field.set(item.getItemMeta(), profile);
        } catch(NoSuchFieldException | IllegalAccessException x ) {
            x.printStackTrace();
            return null;
        }
        return item;
    }

    public static boolean isNewAPI() {
        try {

            //If the game returns a material with value Player Head,
            // you are on a more recent version of Minecraft.
            Material.valueOf("PLAYER_HEAD");
            return true;

        } catch (IllegalArgumentException x) {
            // If the game cannot return PLAYER_HEAD, it
            // is an older version which still uses SKULL.
            return false;
        }
    }

    @SuppressWarnings("deprecation")
    public static ItemStack getPlayerHeadType() {
        if(isNewAPI()) {
            return new ItemStack(Material.valueOf("PLAYER_HEAD"));
        } else {
            // While SKULL_ITEM is deprecated in newer versions of Minecraft,
            // it is required for older ones.
            return new ItemStack(Material.valueOf("SKULL_ITEM"), 1, (byte) 3);
        }
    }

    private static void notNull(Object object, String name) {
        if (object == null) {
            throw new NullPointerException(name + " is null! Please check.");
        }
    }
}
