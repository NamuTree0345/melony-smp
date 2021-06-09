package xyz.namutree0345.melonySmp.event

import com.destroystokyo.paper.event.server.PaperServerListPingEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class ServerListPing : Listener {

    @EventHandler
    fun ping(event: PaperServerListPingEvent) {
        event.setHidePlayers(true)
        event.numPlayers = 666
        event.maxPlayers = 666
        event.motd(Component.text("MELONY SMP.", NamedTextColor.GREEN, TextDecoration.ITALIC, TextDecoration.BOLD))
    }

}