package xyz.namutree0345.melonySmp.util

import org.bukkit.Bukkit
import xyz.namutree0345.melonySmp.plugin

object TickUtil {

    fun runTaskUntilTheTime(runnable: Runnable, repeatUntil: Long, repeatPer: Long) {
        val taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, runnable, 0L, repeatPer)
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, { Bukkit.getScheduler().cancelTask(taskId) }, repeatUntil)
    }

    fun runTaskAfterTheTime(runnable: Runnable, time: Long) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, runnable, time)
    }

}