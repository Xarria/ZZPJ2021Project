package com.zzpj.model.mappers;

import com.zzpj.model.entities.AccessLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccessLevelMapperTest {

    private final Long id = 1L;
    private final String accessLevelString = "user";
    private AccessLevel accessLevel;

    @BeforeEach
    void init() {
        accessLevel = createAccessLevel();
    }

    @Test
    void entityToDTO() {
        String accessLevelDTO = AccessLevelMapper.entityToDTO(accessLevel);
        assertEquals(accessLevelString, accessLevelDTO);
    }

    private AccessLevel createAccessLevel() {
        return new AccessLevel(id, accessLevelString);
    }
}
