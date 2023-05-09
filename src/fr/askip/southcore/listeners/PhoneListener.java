package fr.askip.southcore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PhoneListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getInventory();
        ItemStack item = event.getCurrentItem();

        if (inv.getName().equals("Phone") || inv.getName().equals("Appels urgences") || inv.getName().equals("Métiers illégaux") || inv.getName().equals("Métiers légaux")) {
            event.setCancelled(true);

            if (item != null && item.getType() == Material.SIGN) {
                String itemName = item.getItemMeta().getDisplayName();

                if (itemName.equals("Appels urgences")) {
                    Inventory emergencyCallsInv = Bukkit.createInventory(player, 9 * 3, "Appels urgences");

                    ItemStack callPoliceItem = new ItemStack(Material.SIGN);
                    ItemMeta callPoliceMeta = callPoliceItem.getItemMeta();
                    callPoliceMeta.setDisplayName("Appeler la police");
                    callPoliceItem.setItemMeta(callPoliceMeta);
                    emergencyCallsInv.setItem(0, callPoliceItem);

                    ItemStack callFirefightersItem = new ItemStack(Material.SIGN);
                    ItemMeta callFirefightersMeta = callFirefightersItem.getItemMeta();
                    callFirefightersMeta.setDisplayName("Appeler les pompiers");
                    callFirefightersItem.setItemMeta(callFirefightersMeta);
                    emergencyCallsInv.setItem(1, callFirefightersItem);

                    player.openInventory(emergencyCallsInv);
                } else if (itemName.equals("Ma banque")) {
                    player.performCommand("bal");
                } else if (itemName.equals("Métiers illégaux")) {
                    Inventory illegalJobsInv = Bukkit.createInventory(player, 9 * 3, "Métiers illégaux");
                    player.openInventory(illegalJobsInv);
                } else if (itemName.equals("Métiers légaux")) {
                    Inventory legalJobsInv = Bukkit.createInventory(player, 9 * 3, "Métiers légaux");
                    player.openInventory(legalJobsInv);
                } else if (itemName.equals("Quitter")) {
                    player.closeInventory();
                } else if (itemName.equals("Appeler la police")) {
                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        onlinePlayer.sendMessage(ChatColor.BLUE + player.getName() + " appelle la police à ses coordonnées: X=" + player.getLocation().getBlockX() + " Y=" + player.getLocation().getBlockY() + " Z=" + player.getLocation().getBlockZ());
                    }
                } else if (itemName.equals("Appeler les pompiers")) {
                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        onlinePlayer.sendMessage(ChatColor.GREEN + player.getName() + " appelle les pompiers à ses coordonnées: X=" + player.getLocation().getBlockX() + " Y=" + player.getLocation().getBlockY() + " Z=" + player.getLocation().getBlockZ());
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Material material = player.getInventory().getItemInMainHand().getType();

        if (material == Material.CLAY_BRICK) {
            Inventory phone = Bukkit.createInventory(player, 9 * 3, "Phone");

            ItemStack emergencyCallsItem = new ItemStack(Material.SIGN);
            ItemMeta emergencyCallsMeta = emergencyCallsItem.getItemMeta();
            emergencyCallsMeta.setDisplayName("Appels urgences");
            emergencyCallsItem.setItemMeta(emergencyCallsMeta);
            phone.setItem(0, emergencyCallsItem);

            ItemStack myBankItem = new ItemStack(Material.SIGN);
            ItemMeta myBankMeta = myBankItem.getItemMeta();
            myBankMeta.setDisplayName("Ma banque");
            myBankItem.setItemMeta(myBankMeta);
            phone.setItem(1, myBankItem);

            ItemStack illegalJobsItem = new ItemStack(Material.SIGN);
            ItemMeta illegalJobsMeta = illegalJobsItem.getItemMeta();
            illegalJobsMeta.setDisplayName("Métiers illégaux");
            illegalJobsItem.setItemMeta(illegalJobsMeta);
            phone.setItem(2, illegalJobsItem);

            ItemStack legalJobsItem = new ItemStack(Material.SIGN);
            ItemMeta legalJobsMeta = legalJobsItem.getItemMeta();
            legalJobsMeta.setDisplayName("Métiers légaux");
            legalJobsItem.setItemMeta(legalJobsMeta);
            phone.setItem(3, legalJobsItem);

            ItemStack quitItem = new ItemStack(Material.SIGN);
            ItemMeta quitMeta = quitItem.getItemMeta();
            quitMeta.setDisplayName("Quitter");
            quitItem.setItemMeta(quitMeta);
            phone.setItem(26, quitItem);

            player.openInventory(phone);
        }
    }

}
