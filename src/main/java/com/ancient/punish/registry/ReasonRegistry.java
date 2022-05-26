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

import com.ancient.punish.model.Reason;
import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.Map;

@Getter
public class ReasonRegistry {

    private final Map<String, Reason> reasonMap = Maps.newHashMap();

    public Reason getReason(String evidence) {
        return reasonMap.get(evidence);
    }
}
