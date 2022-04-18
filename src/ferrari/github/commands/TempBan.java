package ferrari.github.commands;

import ferrari.github.FBans;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Calendar;
import java.util.Date;

public class TempBan implements CommandExecutor {

    public void BanEntry(Player name, Date date , String time, String reason , CommandSender sender, Command cmd) {
        if (name == null) {
            sender.sendMessage("§cJogador não encontrado!");
            sender.sendMessage("§cUse §e/tempban <jogador> <tempo> <motivo>");
            return;
        }

        if (StringUtils.isNumeric(time)) {
            int timeInt = Integer.parseInt(time);
            if (timeInt < 1) {
                sender.sendMessage("§cO tempo deve ser maior que 0");
                return;
            }
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE, timeInt);
            Date date2 = cal.getTime();
            Bukkit.getBanList(BanList.Type.NAME).addBan(name.getName(), reason, date2, sender.getName());
            sender.sendMessage("§aJogador " + name.getName() + " foi banido por " + time + " minutos");
            name.kickPlayer(FBans.getPlugin(FBans.class).getConfig().getString("messages.nomeservidor").replace("&", "§") +"\n§cVocê foi banido por " + time + " minutos\n§cMotivo: " + reason);
        } else {
            sender.sendMessage("§co Tempo precisa ser um numero");
        }
    }
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("tempban")) {
            if (sender.hasPermission("ferrari.tempban")) {
                if (args.length == 3) {
                    Player name = Bukkit.getPlayer(args[0]);
                    String time = args[1];
                    String reason = args[2];
                    BanEntry(name, new Date(), time, reason, sender, cmd);
                }
            }
        }
        return false;
    }













}
