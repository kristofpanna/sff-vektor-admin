package com.codecool.kp.sffvectoradmin;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

public class BaseTestWithMockito {
    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.initMocks(this);
    }

}

