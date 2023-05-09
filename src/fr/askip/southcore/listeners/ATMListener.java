package fr.askip.southcore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ATMListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getInventory();
        ItemStack item = event.getCurrentItem();

        if (inv.getName().equals("ATM")) {
            if (item != null && item.getType() == Material.CONCRETE) {
                if (item.getDurability() == 5) { // green concrete
                    event.setCancelled(true);
                    Inventory depositInv = Bukkit.createInventory(null, 9, "Déposer");
                    player.openInventory(depositInv);
                } else if (item.getDurability() == 14) { // red concrete
                    event.setCancelled(true);
                    Inventory withdrawInv = Bukkit.createInventory(null, 9, "Retirer");
                    player.openInventory(withdrawInv);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        Block block = event.getClickedBlock();

        if (action == Action.RIGHT_CLICK_BLOCK && block.getType() == Material.GOLD_BLOCK) {
            Inventory inv = Bukkit.createInventory(null, 9, "ATM");

            ItemStack depositItem = new ItemStack(Material.CONCRETE, 1, (short) 5); // green concrete
            ItemMeta depositMeta = depositItem.getItemMeta();
            depositMeta.setDisplayName("Déposer");
            depositItem.setItemMeta(depositMeta);
            inv.setItem(0, depositItem);

            ItemStack withdrawItem = new ItemStack(Material.CONCRETE, 1, (short) 14); // red concrete
            ItemMeta withdrawMeta = withdrawItem.getItemMeta();
            withdrawMeta.setDisplayName("Retirer");
            withdrawItem.setItemMeta(withdrawMeta);
            inv.setItem(8, withdrawItem);

            player.openInventory(inv);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        if (block.getType() == Material.GOLD_BLOCK && (!player.isOp() || !player.isSneaking())) {
            event.setCancelled(true);
            player.sendMessage("Vous ne pouvez pas casser ce bloc d'or !");
        }
    }
}
