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

import com.ancient.punish.interfaces.EvidenceBase;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Evidence implements EvidenceBase {

    private String evidence;
    private String proof;

    @Override
    public boolean hasEvidence() {
        return evidence != null;
    }

    @Override
    public boolean hasProof() {
        return proof != null;
    }
}
