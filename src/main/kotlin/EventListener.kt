package me.mikinol.hostnamegate

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerPreLoginEvent

class EventListener : Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    fun preLoginEvent(event: AsyncPlayerPreLoginEvent) {
        val allowedHostsList = HostnameGatePlugin.instance.allowedHosts
        if (allowedHostsList.isEmpty() || HostnameGatePlugin.instance.mode != "default")
            return

        if (!allowedHostsList.contains(event.hostname)) {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, HostnameGatePlugin.instance.kickMessage)
        }
    }
}