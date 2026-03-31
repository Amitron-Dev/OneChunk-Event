package net.amitron.event.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.inventory.ItemStack;

import net.amitron.event.GMain;
import net.amitron.event.GState;
import net.amitron.event.manager.FilesManager;
import net.amitron.event.runnables.GAutoStart;

public class PlayerHandler implements Listener {
	
	private GMain main;
	FilesManager files = new FilesManager(main);
	
	public PlayerHandler(GMain main) {
		this.main = main;
	}
	
	String launch_name = "§a§lLANCEMENT";
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		if(main.isState(GState.WAITING)) {
			p.setGameMode(GameMode.SURVIVAL);
			p.sendMessage("§aBienvenue !");
			p.sendMessage("§6LUMERYA Présente : OneChunk event");
			
			p.getInventory().clear();
			p.setFoodLevel(20);
			p.setHealth(20);

			if(p.hasPermission("admin.item")) {
				ItemStack launch = GMain.createItem(launch_name, 1, Material.EMERALD);
				p.getInventory().clear();
				p.getInventory().setItem(4, launch);
				p.setGameMode(GameMode.CREATIVE);
				return;
			}else {
				if(!p.hasPermission("admin.item")) {
					if(files.contains("locations", "lobby"))
					p.teleport(files.getLocation("location", "lobby"));
					main.getPlayers().add(p);
				}
			}
			
				
		}
		
		
		if(main.isState(GState.SURVIVAL)) {
			
			if(p.hasPermission("admin.login")) {
				p.setGameMode(GameMode.CREATIVE);
				return;
			}
			if(main.getPlayers().contains(p)) {
				p.sendMessage("§cRebonjour!");
				return;
			}
			p.sendMessage("§cLe jeu a déjà démarré.");
			p.setGameMode(GameMode.SPECTATOR);
			p.getInventory().clear();
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack it = e.getItem();
		
		if (it == null || it.getType() == Material.AIR) return;
		
		if(it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equals(launch_name)) {
			p.getInventory().clear(4);
			p.sendMessage("§aLancement...");
			main.setState(GState.START);
			
			GAutoStart GAutoStart = new GAutoStart(main);
			GAutoStart.runTaskTimer(main, 20, 20);
			
		}
		
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		if(main.isState(GState.SURVIVAL)) {
			Player p = e.getEntity();
			main.getEliminated().add(p.getUniqueId());
			p.kickPlayer("§4§lVous êtes éliminé\n§r§6Merci de votre particiption " + p.getName() + " !");
		}
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		Player p = e.getPlayer();
		if(main.getEliminated().contains(p.getUniqueId())) {
			if(p.hasPermission("admin.login")) return;
			e.disallow(Result.KICK_OTHER, "§4§lVous êtes éliminé\n§r§6Merci de votre particiption " + p.getName() + " !");
		}
	}

}
