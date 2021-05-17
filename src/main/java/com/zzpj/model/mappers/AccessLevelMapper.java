package com.zzpj.model.mappers;

import com.zzpj.model.entities.AccessLevel;

public class AccessLevelMapper {

//    public static String entityToDTO(AccessLevel accessLevel) {
//        return accessLevel.getName();
//    }
//
    public static AccessLevel dtoToEntity(String accessLevelDTO) {
        AccessLevel accessLevel = new AccessLevel();
        accessLevel.setName(accessLevelDTO);
        return accessLevel;
    }
}
