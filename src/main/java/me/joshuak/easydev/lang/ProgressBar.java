package me.joshuak.easydev.lang;

import org.bukkit.ChatColor;

public class ProgressBar {

    public static String createProgressBar(float bars, float progress, float needed) {

        StringBuilder greenBar = new StringBuilder();
        StringBuilder redBar = new StringBuilder();

        float percentage = progress * 100 / needed;
        float barPercentage = 100 / bars;

        while (percentage > barPercentage) {
            percentage -= barPercentage;
            greenBar.append("|");
            bars -= 1;
        }

        while (bars > 0) {
            redBar.append("|");
            bars -= 1;
        }

        return ChatColor.GREEN + greenBar.toString() + ChatColor.RED + redBar;
    }
}
