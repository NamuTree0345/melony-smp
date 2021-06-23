package xyz.namutree0345.melonySmp

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin
import xyz.namutree0345.melonySmp.event.*

lateinit var plugin: JavaPlugin

fun getInstance() : MelonySmp = JavaPlugin.getPlugin(MelonySmp::class.java)

class MelonySmp : JavaPlugin() {

    // Melony SMP (Survival Multi Player)

    override fun onEnable() {
        plugin = this

        val headRecipe = ShapedRecipe(NamespacedKey.minecraft("melony_head"), melHeadItem.clone().also { it.amount = 10 })
        headRecipe.shape(" * ", "*B*", " * ")
        headRecipe.setIngredient('*', Material.MELON)
        headRecipe.setIngredient('B', Material.ARROW)

        val teleporterRecipe = ShapedRecipe(NamespacedKey.minecraft("melony_teleporter"), melonyTeleporter)
        teleporterRecipe.shape("***", "BAB", "***")
        teleporterRecipe.setIngredient('*', Material.MELON)
        teleporterRecipe.setIngredient('B', Material.MELON_SLICE)
        teleporterRecipe.setIngredient('A', Material.ENDER_PEARL)
        server.addRecipe(headRecipe)
        server.addRecipe(teleporterRecipe)

        server.pluginManager.registerEvents(CustomEntitySpawn(), this)
        server.pluginManager.registerEvents(ServerListPing(), this)
        server.pluginManager.registerEvents(MelonyTeleporter(), this)
        server.pluginManager.registerEvents(MelHead(), this)
        server.pluginManager.registerEvents(JoinQuitEvent(), this)
        server.pluginManager.registerEvents(Ending(), this)
    }

}