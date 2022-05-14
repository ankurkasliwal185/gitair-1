package com.getair.bookStore.controller;


import com.getair.bookStore.model.Statistic;
import com.getair.bookStore.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/statistic")
@RequiredArgsConstructor
public class StatisticController {
    private final StatisticService statisticService;

    @GetMapping("{id}")
    public ResponseEntity<List<Statistic>> getMonthlyStatistic(@PathVariable Long id) {
        return ResponseEntity.ok(statisticService.getMonthlyStatistic(id));
    }
}
