package ferrari.github.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import javax.xml.soap.Text;

public class Punir implements CommandExecutor, Listener {

    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("punir")) {
            p.sendMessage("§ePunições atuais disponiveis;");


            TextComponent banhack = new TextComponent("§e§l    °  §eBanimento de Conta ");
            TextComponent  bandiv= new TextComponent("§e§l    °  §eBanimento de Conta ");


            //Ban HACK
            banhack.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/banir usuario Uso de Auxilio Externo"));
            banhack.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§eBanimento \n §f/banir usuario motivo \n §f Cargo:§a Moderador")));

            //Ban DIV
            bandiv.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/banir usuario Divulgação"));
            bandiv.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§eBanimento \n §f/banir usuario motivo \n §f Cargo:§a Moderador")));

            p.spigot().sendMessage(banhack);
        }

        return false;
    }
}
