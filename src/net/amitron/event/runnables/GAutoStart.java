package net.amitron.event.runnables;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.amitron.event.GMain;
import net.amitron.event.GState;
import net.amitron.event.manager.FilesManager;

public class GAutoStart extends BukkitRunnable {
	
	private int timer = 20;
	private GMain main;
	FilesManager files;
	
	public GAutoStart(GMain main) {
		this.main = main;
		this.files = main.getFilesManager();
	}
	
	
	
	@Override
	public void run() {
		
		if(timer!= 0) {
			for(Player p : Bukkit.getOnlinePlayers()) {
				p.sendMessage(GMain.prefix + "§aL'event démarre dans " + timer + " secondes");
			}
		}
		
		if(timer == 0) {
			main.setState(GState.SURVIVAL);
			
			Bukkit.broadcastMessage(GMain.prefix + "§aLa partie commence !");
			for(Player p : main.getPlayers()) {
				p.sendMessage("§aBon courage !");
				p.getInventory().addItem(GMain.createItem("§dLe steak de la force", 10, Material.COOKED_BEEF));
				if(files.contains("locations", "spawn")) {
					p.teleport(files.getLocation("locations", "spawn"));
				}
				
			}
			cancel();
		}
		
		
		timer--;
		
	}

}
