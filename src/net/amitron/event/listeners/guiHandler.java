package net.amitron.event.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.amitron.event.GMain;

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
			
			
		}
		
		
	}

}
