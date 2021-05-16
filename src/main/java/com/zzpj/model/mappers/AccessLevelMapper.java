package com.zzpj.model.mappers;

import com.zzpj.model.DTOs.AccessLevelDTO;
import com.zzpj.model.entities.AccessLevel;

public class AccessLevelMapper {

    public static AccessLevelDTO entityToDTO(AccessLevel accessLevel) {
        AccessLevelDTO accessLevelDTO = new AccessLevelDTO();
        accessLevelDTO.setName(accessLevel.getName());
        return accessLevelDTO;
    }
}
