package fr.askip.southcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;

public class ClearLagCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("southcore.clearlag")) {
            int count = 0;
            for (Entity entity : Bukkit.getWorlds().get(0).getEntities()) {
                if (entity instanceof Item) {
                    entity.remove();
                    count++;
                }
            }
            Bukkit.broadcastMessage(ChatColor.GREEN + "Tous les items au sol ont été supprimés par " + sender.getName() + ". (" + count + " items)");
        } else {
            sender.sendMessage(ChatColor.RED + "Vous n'avez pas la permission d'utiliser cette commande!");
        }
        return true;
    }
}
