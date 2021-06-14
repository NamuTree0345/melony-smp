package xyz.namutree0345.melonySmp

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin
import xyz.namutree0345.melonySmp.event.CustomEntitySpawn
import xyz.namutree0345.melonySmp.event.MelHead
import xyz.namutree0345.melonySmp.event.ServerListPing
import xyz.namutree0345.melonySmp.event.melHeadItem

lateinit var plugin: JavaPlugin

class MelonySmp : JavaPlugin() {

    // Melony SMP (Survival Multi Player)

    override fun onEnable() {
        plugin = this

        val headRecipe = ShapedRecipe(NamespacedKey.minecraft("melony_head"), melHeadItem.clone().also { it.amount = 3 })
        headRecipe.shape(" * ", "*B*", " * ")
        headRecipe.setIngredient('*', Material.MELON)
        headRecipe.setIngredient('B', Material.ARROW)
        server.addRecipe(headRecipe)

        server.pluginManager.registerEvents(CustomEntitySpawn(), this)
        server.pluginManager.registerEvents(ServerListPing(), this)
        server.pluginManager.registerEvents(MelHead(), this)
    }

}