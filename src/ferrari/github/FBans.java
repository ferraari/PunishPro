package ferrari.github;

import ferrari.github.commands.Bans;
import ferrari.github.commands.Kick;
import ferrari.github.commands.Punir;
import ferrari.github.commands.Unban;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;

public class FBans extends JavaPlugin {


    public void onEnable() {

        getCommand("banir").setExecutor(new Bans());
        getCommand("expulsar").setExecutor(new Kick());
        getCommand("desbanir").setExecutor(new Unban());
        getCommand("punir").setExecutor(new Punir());
        getServer().getPluginManager().registerEvents(new Bans(), this);
        Bukkit.getConsoleSender().sendMessage("§a[FBans] §fPlugin §aFBans §fby §aFerrari §floaded!");


        File file = new File(getDataFolder() + File.separator + "config.yml"); //This will get the config file


        if (!file.exists()){ //This will check if the file exist
            //Situation A, File doesn't exist

            getConfig().addDefault("Name", "Value"); //adding default settings

            //Save the default settings
            getConfig().options().copyDefaults(true);
            saveConfig();
        } else {
            //situation B, Config does exist
            CheckConfig(); //function to check the important settings
            saveConfig(); //saves the config
            reloadConfig();    //reloads the config

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
