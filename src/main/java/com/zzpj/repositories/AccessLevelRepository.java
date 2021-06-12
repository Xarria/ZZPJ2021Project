package com.zzpj.repositories;

import com.zzpj.model.entities.AccessLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessLevelRepository extends JpaRepository<AccessLevel, Long> {

    AccessLevel findAccessLevelByName(String name);
}
