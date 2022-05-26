/*
 * Copyright (c) 2022 Ancient Productions

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
