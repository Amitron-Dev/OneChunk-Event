package net.amitron.event.manager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class FilesManager {

    private final JavaPlugin plugin;
    private final Map<String, File> files = new HashMap<>();
    private final Map<String, FileConfiguration> configs = new HashMap<>();

    public FilesManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void create(String name) {
        File file = new File(plugin.getDataFolder(), name + ".yml");

        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        files.put(name, file);
        configs.put(name, YamlConfiguration.loadConfiguration(file));
    }

    public FileConfiguration getConfig(String name) {
        return configs.get(name);
    }

    public void save(String name) {
        try {
            configs.get(name).save(files.get(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload(String name) {
        configs.put(name, YamlConfiguration.loadConfiguration(files.get(name)));
    }

    public void delete(String name) {
        if (files.containsKey(name)) {
            files.get(name).delete();
            files.remove(name);
            configs.remove(name);
        }
    }

    public boolean exists(String name) {
        return files.containsKey(name);
    }

    public void set(String name, String path, Object value) {
        configs.get(name).set(path, value);
        save(name);
    }

    public Object get(String name, String path) {
        return configs.get(name).get(path);
    }

    public String getString(String name, String path) {
        return configs.get(name).getString(path);
    }

    public int getInt(String name, String path) {
        return configs.get(name).getInt(path);
    }

    public boolean getBoolean(String name, String path) {
        return configs.get(name).getBoolean(path);
    }

    public double getDouble(String name, String path) {
        return configs.get(name).getDouble(path);
    }

    public void remove(String name, String path) {
        configs.get(name).set(path, null);
        save(name);
    }

    public boolean contains(String name, String path) {
        return configs.get(name).contains(path);
    }

    public void setLocation(String name, String path, Location loc) {
        FileConfiguration config = configs.get(name);
        config.set(path + ".world", loc.getWorld().getName());
        config.set(path + ".x", loc.getX());
        config.set(path + ".y", loc.getY());
        config.set(path + ".z", loc.getZ());
        config.set(path + ".yaw", loc.getYaw());
        config.set(path + ".pitch", loc.getPitch());
        save(name);
    }

    public Location getLocation(String name, String path) {
        FileConfiguration config = configs.get(name);

        if (!config.contains(path + ".world")) return null;

        String world = config.getString(path + ".world");
        double x = config.getDouble(path + ".x");
        double y = config.getDouble(path + ".y");
        double z = config.getDouble(path + ".z");
        float yaw = (float) config.getDouble(path + ".yaw");
        float pitch = (float) config.getDouble(path + ".pitch");

        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }
}