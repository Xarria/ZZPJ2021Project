package com.zzpj.repositories;

import com.zzpj.model.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByLogin(String login);
}