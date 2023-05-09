package fr.askip.southcore;

import fr.askip.southcore.commands.ClearLagCommand;
import fr.askip.southcore.commands.StaffCommand;
import fr.askip.southcore.listeners.ATMListener;
import fr.askip.southcore.listeners.PhoneListener;
import fr.askip.southcore.tasks.ClearItemsTask;
import org.bukkit.plugin.java.JavaPlugin;

public class SouthCore extends JavaPlugin {

    private ClearItemsTask clearItemsTask;

    @Override
    public void onEnable() {
        getLogger().info("Le plugin a bien start !");

        this.getCommand("clearlag").setExecutor(new ClearLagCommand());
        this.getCommand("staff").setExecutor(new StaffCommand(this));

        getServer().getPluginManager().registerEvents(new ATMListener(), this);
        getServer().getPluginManager().registerEvents(new PhoneListener(), this);

        clearItemsTask = new ClearItemsTask(this);
        clearItemsTask.start();
    }

    @Override
    public void onDisable() {
        getLogger().info("Le plugin s'est arrêté !");

        clearItemsTask.stop();
    }
}
