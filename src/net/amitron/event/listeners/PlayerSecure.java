package net.amitron.event.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import net.amitron.event.GMain;
import net.amitron.event.GState;
import net.amitron.event.commands.event;

public class PlayerSecure implements Listener {
	
	private GMain main;
	
	public PlayerSecure(GMain main) {
		this.main = main;
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		
		if(p.hasPermission("admin.bypass")) return;
		
		if(!main.isState(GState.SURVIVAL)) {
			p.sendMessage("§2Vous ne pouvez pas faire cela maintenant");
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		
		if(p.hasPermission("admin.bypass")) return;
		
		if(!main.isState(GState.SURVIVAL)) {
			p.sendMessage("§2Vous ne pouvez pas faire cela maintenant");
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		
		Entity p = e.getEntity();
		if(!(p instanceof Player)) return;
		Player d = (Player) e.getDamageSource();

		if(d.getInventory().getItemInMainHand().hasItemMeta() && d.getInventory().getItemInMainHand().getItemMeta().hasDisplayName() && d.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(event.stickname)) {
			d.getItemInUse().setType(Material.AIR);
		}
		if(d.hasPermission("admin.bypass")) return;
		if(!main.isState(GState.SURVIVAL)) {
			d.sendMessage("§2Vous ne pouvez pas faire cela maintenant");
			e.setCancelled(true);
		}else if(main.isState(GState.SURVIVAL)) {
			if(!GMain.pvp) {
				d.sendMessage("§2Vous ne pouvez pas faire cela maintenant");
				
				e.setCancelled(true);
			}
		}
	}
	

}
