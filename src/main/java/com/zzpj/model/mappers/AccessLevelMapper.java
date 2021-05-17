package com.zzpj.model.mappers;

import com.zzpj.model.entities.AccessLevel;

public class AccessLevelMapper {

    public static String entityToDTO(AccessLevel accessLevel) {
        return accessLevel.getName();
    }
}
