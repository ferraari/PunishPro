/*
 * Copyright (C) 2022 Diluvian
 *
 * You may not use this file except in compliance with the Team Agreement.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */

package com.ancient.punish.model;

import java.time.Instant;
import java.util.UUID;

public class Mute extends Punish {

    public Mute(UUID uniqueId, String operator, String victim, Evidence evidence, Type type, Status status, Instant date) {
        super(uniqueId, operator, victim, evidence, type, status, date);
    }
}