package com.paraparp.realestatestats.scheduled;

import com.paraparp.realestatestats.services.IdealistaService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduledTasks {

    private final IdealistaService idealistaService;

    @PostConstruct
    public void executeGenerateDailyStats() {
        log.info("Getting today statistics...");
        idealistaService.getStatistics();
    }
}