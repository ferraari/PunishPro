package ferrari.github.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;


public class Punir implements CommandExecutor, Listener {

    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("punir")) {
            p.sendMessage("§ePunições atuais disponiveis;");


            TextComponent banhack = new TextComponent("§fCliente Alternativo ");
            TextComponent bandiv = new TextComponent("Divulgação Imprópria ");
            TextComponent floodmute = new TextComponent("Silenciaento por Flood ");
            TextComponent spammute = new TextComponent("Silenciamento por Spam ");
            TextComponent banbug = new TextComponent("Abuso de Bug's ");
            TextComponent bantemp = new TextComponent("§fBanimento temporário ");
            TextComponent banofens = new TextComponent("§fOfensa a Staff ");
            TextComponent banofp = new TextComponent("§fOfensa ao Player ");
            TextComponent antjogo = new TextComponent("§fAnti-Jogo ");


            //Ban HACK
            banhack.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/banir usuario Auxilio Externo"));
            banhack.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§cTipo: Banimento \n §f/banir usuario motivo \n §f Cargo:§a Moderador")));

            //Ban DIV
            bandiv.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/banir usuario Divulgação"));
            bandiv.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§cTipo: Banimento \n §f/banir usuario motivo \n §f Cargo:§a Moderador")));

            //Band BUG
            banbug.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/banir usuario Abuso de Bug's"));
            banbug.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§cTipo: Banimento \n §f/banir usuario motivo \n §f Cargo:§a Moderador")));

            //Spam Mute
            spammute.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/mutartemp usuario 6h Spam"));
            spammute.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§cTipo: Silenciamento \n §f/mutartemp usuario tempo motivo \n §f Cargo:§a Ajudante")));

            //Flood Mute
            floodmute.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/mutartemp usuario 6h Flood"));
            floodmute.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§cTipo: Silenciamento \n §f/mutartemp usuario tempo motivo \n §f Cargo:§a Ajudante")));

            //AniJogo
            antjogo.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/banir usuario Anti-Jogo"));
            antjogo.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§cTipo: Banimento \n §f/banir usuario Motivo \n §f Cargo:§a Moderador")));
            //Ban Ofensa
            banofp.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/banir usuario Ofensa"));
            banofp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§cTipo: Banimento \n §f/banir usuario Motivo \n §f Cargo:§a Moderador")));
            // BanOfensa Staff
            banofens.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/banir usuario OfensaStaff"));
            banofens.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§cTipo: Banimento \n §f/banir usuario Motivo \n §f Cargo:§a Moderador")));



            p.spigot().sendMessage(banhack);
            p.spigot().sendMessage(bandiv);
            p.spigot().sendMessage(bantemp);
            p.spigot().sendMessage(banbug);
            p.spigot().sendMessage(spammute);
            p.spigot().sendMessage(floodmute);
            p.spigot().sendMessage(banofens);
            p.spigot().sendMessage(banofp);
            p.spigot().sendMessage(antjogo);


        }

        return false;
    }
}
