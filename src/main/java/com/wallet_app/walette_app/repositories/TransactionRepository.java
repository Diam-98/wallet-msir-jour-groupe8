package com.wallet_app.walette_app.repositories;

import com.wallet_app.walette_app.models.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Long, Transaction> {
}
