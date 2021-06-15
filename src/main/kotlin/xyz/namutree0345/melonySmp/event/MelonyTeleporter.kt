package xyz.namutree0345.melonySmp.event

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerPortalEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.util.*

val melonyTeleporter = ItemStack(Material.PLAYER_HEAD).also {
    it.itemMeta = (it.itemMeta as SkullMeta).also { im ->
        im.displayName(Component.text("멜로니 텔레포터", NamedTextColor.GOLD, TextDecoration.BOLD))
        im.owningPlayer = Bukkit.getOfflinePlayer(Bukkit.getPlayerUniqueId("Me1ony")!!)
    }
}

class MelonyTeleporter : Listener {

    @EventHandler
    fun goToEnd(event: PlayerPortalEvent) {
        if(event.player.inventory.itemInMainHand.type != Material.PLAYER_HEAD) {
            if(event.player.inventory.itemInMainHand.displayName() != melonyTeleporter.displayName()) {
                event.isCancelled = true
                event.player.sendMessage(Component.text("멜로니 매게체가 필요합니다.", NamedTextColor.RED, TextDecoration.BOLD))
            } else {
                event.player.inventory.itemInMainHand.subtract()
            }
        }
    }

}