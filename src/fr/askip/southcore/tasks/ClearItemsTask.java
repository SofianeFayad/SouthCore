package fr.askip.southcore.tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.plugin.java.JavaPlugin;

public class ClearItemsTask implements Runnable {

    private JavaPlugin plugin;
    private int taskID;

    public ClearItemsTask(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void start() {
        // Schedule task to clear items every 30 minutes
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, 0, 30 * 60 * 20);
    }

    public void stop() {
        // Cancel task
        Bukkit.getScheduler().cancelTask(taskID);
    }

    @Override
    public void run() {
        // Send warning messages
        Bukkit.broadcastMessage(ChatColor.YELLOW + "Tous les items au sol seront supprimés dans 30 minutes.");
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatColor.YELLOW + "Tous les items au sol seront supprimés dans 15 minutes.");
            }
        }, 15 * 60 * 20);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatColor.YELLOW + "Tous les items au sol seront supprimés dans 5 minutes.");
            }
        }, 25 * 60 * 20);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatColor.YELLOW + "Tous les items au sol seront supprimés dans 3 minutes.");
            }
        }, 27 * 60 * 20);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatColor.YELLOW + "Tous les items au sol seront supprimés dans 1 minute.");
            }
        }, 29 * 60 * 20);

        // Remove all ground items
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                int count = 0;
                for (Entity entity : Bukkit.getWorlds().get(0).getEntities()) {
                    if (entity instanceof Item) {
                        entity.remove();
                        count++;
                    }
                }
                Bukkit.broadcastMessage(ChatColor.GREEN + "Tous les items au sol ont été supprimés. (" + count + " items)");
            }
        }, 30 * 60 * 20);
    }
}
