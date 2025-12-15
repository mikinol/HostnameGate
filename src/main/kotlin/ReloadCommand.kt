package me.mikinol.hostnamegate

import com.mojang.brigadier.Command
import com.mojang.brigadier.tree.LiteralCommandNode
import io.papermc.paper.command.brigadier.CommandSourceStack
import io.papermc.paper.command.brigadier.Commands
import me.mikinol.hostnamegate.HostnameGatePlugin.Companion.debug


object ReloadCommand {
    val command: LiteralCommandNode<CommandSourceStack> = Commands.literal("hostnamegate")
        .requires { source: CommandSourceStack -> source.sender.hasPermission("hostnamegate.reload") }
        .then(
            Commands.literal("reload")
                .executes { ctx ->
                    debug("${ctx.source.sender} reloading plugin")
                    HostnameGatePlugin.instance.reloadConfig()
                    ctx.source.sender.sendMessage("Config reloaded")
                    Command.SINGLE_SUCCESS
                }
        ).build()
}