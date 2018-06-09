package com.perepelitsya.rentbicycles.repository;

import com.perepelitsya.rentbicycles.model.QueriesHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueriesHistoryRepository extends JpaRepository<QueriesHistory, Long> {
}
