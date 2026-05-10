package com.example.minecraftbash;

import org.bukkit.plugin.java.JavaPlugin;

public class MinecraftBashPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("MinecraftBashPlugin 已启用!");
        this.getCommand("bash").setExecutor(new CommandBash());
    }

    @Override
    public void onDisable() {
        getLogger().info("MinecraftBashPlugin 已禁用!");
    }
}
