package net.amitron.event;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.amitron.event.commands.event;
import net.amitron.event.listeners.PlayerHandler;
import net.amitron.event.listeners.PlayerSecure;
import net.amitron.event.listeners.guiHandler;
import net.amitron.event.manager.FilesManager;

public class GMain extends JavaPlugin{
	
	private GState state;
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<UUID> eliminated = new ArrayList<UUID>();
	public static String piege = "§aOnechunk";
	public static String prefix = "§6§l[EVENT] §r ";
	public static String location_files_name = "locations";
	public static boolean pvp = false;
	
	FilesManager files = new FilesManager(this);
	
	@Override
	public void onEnable() {
		
		setState(GState.WAITING);
		
		files.create(location_files_name);
		
		//COMMANDS
		getCommand("event").setExecutor(new event(this));
		
		//LISTERNERS
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerHandler(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerSecure(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new guiHandler(this), this);
	}
	
	public void setState(GState state) {
		this.state = state;
	}
	
	public boolean isState(GState state) {
		return this.state==state;
	}
	
	public static ItemStack createItem(String name, Integer nombre, Material material) {
		ItemStack it = new ItemStack(material, nombre);
		ItemMeta itm = it.getItemMeta();
		
		itm.setDisplayName(name);
		
		it.setItemMeta(itm);
		
		return it;
		
	}
	
	public List<Player> getPlayers(){
		return players;
	}
	
	public List<UUID> getEliminated(){
		return eliminated;
	}

}
