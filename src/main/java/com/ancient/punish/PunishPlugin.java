/*
 * Copyright (C) 2022 Diluvian
 *
 * You may not use this file except in compliance with the Team Agreement.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */

package com.ancient.punish;

import com.ancient.punish.command.Commands;
import com.ancient.punish.factory.ReasonFactory;
import com.ancient.punish.listener.LoginListener;
import com.ancient.punish.listener.MutedListener;
import com.ancient.punish.registry.PunishRegistry;
import com.ancient.punish.registry.ReasonRegistry;
import com.ancient.punish.service.PunishService;
import me.saiintbrisson.bukkit.command.BukkitFrame;
import me.saiintbrisson.minecraft.ViewFrame;
import me.saiintbrisson.minecraft.command.message.MessageHolder;
import me.saiintbrisson.minecraft.command.message.MessageType;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.ConcurrentHashMap;

public class PunishPlugin extends JavaPlugin {



    private PunishRegistry punishRegistry;
    private ReasonRegistry reasonRegistry;

    private ReasonFactory reasonFactory;

    private PunishService punishService;

    private BukkitFrame commandFrame;
    private ViewFrame viewFrame;

    @Override
    public void onLoad() {
        if (!getDataFolder().exists() && getDataFolder().mkdirs()) {
            saveDefaultConfig();

            saveResource("reasons.yml", false);
        }

        punishRegistry = new PunishRegistry();
        reasonRegistry = new ReasonRegistry();

        reasonFactory = new ReasonFactory(reasonRegistry);

        punishService = new PunishService(punishRegistry);
    }

    @Override
    public void onEnable() {
        reasonFactory.provideAll(this);

        commands();
        listeners();
        views();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private void commands() {
        commandFrame = new BukkitFrame(this);

        final MessageHolder messageHolder = commandFrame.getMessageHolder();
        messageHolder.setMessage(MessageType.NO_PERMISSION, "no perm (change it later)");

        commandFrame.registerCommands(
          new Commands(
            reasonRegistry,
            punishService
          )
        );
    }

    private void listeners() {
        final PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new LoginListener(punishRegistry), this);
        pluginManager.registerEvents(new MutedListener(punishRegistry), this);
    }

    private void views() {
        viewFrame = new ViewFrame(this);

        viewFrame.register(


        );
    }
}
