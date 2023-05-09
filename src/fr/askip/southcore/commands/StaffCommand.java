package fr.askip.southcore.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class StaffCommand implements CommandExecutor {

    private Plugin plugin;
    private Map<UUID, ItemStack[]> savedInventories = new HashMap<>();

    public StaffCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("southcore.staff")) {
                if (savedInventories.containsKey(player.getUniqueId())) {
                    // Restore player's inventory and visibility
                    player.getInventory().setContents(savedInventories.remove(player.getUniqueId()));
                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        if (!onlinePlayer.equals(player)) {
                            onlinePlayer.hidePlayer(plugin, player);
                        }
                    }
                    player.sendMessage(ChatColor.GREEN + "Vous êtes maintenant en mode staff et invisible aux autres joueurs.");
                    player.setCanPickupItems(true);
                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        onlinePlayer.showPlayer(plugin, player);
                    }
                    player.sendMessage(ChatColor.GREEN + "Vous n'êtes plus en mode staff.");
                } else {
                    // Save player's inventory and set staff items
                    savedInventories.put(player.getUniqueId(), player.getInventory().getContents());
                    player.getInventory().clear();
                    player.getInventory().setItem(0, new ItemStack(Material.BOOK));
                    player.getInventory().setItem(1, new ItemStack(Material.COMPASS));
                    player.setCanPickupItems(false);

                    // Hide player from other players
                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        if (!onlinePlayer.hasPermission("southcore.staff")) {
                            onlinePlayer.hidePlayer(plugin, player);
                        }
                    }
                    player.sendMessage(ChatColor.GREEN + "Vous êtes maintenant en mode staff.");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Vous n'avez pas la permission d'utiliser cette commande !");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Cette commande ne peut être utilisée que par un joueur !");
        }
        return true;
    }
}