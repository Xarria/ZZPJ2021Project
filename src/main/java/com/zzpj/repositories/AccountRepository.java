package com.zzpj.repositories;

import com.zzpj.model.entities.Account;
import com.zzpj.model.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByLogin(String login);

    List<Account> findAccountsByFavouriteRecipesContaining(Recipe recipe);
}
