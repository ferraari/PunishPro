package ferrari.github;

import ferrari.github.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class FBans extends JavaPlugin {



    public static ConcurrentHashMap<String, String> mutado = new ConcurrentHashMap<>();

    public void onEnable() {



        getCommand("banir").setExecutor(new Bans());
        getCommand("expulsar").setExecutor(new Kick());
        getCommand("desbanir").setExecutor(new Unban());
        getCommand("punir").setExecutor(new Punir());
        getCommand("tbanir").setExecutor(new TempBan());
        getCommand("mutar").setExecutor(new Mute());
        getCommand("desmutar").setExecutor(new UnMute());
        getServer().getPluginManager().registerEvents(new UnMute(), this);
        getServer().getPluginManager().registerEvents(new Mute(), this);
        getServer().getPluginManager().registerEvents(new Bans(), this);


        Bukkit.getConsoleSender().sendMessage("§a[FBans] §fPlugin §aFBans §fby §aFerrari §floaded!");

        File file = new File(getDataFolder() + File.separator + "config.yml");


        if (!file.exists()){
            getConfig().addDefault("Version", "1.0.0");


            getConfig().options().copyDefaults(true);
            saveConfig();
        } else {

            CheckConfig();
            saveConfig();
            reloadConfig();

        }



    }



    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§a[FBans] §fPlugin §aFBans §fby §aFerrari §fdisabled!");
    }

    public void CheckConfig() {

        if(getConfig().get("Version") == null){
            getConfig().set("Version", "1.0.0");
            saveConfig();
            reloadConfig();

        }




    }
}
