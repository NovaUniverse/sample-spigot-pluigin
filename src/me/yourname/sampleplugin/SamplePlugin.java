package me.yourname.sampleplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.yourname.sampleplugin.commands.HealCommand;
import me.yourname.sampleplugin.events.NoExplosionDamage;
import net.md_5.bungee.api.ChatColor;

/**
 * This is the main class of out plugin.
 * <p>
 * We need to extend {@link JavaPlugin} to make spigot able to detect this as a
 * plugin. We will also need to implement {@link Listener} so that we can
 * register our custom events in this class
 */
public class SamplePlugin extends JavaPlugin implements Listener {
	// This variable stores the welcome message our plugin displays to players when
	// they join
	private String welcomeMessage;

	@Override
	public void onEnable() {
		// This function is called when our plugin starts

		// We call this function to set up the config.yml file in our plugins data
		// folder. if the config file already exists this wont do anything
		saveDefaultConfig();

		// Now that the config is set up we load our custom welcome message from the
		// config like this
		welcomeMessage = getConfig().getString("WelcomeMessage");

		// To make our events work we need to register our listeners by calling the
		// following function
		Bukkit.getServer().getPluginManager().registerEvents(this, this);

		// If we have custom classes that has our event handlers in them we can register
		// them like this
		Bukkit.getServer().getPluginManager().registerEvents(new NoExplosionDamage(), this);

		// Now we register our commands
		getCommand("heal").setExecutor(new HealCommand());
	}

	@Override
	public void onDisable() {
		// This function is called when the plugin is disabled. If your plugin needs to
		// save data before exiting, clean up temporary files or close database
		// connections you can do that here
	}

	// This is our event that sends a welcome message to players when they join.

	// To make an event we create a function with any name of our choice then we add
	// the @EventHandler annotation to the function. The event that this function
	// handles is determined by the class type our function takes as an argument.

	// A list of all event types can be found here
	// https://hub.spigotmc.org/javadocs/spigot/org/bukkit/event/package-summary.html
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerJoin(PlayerJoinEvent e) {
		// Now we get the player from the PlayerJoinEvent and send the message we loaded
		// from config.yml
		Player player = e.getPlayer();

		// by using ChatColor.translateAlternateColorCodes('$', welcomeMessage) we can
		// set the color of our message in config.yml by using $a to get a green
		// message. a list of all colors can be found here
		// https://minecraft.fandom.com/wiki/Formatting_codes
		player.sendMessage(ChatColor.translateAlternateColorCodes('$', welcomeMessage));
	}
}