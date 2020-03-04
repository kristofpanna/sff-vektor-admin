package com.codecool.kp.sffvectoradmin.model.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Alternative {
    private Long id;

    private String name;

    public Alternative(String name) {
        this.name = name;
    }
}
