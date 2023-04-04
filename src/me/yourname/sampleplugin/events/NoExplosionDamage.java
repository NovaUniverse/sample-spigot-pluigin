package me.yourname.sampleplugin.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

/**
 * to keep our code clean we can create different classes for our EventHandlers.
 * <p>
 * there is no limit to how many event handlers we can have in a single class
 * but by splitting our plugins into multiple class files we keep our code clean
 * and easy to read.
 * <p>
 * Since we use the @EventHandler annotations in here we also need to implement
 * {@link Listener} so that spigot knows this class will use EventHandlers
 */
public class NoExplosionDamage implements Listener {
	// Some events implements Cancellable and that means that we can cancel the
	// event to prevent certain things from happening on our server. For example
	// this function below will make it that entities will no longer take explosion
	// damage
	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityDamage(EntityDamageEvent e) {
		// We check if the damage cause is BLOCK_EXPLOSION or ENTITY_EXPLOSION
		if (e.getCause() == DamageCause.BLOCK_EXPLOSION || e.getCause() == DamageCause.ENTITY_EXPLOSION) {
			// We cancel the damage event here
			e.setCancelled(true);
		}
	}
}