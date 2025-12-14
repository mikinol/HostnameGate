package me.mikinol.hostnamegate

import io.papermc.paper.plugin.bootstrap.BootstrapContext
import io.papermc.paper.plugin.bootstrap.PluginBootstrap
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents

@Suppress("UnstableApiUsage", "unused")
class HostnameGateBootstrap : PluginBootstrap {
    override fun bootstrap(context: BootstrapContext) {
        context.lifecycleManager.registerEventHandler(LifecycleEvents.COMMANDS) { event ->
            event.registrar().register(ReloadCommand.command)
        }
    }
}