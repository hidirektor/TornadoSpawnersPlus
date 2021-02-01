package me.t3sl4.tornadoplus.command.arguements;

import me.t3sl4.tornadoplus.util.command.ErrorType;
import me.t3sl4.tornadoplus.util.command.IArgument;
import me.t3sl4.tornadoplus.util.misc.PluginUtil;
import me.t3sl4.tornadoplus.misc.VariableName;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand implements IArgument {
   private final IArgument argument;

   public HelpCommand(IArgument argument) {
      this.argument = argument;
   }

   public void process(CommandSender commandSender, String[] args) {
      PluginUtil.getInstance().sendMessage(VariableName.pullMessage("commands"), commandSender);
      Player phover = (Player) commandSender;
      TextComponent msg = new TextComponent("§e§lAuthor §7|| §e§lYapımcı");
      msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Eklenti Yapımcısı:\n   §8§l» §eSYN_T3SL4 \n   §8§l» §7Discord: §eHalil#4439").create()));
      phover.spigot().sendMessage(msg);
   }

   public IArgument isFinalArgument(String s) {
      return this.argument.isFinalArgument(s);
   }

   public ErrorType control(CommandSender commandSender, String[] strings) {
      return this.argument.control(commandSender, strings);
   }
}
