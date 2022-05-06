package ferrari.github.commands;

import ferrari.github.FBans;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kick implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("ferrari.kick")) {


            if (sender.hasPermission("ferrari.kick")) {


                if (sender.hasPermission("ferrari.kick")) {
                    if (args.length == 0) {
                        sender.sendMessage(FBans.getPlugin(FBans.class).getConfig().getString("messages.kickincorreto").replace( "&", "§"));
                        return false;
                    }
                    String reason = args.length > 0 ? StringUtils.join(args, "", 1, args.length) : null;


                    Player player = Bukkit.getPlayer(args[0]);
                    if (player != null) {
                        player.kickPlayer( FBans.getPlugin(FBans.class).getConfig().getString("messages.nomeservidor").replace("&", "§") +   "\n§cVocê foi kickado por " + sender.getName() +   " \n Motivo: " + reason + "§e\n" + FBans.getPlugin(FBans.class).getConfig().getString("messages.kickapelacao").replace("&", "§"));
                        sender.sendMessage(FBans.getPlugin(FBans.class).getConfig().getString("messages.kickou").replace("&",  "§").replace("{player}", args[0]).replace("{motivo}", reason));

                    } else {
                        sender.sendMessage("§cJogador não encontrado");
                    }



                }

            }


        }
        return false;}
}
