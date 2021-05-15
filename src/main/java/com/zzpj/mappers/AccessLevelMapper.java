package com.zzpj.mappers;

import com.zzpj.DTOs.AccessLevelDTO;
import com.zzpj.model.AccessLevel;

public class AccessLevelMapper {

    public static AccessLevelDTO entityToDTO(AccessLevel accessLevel) {
        AccessLevelDTO accessLevelDTO = new AccessLevelDTO();
        accessLevelDTO.setName(accessLevel.getName());
        return accessLevelDTO;
    }
}
