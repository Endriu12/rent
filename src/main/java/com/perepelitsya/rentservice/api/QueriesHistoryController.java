package com.perepelitsya.rentservice.api;

import com.perepelitsya.rentservice.model.QueriesHistory;
import com.perepelitsya.rentservice.repository.QueriesHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/queries")
public class QueriesHistoryController {

    private final QueriesHistoryRepository queriesHistoryRepository;

    @GetMapping
    public List<QueriesHistory> getAllQueriesHistories() {
        return queriesHistoryRepository.findAll();
    }

}
