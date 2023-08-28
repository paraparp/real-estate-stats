package com.paraparp.realestatestats.controller;


import com.paraparp.realestatestats.services.IdealistaService;
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
public class GenerateStatsController {

    private final IdealistaService idealistaService;

    @PostMapping("/stats")
    public List<Map<String, Object>> getStatsFromIdealista() {
        return idealistaService.getStatistics();
    }


}
