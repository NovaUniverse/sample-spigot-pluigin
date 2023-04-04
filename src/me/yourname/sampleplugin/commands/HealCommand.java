package me.yourname.sampleplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

/**
 * To make a custom command we create a class for the command then we implement
 * {@link CommandExecutor}. We register the command in the onEnabled function in
 * our main plugin class.
 * <p>
 * This command will be a heal command that when run by a player will fully heal
 * the player, the command will also be able to heal other players by using
 * /heal NAME_HERE
 */
public class HealCommand implements CommandExecutor {
	// This function is called when our custom command is executed by a player
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// Since we want this command to be both used by a player to heal them selves
		// and for players and the console to heal other players we need to check if any
		// arguments where specified
		if (args.length > 0) {
			// At least one argument was specified so lets try to get the player
			Player target = Bukkit.getPlayer(args[0]);

			// Bukkit.getPlayer returns null if the player is offline so we need to check
			// that the returned value is not null otherwise we tell the player that the
			// target is not online
			if (target != null) {
				// Normally the max health of a player is 20 but since that can be modifies we
				// need to get the max health attribute of the player like this
				double maxHealth = target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

				// Now we set the health
				target.setHealth(maxHealth);

				// And now we message both the player and the sender
				target.sendMessage(ChatColor.GREEN + "You have been healed");
				sender.sendMessage(ChatColor.GREEN + "Healed " + target.getName());
			} else {
				sender.sendMessage(ChatColor.RED + "Could not find an online player by the name " + args[0]);
			}
		} else {
			// No argument was specified
			// Since we cant heal the sender if this command was run by the console we first
			// need to check if that the sender is a player
			if (sender instanceof Player) {
				// we need to cast the sender into a player
				Player player = (Player) sender;

				// Same as above we have to fetch the max health
				double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

				// Now we set the health
				player.setHealth(maxHealth);

				// And message the player about it
				player.sendMessage(ChatColor.GREEN + "You have been healed");
			} else {
				// the command was run by the console or a command block and since we cant heal
				// those we send them a message about it
				sender.sendMessage(ChatColor.RED + "Since you are not a player you need to specify who to heal");
			}
		}

		return true;
	}
}