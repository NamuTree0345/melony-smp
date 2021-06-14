package xyz.namutree0345.melonySmp.event

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerLoginEvent
import org.bukkit.event.player.PlayerQuitEvent
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class JoinQuitEvent : Listener {

    val alreadyKicked = ArrayList<UUID>()
    private val kickText = arrayListOf("이제 그만 접지 그래?", "넌 답이 없어.", "걍 들오지마 ㅋ", "- 유료 결제시 접속 가능합니다 ㅋ -",
                                        "그만해라 마")

    @EventHandler
    fun login(event: PlayerLoginEvent) {
        if(alreadyKicked.contains(event.player.uniqueId)) {
            event.disallow(PlayerLoginEvent.Result.KICK_BANNED, Component.text(kickText[Random.nextInt(kickText.size)], NamedTextColor.DARK_RED, TextDecoration.BOLD)
                .append(Component.text("\n죽은 시간으로부터 10초 뒤 접속 ㄱㄴ", NamedTextColor.WHITE)))
        }
    }

    @EventHandler
    fun join(event: PlayerJoinEvent) {event.joinMessage(Component.text("${event.player.name}이 멜로니로 감염된 세계에 입장하였습니다.", NamedTextColor.YELLOW))}
    @EventHandler
    fun quit(event: PlayerQuitEvent) {event.quitMessage(Component.text("${event.player.name}이 멜로니로 감염된 세계에 퇴장하였습니다.", NamedTextColor.YELLOW))}

    @EventHandler
    fun death(event: PlayerDeathEvent) {
        alreadyKicked.add(event.entity.uniqueId)
        event.entity.kick(Component.text(kickText[Random.nextInt(kickText.size)], NamedTextColor.DARK_RED, TextDecoration.BOLD)
            .append(Component.text("\n죽은 시간으로부터 10초 뒤 접속 ㄱㄴ", NamedTextColor.WHITE)))
    }

}