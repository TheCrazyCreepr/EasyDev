package me.joshuak.easydev.itemstack;

import me.joshuak.easydev.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ItemMaker {

    /**
     *
     * @param material The material to build your item with
     * @param amount The amount of the item
     * @return
     */
    public static final ItemStack createItemStack(Material material, int amount) {
        return new ItemStack(material, amount);
    }

    /**
     *
     * @param material The material to build your item with
     * @param amount The amount of the item
     * @param displayName The display name of the item
     * @return
     */
    public static final ItemStack createItemStack(Material material, int amount, String displayName) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(StringUtils.asColor(displayName));
        item.setItemMeta(meta);

        return item;
    }

    /**
     *
     * @param material The material to build your item with
     * @param amount The amount of the item
     * @param displayName The display name of the item
     * @param unbreakable Whether the item is unbreakable or not.
     * @param lore The lore, automatically colored via '&' color codes.
     * @return
     */

    public static final ItemStack createItemStack(Material material, int amount, String displayName, boolean unbreakable, String... lore) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(displayName);

        // Sets lore and automatically changes color codes.
        meta.setLore(Arrays.stream(lore).map(StringUtils::asColor).collect(Collectors.toList()));

        meta.setUnbreakable(unbreakable);

        item.setItemMeta(meta);

        return item;
    }
}
