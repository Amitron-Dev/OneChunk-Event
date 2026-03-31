package net.amitron.event.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.amitron.event.GMain;
import net.amitron.event.manager.FilesManager;

public class event implements CommandExecutor {
	
	private GMain main;
	public static String stickname = "§aStick of knockback";
	public static String closename = "§cFermer";
	public static String pvponname = "§aPVP";
	public static String pvpoffname = "§cPVP";
	
	FilesManager files = new FilesManager(main);
	
	public event(GMain main) {
		this.main=main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player))  return true;
		
		Player p = (Player) sender;
		
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("gui")) {
				
				Inventory gui = Bukkit.createInventory(null, 27, GMain.piege);
				
				//    NAMES
				
				
				ItemStack stick = GMain.createItem(stickname, 1 , Material.STICK);
				
				if(GMain.pvp) {
					ItemStack pvpon = GMain.createItem(pvponname, 1, Material.DIAMOND_SWORD);
					gui.addItem(pvpon);
				}else {
					ItemStack pvpoff = GMain.createItem(pvpoffname, 1, Material.WOODEN_SWORD);
					gui.addItem(pvpoff);
				}
				
				
				ItemStack close = GMain.createItem(closename, 1, Material.BARRIER);
				
				gui.setItem(18, close);
				gui.addItem(stick);
				
				
				p.openInventory(gui);
				
			}
			
			if(args[0].equalsIgnoreCase("setlobby")) {
				if(p.hasPermission("admin.commands")) {
					p.sendMessage("§aPosition §rlobby §amodifiée");
					
					files.setLocation(GMain.location_files_name, "lobby", p.getLocation());
					files.save("locations");
				}else {
					p.sendMessage("§cVous n'avez pas la permission");
				}
			}
			
			if(args[0].equalsIgnoreCase("setspawn")) {
				if(p.hasPermission("admin.commands")) {
					p.sendMessage("§aPosition §rspawn §amodifiée");
					
					files.setLocation(GMain.location_files_name, "spawn", p.getLocation());
					files.save("locations");
					
				}else {
					p.sendMessage("§cVous n'avez pas la permission");
				}
			}
		}
		
		
		
		return false;
	}

}
