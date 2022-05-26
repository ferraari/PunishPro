/*
 * Copyright (C) 2022 Diluvian
 *
 * You may not use this file except in compliance with the Team Agreement.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */

package com.ancient.punish.codec;

import com.ancient.punish.model.Reason;
import com.google.common.collect.Maps;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReasonDecode {

    public static Map<String, Reason> decode(FileConfiguration configuration) {
        final ConfigurationSection section = configuration.getConfigurationSection("reasons");

        if (section == null) {
            return new HashMap<>();
        }

        final Map<String, Reason> reasonMap = Maps.newHashMap();

        for (String key : section.getKeys(false)) {
            final String name = section.getString(key + ".name");
            final List<String> description = section.getStringList(key + ".description");

            description.replaceAll(line -> ChatColor.translateAlternateColorCodes('&', line));

            reasonMap.put(key, Reason.builder()
              .reference(key)
              .name(name)
              .description(description)
              .build());
        }
        return reasonMap;
    }
}
