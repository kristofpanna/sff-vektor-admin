package com.codecool.kp.sffvectoradmin.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Plan {
    private Long id;

    private PlanStatus status;

    public enum PlanStatus {
        UNDECIDED,
        NO,
        YES,
        DONE
    }
}
