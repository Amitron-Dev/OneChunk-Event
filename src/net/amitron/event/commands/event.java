package net.amitron.event.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.amitron.event.GMain;

public class event implements CommandExecutor {
	
	private GMain main;
	
	public event(GMain main) {
		this.main=main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		
		
		return false;
	}

}
