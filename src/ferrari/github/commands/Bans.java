package ferrari.github.commands;

import ferrari.github.FBans;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;


public class Bans implements CommandExecutor, Listener {



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


            if (sender.hasPermission("ferrari.bans")) {
                if (args.length == 0) {
                    sender.sendMessage(FBans.getPlugin(FBans.class).getConfig().getString("messages.incorreto").replace( "&", "§"));
                    return false;
                }
                String reason = args.length > 0 ? StringUtils.join(args, "", 1, args.length) : null;
                Bukkit.getBanList(BanList.Type.NAME).addBan(args[0], reason, null, sender.getName());

                Player player = Bukkit.getPlayer(args[0]);
                if (player != null) {
                    player.kickPlayer( FBans.getPlugin(FBans.class).getConfig().getString("messages.nomeservidor").replace("&", "§") +   "\n§cVocê foi banido por " + sender.getName() +   " \n Motivo: " + reason + "§e\n" + FBans.getPlugin(FBans.class).getConfig().getString("messages.banapelacao"));
                    sender.sendMessage(FBans.getPlugin(FBans.class).getConfig().getString("messages.baniu").replace("&", "§").replace("{player}", args[0]).replace("{motivo}", reason));
                } else if (player == null) {
                    sender.sendMessage("§cO jogador não está online!");

                }



            }




        return true;
    }

    @EventHandler
    public void onLogin(AsyncPlayerPreLoginEvent e) {
        if (Bukkit.getBanList(BanList.Type.NAME).isBanned(e.getName())) {
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, FBans.getPlugin(FBans.class).getConfig().getString("messages.nomeservidor").replace("&", "§") +   "\n§cVocê foi banido por: "  + Bukkit.getBanList(BanList.Type.NAME).getBanEntry(e.getName()).getReason()+"\n§cExpira em: "+ Bukkit.getBanList(BanList.Type.NAME).getBanEntry(e.getName()).getExpiration() + "§e\n" + FBans.getPlugin(FBans.class).getConfig().getString("messages.banapelacao").replace("&", "§"));

        }

    }

}