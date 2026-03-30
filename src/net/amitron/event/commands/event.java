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

public class event implements CommandExecutor {
	
	private GMain main;
	
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
				
				ItemStack stick = GMain.createItem("§aStick of knockback", 1 , Material.STICK);
				ItemStack close = GMain.createItem("§cFermer", 1, Material.BARRIER);
				
				gui.setItem(18, close);
				gui.addItem(stick);
				
				
				p.openInventory(gui);
				
			}
		}
		
		
		return false;
	}

}
