package com.codecool.kp.sffvectoradmin.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MolyShelfItem {
    /** Represents an element of a "lista" or "polc" on moly.hu */
    private String url;
    private String description;  // for debug and logging
    private String note;  // text from the yellow note box
}
