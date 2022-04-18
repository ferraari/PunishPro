package ferrari.github;

import ferrari.github.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;

public class FBans extends JavaPlugin {


    public void onEnable() {



        getCommand("banir").setExecutor(new Bans());
        getCommand("expulsar").setExecutor(new Kick());
        getCommand("desbanir").setExecutor(new Unban());
        getCommand("punir").setExecutor(new Punir());
        getCommand("tbanir").setExecutor(new TempBan());
        getServer().getPluginManager().registerEvents(new Bans(), this);
        Bukkit.getConsoleSender().sendMessage("§a[FBans] §fPlugin §aFBans §fby §aFerrari §floaded!");


        File file = new File(getDataFolder() + File.separator + "config.yml");


        if (!file.exists()){

            getConfig().addDefault("Name", "Value");


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

        if(getConfig().get("Name") == null){ //if the setting has been deleted it will be null
            getConfig().set("Name", "Value"); //reset the setting
            saveConfig();
            reloadConfig();

        }



    }
}
