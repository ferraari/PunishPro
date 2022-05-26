/*
 * Copyright (C) 2022 Diluvian
 *
 * You may not use this file except in compliance with the Team Agreement.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */

package com.ancient.punish.registry;

import com.ancient.punish.model.Punish;
import com.ancient.punish.model.Status;
import com.google.common.collect.Sets;
import lombok.Getter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class PunishRegistry {

    private final Set<Punish> punishes = Sets.newHashSet();

    public Punish getActivatedPunish(String victimName) {
        return punishes.stream()
          .filter(punish -> punish.getVictim().equalsIgnoreCase(victimName))
          .filter(punish -> punish.getStatus() == Status.ALREADY_BANNED)
          .findAny()
          .orElse(null);
    }

    public List<Punish> getAllPunishes(String victimName) {
        return punishes.stream()
          .filter(punish -> punish.getVictim().equalsIgnoreCase(victimName))
          .collect(Collectors.toList());
    }

    public List<Punish> getAllPunisheds(String operatorName) {
        return punishes.stream()
          .filter(punish -> punish.getOperator().equalsIgnoreCase(operatorName))
          .collect(Collectors.toList());
    }
}
