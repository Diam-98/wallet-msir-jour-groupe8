package com.wallet_app.walette_app.repositories;

import com.wallet_app.walette_app.models.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawRepository extends JpaRepository<Withdraw, Long> {
}
