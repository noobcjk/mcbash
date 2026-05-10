package com.example.minecraftbash;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandBash implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("minecraftbash.execute")) {
            sender.sendMessage("§c你没有权限执行此命令!");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("§c用法: /bash <命令>");
            return true;
        }

        StringBuilder cmdBuilder = new StringBuilder();
        for (String arg : args) {
            cmdBuilder.append(arg).append(" ");
        }
        String bashCommand = cmdBuilder.toString().trim();

        try {
            ProcessBuilder pb = new ProcessBuilder("bash", "-c", bashCommand);
            pb.redirectErrorStream(true);
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            sender.sendMessage("§a===== 命令输出 =====");
            while ((line = reader.readLine()) != null) {
                sender.sendMessage(line);
            }
            process.waitFor();
            sender.sendMessage("§a===== 执行完成 =====");
        } catch (Exception e) {
            sender.sendMessage("§c执行命令出错: " + e.getMessage());
            e.printStackTrace();
        }

        return true;
    }
}
