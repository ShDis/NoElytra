package com.gmail.theshdis.noelytra;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.block.Action;

public class NoElytra extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this,this);
    }
    @Override
    public void onDisable(){

    }

    @EventHandler
    public void useItemInHand(PlayerInteractEvent e)
    {
        if (e.getAction() != Action.RIGHT_CLICK_AIR)
            return;
        if (e.getItem() == null)
            return;
        if (!e.getPlayer().isGliding())
            return;

        ItemStack item = e.getItem();
        if (item.getType() != Material.FIREWORK_ROCKET) {
            return;
        }

        ItemStack chest = e.getPlayer().getInventory().getChestplate();
        if (chest.getType() != Material.ELYTRA) {
            return;
        }

        chest.setDurability((short) 432);
        e.getPlayer().getInventory().setChestplate(chest);
        Bukkit.getConsoleSender().sendMessage("[No Elytra] "+ e.getPlayer().getDisplayName() + " used firework while gliding. Removed all elytra's durability.");
    }
}


