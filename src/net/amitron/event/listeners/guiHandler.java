package net.amitron.event.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.amitron.event.GMain;
import net.amitron.event.commands.event;

public class guiHandler implements Listener {
	
	private GMain main;
	
	public guiHandler(GMain main) {
		this.main = main;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		
		if(!(e.getWhoClicked() instanceof Player)) return;
		
		Inventory inv = e.getInventory();
		Player p = (Player) e.getWhoClicked();
		ItemStack it = e.getCurrentItem();
		
		if(e.getView().getTitle().equals(GMain.piege)) {
			
			if(it == null) return;
			
			if(it.hasItemMeta()&& it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equals(event.closename)) {
				p.closeInventory();
			}
			
			if(it.hasItemMeta()&& it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equals(event.stickname)) {
				p.closeInventory();
				
				ItemStack stick = new ItemStack(Material.STICK, 1);
				ItemMeta stickm = stick.getItemMeta();
				
				stickm.setDisplayName("§4§lSTICK");
				stickm.addEnchant(Enchantment.KNOCKBACK, 255, true);
				
				stick.setItemMeta(stickm);
				
				p.getInventory().addItem(stick);
				
			}
			
			if(it.hasItemMeta()&& it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equals(event.pvpoffname)) {
				p.closeInventory();
				GMain.pvp = true;
				Bukkit.broadcastMessage(GMain.prefix + "§aLe pvp est maintenant activé !");
			}
			if(it.hasItemMeta()&& it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equals(event.pvponname)) {
				p.closeInventory();
				GMain.pvp = false;
				Bukkit.broadcastMessage(GMain.prefix + "§cLe pvp est maintenant désactivé !");
			}
			
		}
		
		
	}

}
