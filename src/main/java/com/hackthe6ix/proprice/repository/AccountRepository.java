package com.hackthe6ix.proprice.repository;

import com.hackthe6ix.proprice.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
