package xyz.namutree0345.melonySmp.event

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.title.Title
import org.bukkit.Bukkit
import org.bukkit.block.data.type.Bed
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import xyz.namutree0345.melonySmp.getInstance
import java.time.Duration

var endingTime = 0
var endingTask = -999

class Ending : Listener {

    class EndingScheduler : Runnable {

        override fun run() {
            endingTime++
            when(endingTime) {
                10 -> {
                    Bukkit.getServer().showTitle(Title.title(Component.text("멜로니는 드래곤을 죽였습니다.", NamedTextColor.GREEN), Component.empty(), Title.Times.of(
                        Duration.ofMillis(5000), Duration.ofMillis(3000), Duration.ofMillis(5000))))
                }

                10 + 13 * 1 -> {
                    Bukkit.getServer().showTitle(Title.title(Component.text("멜로니는 이 제작자에게 소리쳤습니다.", NamedTextColor.GREEN), Component.empty(), Title.Times.of(
                            Duration.ofMillis(5000), Duration.ofMillis(3000), Duration.ofMillis(5000))))
                }

                10 + 13 * 2 -> {
                    Bukkit.getServer().showTitle(Title.title(Component.text("tlqkf!!!!", NamedTextColor.GREEN), Component.empty(), Title.Times.of(
                        Duration.ofMillis(5000), Duration.ofMillis(3000), Duration.ofMillis(5000))))
                }

                10 + 13 * 3 -> {
                    Bukkit.getServer().showTitle(Title.title(Component.text("결국 멜로니는 이 세상에서 퇴출당할까요?", NamedTextColor.GREEN), Component.empty()))
                }

                10 + 13 * 4 -> {
                    Bukkit.getOnlinePlayers().forEach {
                        it.kick(Component.text("뭘봐"))
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "stop")
                    }
                }
            }
        }
    }

    @EventHandler
    fun kill(event: EntityDeathEvent) {
        val entity: LivingEntity = event.entity
        if(event.entityType == EntityType.ENDER_DRAGON) {
            if (entity.killer is Player || entity.killer is Bed || entity.killer is LivingEntity) {
                val loc = entity.killer?.location?.add(0.0, 0.9, 0.0)
                Bukkit.getOnlinePlayers().forEach {
                    it.addPotionEffect(PotionEffect(PotionEffectType.LEVITATION, 1000000, 0, false, false))
                    it.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, 255, false, false))
                    it.addPotionEffect(PotionEffect(PotionEffectType.SLOW, 1000000, 255, false, false))
                }
            }
        }
        endingTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(getInstance(), EndingScheduler(), 0, 20)
    }

}