/*
 * Copyright (C) 2022 Diluvian
 *
 * You may not use this file except in compliance with the Team Agreement.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */

package com.ancient.punish.factory;

import com.ancient.punish.PunishPlugin;
import com.ancient.punish.codec.ReasonDecode;
import com.ancient.punish.registry.ReasonRegistry;
import lombok.AllArgsConstructor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

@AllArgsConstructor
public class ReasonFactory {

    private ReasonRegistry reasonRegistry;

    public void provideAll(PunishPlugin plugin) {
        final File file = new File(plugin.getDataFolder(), "reasons.yml");

        final YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);

        reasonRegistry.getReasonMap().putAll(
          ReasonDecode.decode(yamlConfiguration)
        );
    }
}
