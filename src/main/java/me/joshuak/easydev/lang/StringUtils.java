package me.joshuak.easydev.lang;

import org.bukkit.ChatColor;

public class StringUtils {

    /**
     *
     * @param string The text to be parsed into color text (e.g. '&4')
     * @return
     */
    public static String asColor(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
