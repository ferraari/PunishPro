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

import com.ancient.punish.interfaces.PunishBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class Punish implements PunishBase {

    private UUID uniqueId;

    private String operator;
    private String victim;

    private Evidence evidence;
    private Type type;
    @Setter
    private Status status;

    private Instant date;
}
