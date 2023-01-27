package com.paraparp.realstatestats.controller;


import com.paraparp.realstatestats.services.IdealistaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
public class GetStatsController {

    private final IdealistaService idealistaService;

    @PostMapping("/stats")
    public List<Map<String, Object>> getStatsFromIdealista() {
        return idealistaService.getStatistics();
    }
}
