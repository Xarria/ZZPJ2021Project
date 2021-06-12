package com.zzpj.services;

import com.zzpj.model.entities.AccessLevel;
import com.zzpj.repositories.AccessLevelRepository;
import com.zzpj.services.interfaces.AccessLevelServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessLevelService implements AccessLevelServiceInterface {

    private AccessLevelRepository accessLevelRepository;

    @Autowired
    public AccessLevelService(AccessLevelRepository accessLevelRepository) {
        this.accessLevelRepository = accessLevelRepository;
    }

    @Override
    public AccessLevel getAccessLevelByName(String name) {
        return accessLevelRepository.findAccessLevelByName(name);
    }
}
