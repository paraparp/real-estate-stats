package scheduled;

import com.paraparp.realestatestats.services.IdealistaService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private final IdealistaService idealistaService;

    @PostConstruct
    public void executeGenerateDailyStats() {
        idealistaService.getStatistics();
    }
}