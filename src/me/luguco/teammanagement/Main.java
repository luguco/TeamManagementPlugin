package me.luguco.teammanagement;

import me.luguco.teammanagement.mysql.MySQLConnection;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public FileConfiguration config = getConfig();

    @Override
    public void onEnable(){
        Bukkit.getServer().getConsoleSender().sendMessage("§1[§9TeamManagement§1] §aenabled");

        config.options().copyDefaults(true);
        saveConfig();
        MySQLConnection.connect(config.getString("mysql.host"), config.getInt("mysql.port"), config.getString("mysql.database"), config.getString("mysql.user"), config.getString("mysql.password"), "§1[§9VorbauEvent§1]");


        Team cTeam = new Team(this);
        getCommand("team").setExecutor(cTeam);
    }

    @Override
    public void onDisable(){
        Bukkit.getServer().getConsoleSender().sendMessage("§1[§9TeamManagement§1] §adisabled");
        MySQLConnection.disconnect();
    }
}
