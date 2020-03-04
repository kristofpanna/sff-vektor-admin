package com.codecool.kp.sffvectoradmin.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reader {
    private Long id;

    private String molyName;
    private String url;
}
