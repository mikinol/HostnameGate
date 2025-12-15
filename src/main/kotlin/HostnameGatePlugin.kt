package me.mikinol.hostnamegate

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.ConsoleHandler
import java.util.logging.Level
import java.util.logging.SimpleFormatter
import kotlin.math.log


class HostnameGatePlugin : JavaPlugin() {
    companion object {
        lateinit var instance: HostnameGatePlugin
            private set
        fun debug(msg: String) {
            if (instance.debugMode) {
                instance.logger.info("[DEBUG] $msg")
            }
        }

    }

    lateinit var allowedHosts: List<String>
        private set
    lateinit var kickMessage: Component
        private set
    lateinit var mode: String
        private set
    var debugMode: Boolean = false
        private set

    override fun reloadConfig() {
        saveDefaultConfig()
        super.reloadConfig()
        config.options().copyDefaults(true)
        saveConfig()

        allowedHosts = config.getStringList("allowed-hosts")
        kickMessage = MiniMessage.miniMessage()
            .deserialize(config.getString("kick-message") ?: "<red>You must connect through an allowed hostname!</red>")
        val mode = config.getString("mode")?.lowercase() ?: "null"
        if (mode != "default" && mode != "protocollib") {
            logger.warning("Invalid mode in config: $mode. Using default mode.")
            this.mode = "default"
        } else {
            if (mode == "protocollib") {
                logger.severe("Protocollib mode currently not supported")
            }
            this.mode = mode
        }
        debugMode = config.getBoolean("debug")
        debug("Config reloaded!")
    }

    override fun onLoad() {
        instance = this
    }

    override fun onEnable() {
        this.reloadConfig()
        Bukkit.getPluginManager().registerEvents(EventListener(), instance)
    }
}