package me.mikinol.hostnamegate

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.plugin.java.JavaPlugin


class HostnameGatePaper : JavaPlugin() {
    private lateinit var allowedHosts: List<String>
    private lateinit var kickMessage: Component
    private lateinit var mode: String
    override fun reloadConfig() {
        super.reloadConfig()
        config.options().copyDefaults(true)
        saveConfig()

        allowedHosts = config.getStringList("allowed-hosts")
        kickMessage = MiniMessage.miniMessage().deserialize(config.getString("kick-message") ?: "<red>You must connect through an allowed hostname!</red>")
        val mode = config.getString("mode")?.lowercase() ?: "null"
        if (mode != "default" && mode != "protocollib") {
            logger.warning("Invalid mode in config: $mode. Using default mode.")
            this.mode = "default"
        }else {
            this.mode = mode
        }
    }

    override fun onEnable() {
        this.reloadConfig()
    }
}