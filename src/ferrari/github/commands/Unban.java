package ferrari.github.commands;

import ferrari.github.FBans;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Unban implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("ferrari.unban")) {

            if (args.length == 0) {
                sender.sendMessage(FBans.getPlugin(FBans.class).getConfig().getString("messages.unbanincorreto").replace("&", "§"));
                return false;

            }
            Bukkit.getBanList(BanList.Type.NAME).pardon(args[0]);
            sender.sendMessage(FBans.getPlugin(FBans.class).getConfig().getString("messages.unbaniu").replace("&", "§").replace("{player}", args[0]));






        }



        return false;
    }
}
