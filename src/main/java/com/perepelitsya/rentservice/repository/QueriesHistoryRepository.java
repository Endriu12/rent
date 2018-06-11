package com.perepelitsya.rentservice.repository;

import com.perepelitsya.rentservice.model.QueriesHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueriesHistoryRepository extends JpaRepository<QueriesHistory, Long> {
}
