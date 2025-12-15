package me.mikinol.hostnamegate

import me.mikinol.hostnamegate.HostnameGatePlugin.Companion.debug
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerPreLoginEvent
import kotlin.math.log

class EventListener : Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    fun preLoginEvent(event: AsyncPlayerPreLoginEvent) {
        debug("${event.name} connecting through host: ${event.hostname}")

        val allowedHostsList = HostnameGatePlugin.instance.allowedHosts
        if (allowedHostsList.isEmpty()) {
            debug("Allowed hosts list is empty, skip")
            return
        }
        if (HostnameGatePlugin.instance.mode != "default") {
            return
        }

        if (!allowedHostsList.contains(event.hostname)) {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, HostnameGatePlugin.instance.kickMessage)
        }
    }
}