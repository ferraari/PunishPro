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
