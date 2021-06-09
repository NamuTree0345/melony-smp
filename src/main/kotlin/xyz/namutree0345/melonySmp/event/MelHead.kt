package xyz.namutree0345.melonySmp.event

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.entity.EntityType
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.util.EulerAngle
import xyz.namutree0345.melonySmp.util.TickUtil
import java.util.*

val melHeadItem = ItemStack(Material.PLAYER_HEAD).also {
    it.itemMeta = (it.itemMeta as SkullMeta).also { im ->
        im.displayName(Component.text("멜로니 머리", TextColor.color(0x8FFF7D), TextDecoration.BOLD))
        im.owningPlayer = Bukkit.getOfflinePlayer(UUID.fromString("255ca5b2-2da3-4030-a747-5c224224a611"))
    }
}

class MelHead : Listener {

    fun headRightClick(event: PlayerInteractEvent) {
        if(event.action.toString().contains("RIGHT_CLICK")) {
            if(event.player.inventory.itemInMainHand.type == melHeadItem.type) {
                val armorStand = (event.player.world.spawnEntity(
                    event.player.location,
                    EntityType.ARMOR_STAND
                )) as org.bukkit.entity.ArmorStand

                armorStand.velocity = event.player.location.direction.multiply(10)
                armorStand.isInvulnerable = true
                armorStand.setDisabledSlots(
                    EquipmentSlot.OFF_HAND,
                    EquipmentSlot.HAND,
                    EquipmentSlot.HEAD,
                    EquipmentSlot.CHEST,
                    EquipmentSlot.FEET,
                    EquipmentSlot.LEGS
                )
                armorStand.isInvisible = true
                armorStand.setArms(true)
                armorStand.rightArmPose = EulerAngle(270.0, 0.0, 90.0)
                armorStand.equipment?.setItemInMainHand(melHeadItem)
                TickUtil.runTaskUntilTheTime({
                    armorStand.world.spawnParticle(Particle.FLAME, armorStand.location.x, armorStand.location.y + 1, armorStand.location.z, 1)
                    for(e in armorStand.location.getNearbyLivingEntities(4.0)) {
                        if(e.type != EntityType.PLAYER) {
                            e.damage(3.0)
                        }
                    }
                }, 60L, 1L)
                TickUtil.runTaskAfterTheTime({
                    if(!armorStand.isDead) armorStand.remove()
                }, 60L)
            }
        }
    }

}