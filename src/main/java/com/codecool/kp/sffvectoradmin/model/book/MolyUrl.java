package com.codecool.kp.sffvectoradmin.model.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MolyUrl {
    private Long id;

    private String url;
    private int seqId;
}
